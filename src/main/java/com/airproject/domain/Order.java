package com.airproject.domain;

import java.util.List;

import com.airproject.domain.Order.OrderBuilder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@JsonDeserialize(builder = OrderBuilder.class)
public class Order {

	private String orderId;

	private Customer customer;

	private List<Product> products;

	@JsonPOJOBuilder(withPrefix = "")
	public static class OrderBuilder {

	}

}
