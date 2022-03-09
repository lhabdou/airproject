package com.airproject.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.airproject.domain.Drone;
import com.airproject.domain.Position;
import com.airproject.domain.Product;
import com.airproject.domain.Store;
import com.airproject.entities.DroneEntity;
import com.airproject.entities.PositionEntity;
import com.airproject.entities.ProductEntity;
import com.airproject.entities.StoreEntity;
import com.airproject.entities.StoreProduct;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

	@Mapping(source = "storeEntity.productStores", target = "products")
	Store convertStoreEntityToStore(StoreEntity storeEntity);

	@Mapping(source = "product.productId", target = "productId")
	@Mapping(source = "product.productName", target = "productName")
	Product storeProductToProduct(StoreProduct storeProduct);

	@Mapping(source = "productId", target = "product.productId")
	@Mapping(source = "productName", target = "product.productName")
	StoreProduct productTostoreProduct(Product product);

	List<Store> convertListStoreEntityToListStore(List<StoreEntity> stores);

	@Mappings(@Mapping(source = "store.products", target = "productStores"))
	StoreEntity convertStoreToStoreEntity(Store store);

	List<StoreEntity> convertListStoreToListStoreEntity(List<Store> stores);

	Drone convertDroneEntityToDrone(DroneEntity drone);

	List<Drone> convertListDroneEntityToListDrone(List<DroneEntity> drones);

	DroneEntity convertDroneToDroneEntity(Drone drone);

	Position convertPositionEntityToPosition(PositionEntity positionEntity);

	List<Position> convertListPositionsEntityToListPositions(List<PositionEntity> positions);

	List<PositionEntity> convertListPositionToListPositionEntity(List<Position> position);

	PositionEntity convertPositionToPositionEntity(Position position);

	ProductEntity convertProductToProductEntity(Product product);

	Product convertProductEntityToProduct(ProductEntity productEnt);

}
