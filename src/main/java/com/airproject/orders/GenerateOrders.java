package com.airproject.orders;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.airproject.domain.Customer;
import com.airproject.domain.Order;
import com.airproject.domain.Position;
import com.airproject.domain.Product;

/**
 * Component permettant de simuler les commandes
 * 
 * @author asoilihi
 *
 */
@Component
public class GenerateOrders {

	public GenerateOrders() {
		super();
	}

	public List<Order> generateOrders() {
		// Commande du client Cus1
		Position positionCus_1 = Position.builder().x(5).y(8).build();
		Customer cus_1 = Customer.builder().customerId("CUS-1").customerPosition(positionCus_1).build();
	
		Product product1 = Product.builder().productId("LMFRPRD-1").productName("Shovel").quantity(5).build();
		Product product2 = Product.builder().productId("LMFRPRD-2").productName("Pickaxe").quantity(1).build();
		Product product22 = Product.builder().productId("LMFRPRD-3").productName("Rake").quantity(1).build();
	
		List<Product> listProducts_Cus_1 = Arrays.asList(product1, product2, product22);
	
		Order order1 = Order.builder().orderId("LMFRORDER-1").customer(cus_1).products(listProducts_Cus_1).build();
	
		// Commande du client Cus2
	
		Position positionCus_2 = Position.builder().x(20).y(20).build();
		Customer cus_2 = Customer.builder().customerId("CUS-2").customerPosition(positionCus_2).build();
	
		Product product3 = Product.builder().productId("LMFRPRD-2").productName("Pickaxe").quantity(1).build();
		Product product4 = Product.builder().productId("LMFRPRD-3").productName("Rake").quantity(1).build();
	
		List<Product> listProducts_Cus_2 = Arrays.asList(product3, product4);
	
		Order order2 = Order.builder().orderId("LMFRORDER-2").customer(cus_2).products(listProducts_Cus_2).build();
	
		// Commande du client Cus3
		Position positionCus_3 = Position.builder().x(12).y(3).build();
		Customer cus_3 = Customer.builder().customerId("CUS-3").customerPosition(positionCus_3).build();
	
		Product product5 = Product.builder().productId("LMFRPRD-3").productName("Rake").quantity(1).build();
		Product product6 = Product.builder().productId("LMFRPRD-1").productName("Shovel").quantity(5).build();
	
		List<Product> listProducts_Cus_3 = Arrays.asList(product5, product6);
	
		Order order3 = Order.builder().orderId("LMFRORDER-3").customer(cus_3).products(listProducts_Cus_3).build();
	
		return Arrays.asList(order1, order2, order3);
	
	}

}