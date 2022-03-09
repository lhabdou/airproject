package com.airproject.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airproject.domain.Customer;
import com.airproject.domain.Drone;
import com.airproject.domain.FlightPlan;
import com.airproject.domain.Order;
import com.airproject.domain.Position;
import com.airproject.domain.Product;
import com.airproject.domain.Store;
import com.airproject.mappers.ServiceMapper;
import com.airproject.repositories.DroneRepository;
import com.airproject.repositories.StoreRepository;
import com.airproject.services.CalculateDistance;
import com.airproject.services.IFlightsPlanService;

@Service
public class FlightsPlanServiceImpl implements IFlightsPlanService {

	@Autowired
	private StoreRepository storeRepository;

	@Autowired
	private DroneRepository droneRepository;

	@Autowired
	private ServiceMapper serviceMapper;

	@Override
	public List<FlightPlan> getFlightsPlan(List<Order> orders) {

		// Récupération des drones

		List<Drone> drones = serviceMapper.convertListDroneEntityToListDrone(droneRepository.findAll());

		// Récupération du stock des magasins
		List<Store> stock = serviceMapper
				.convertListStoreEntityToListStore(storeRepository.findAllStoresWithProducts());

		List<FlightPlan> flightPlan = new ArrayList<FlightPlan>();
		// on boucle sur chaque commande d'habitant
		orders.forEach(o -> {
			// on boucle sur chaque type de produit d'une commande d'un habitant
			o.getProducts().forEach(product -> {

				makeFlightPlanForEveryProduct(drones, stock, flightPlan, o, product);

			});

		});

		return flightPlan;
	}

	/**
	 * @param drones
	 * @param stock
	 * @param flightPlan
	 * @param o
	 * @param product
	 */
	private void makeFlightPlanForEveryProduct(List<Drone> drones, List<Store> stock, List<FlightPlan> flightPlan,
			Order o, Product product) {
		// on boucle sur la quantité de chaque produit
		for (int i = 0; i < product.getQuantity(); i++) {

			// 1-> Magasins où le produit est disponible
			List<Store> storesProductAvailable = stock.stream().filter(s -> s.getProducts().contains(product))
					.collect(Collectors.toList());

			// 2-> On choisit le magasin le plus proche
			Store storeCloser = chooseStoreCloserCustomerAndUpdateStock(storesProductAvailable, o.getCustomer(),
					product);

			// 3-> On choisit le drone le plus proche du magasin
			Drone drone = chooseDroneCloserStore(storeCloser, drones, o.getCustomer());

			// 4-> On construit le plan de vol pour chaque produit
			FlightPlan flight = FlightPlan.builder().productId(product.getProductId()).storeId(storeCloser.getStoreId())
					.droneId(drone.getDroneId()).CustomerId(o.getCustomer().getCustomerId()).build();

			// 5-> On ajoute le plan de vol du produit à la liste des plans de vol
			flightPlan.add(flight);

			// 6-> On met à jour la position et l'autonomie du drone
			updateDronePositionAndAutonomy(drone, o.getCustomer());
		}
	}

	/**
	 * @param drone
	 * @param customer
	 */
	private void updateDronePositionAndAutonomy(Drone drone, Customer customer) {

		drone.setDronePosition(customer.getCustomerPosition());
		drone.setAutonomy(drone.getAutonomy() - drone.getDistanceParcourue());

		// On pourrait enregistrer en BDD Si besoin
		// droneRepository.save(serviceMapper.convertDroneToDroneEntity(drone));

	}

	/**
	 * @param storeCloser
	 * @param drones
	 * @param customer
	 * @return
	 */
	private Drone chooseDroneCloserStore(Store storeCloser, List<Drone> drones, Customer customer) {

		// calcul de distance vers le magasin pour chaque drone
		Map<Drone, Integer> mapDistancesDronesStore = calculateDistancesFromDronesToCloserStore(storeCloser, drones);

		// Choix du drone plus proche
		Drone droneChoosen = mapDistancesDronesStore.entrySet().stream()
				.filter(map -> map.getKey().getAutonomy() >= storeCloser.getDistanceStoreCustomer())
				.min(Map.Entry.comparingByValue()).get().getKey();

		// Ajout de la distance à parcourir jusqu'à l'habitant
		droneChoosen.setDistanceParcourue(
				mapDistancesDronesStore.get(droneChoosen) + storeCloser.getDistanceStoreCustomer());

		return droneChoosen;
	}

	/**
	 * @param storeCloser
	 * @param drones
	 * @return
	 */
	private Map<Drone, Integer> calculateDistancesFromDronesToCloserStore(Store storeCloser, List<Drone> drones) {
		Map<Drone, Integer> mapDistancesDronesStore = new HashMap<Drone, Integer>();
		CalculateDistance<Position, Position, Double> distance = calculateDistanceBeetweenTWoPositions();
		drones.forEach(d -> {
			Integer distanceP = distance.calculateDistance(storeCloser.getStorePosition(), d.getDronePosition())
					.intValue();
			mapDistancesDronesStore.put(d, distanceP);
		});
		return mapDistancesDronesStore;
	}

	/**
	 * @param storesProductAvailable
	 * @param customer
	 * @param product
	 * @return
	 */
	private Store chooseStoreCloserCustomerAndUpdateStock(List<Store> storesProductAvailable, Customer customer,
			Product product) {

		// calcul de distance de chaque magasin jusqu'à l'habitant
		Map<Store, Integer> mapDistancesStoreCustomer = calculateDistancesFromStoresToCustomer(storesProductAvailable,
				customer);

		// Choix du magasin le plus proche du client
		Store store = mapDistancesStoreCustomer.entrySet().stream()
				.filter(s -> s.getKey().getProducts().get(s.getKey().getProducts().indexOf(product)).getQuantity() != 0)
				.min(Map.Entry.comparingByValue()).get().getKey();

		// Mise à jour du stock en retirant le produit commandé
		updateStockOfStore(product, store);

		// Enregistrer la mise à jour en BDD Si besoin
		// storeRepository.save(serviceMapper.convertStoreToStoreEntity(store));

		return store;
	}

	/**
	 * @param storesProductAvailable
	 * @param customer
	 * @return
	 */
	private Map<Store, Integer> calculateDistancesFromStoresToCustomer(List<Store> storesProductAvailable,
			Customer customer) {
		
		Map<Store, Integer> mapDistancesStoreCustomer = new HashMap<Store, Integer>();
		CalculateDistance<Position, Position, Double> distance = calculateDistanceBeetweenTWoPositions();

		storesProductAvailable.forEach(s -> {
			Integer distanceToTravel = distance.calculateDistance(s.getStorePosition(), customer.getCustomerPosition())
					.intValue();
			mapDistancesStoreCustomer.put(s, distanceToTravel);
			s.setDistanceStoreCustomer(distanceToTravel);
		});
		
		return mapDistancesStoreCustomer;
	}

	/**
	 * @return
	 */
	private CalculateDistance<Position, Position, Double> calculateDistanceBeetweenTWoPositions() {
		CalculateDistance<Position, Position, Double> distance = (x1, x2) -> Math
				.sqrt(Math.pow(x1.getX() - x2.getX(), 2) + Math.pow(x1.getY() - x2.getY(), 2));
		return distance;
	}

	/**
	 * @param product
	 * @param store
	 */
	private void updateStockOfStore(Product product, Store store) {
		store.getProducts().forEach(p -> {
			if (p.equals(product)) {
				p.setQuantity(p.getQuantity() - 1);
			}
		});
	}

}
