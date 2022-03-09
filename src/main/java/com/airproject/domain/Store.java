package com.airproject.domain;

import java.util.List;

import com.airproject.domain.Store.StoreBuilder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
@Getter
@JsonDeserialize(builder = StoreBuilder.class)
public class Store {
	
	private String storeId;
	private Position storePosition;	
	private List<Product> products;
	private Integer distanceStoreCustomer;
	
	@JsonPOJOBuilder(withPrefix = "")
	public static class StoreBuilder {

	}
}
