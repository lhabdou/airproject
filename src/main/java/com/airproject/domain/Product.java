package com.airproject.domain;

import com.airproject.domain.Product.ProductBuilder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode(exclude = {"quantity", "productName"})
@JsonDeserialize(builder = ProductBuilder.class)
public class Product {

	private String productId;
	private String productName;
	private Integer quantity;

	@JsonPOJOBuilder(withPrefix = "")
	public static class ProductBuilder {

	}

}
