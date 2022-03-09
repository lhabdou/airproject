package com.airproject.domain;

import com.airproject.domain.Customer.CustomerBuilder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@JsonDeserialize(builder = CustomerBuilder.class)
public class Customer {
	
	private String customerId;
	private Position customerPosition;
	
	@JsonPOJOBuilder(withPrefix = "")
	public static class CustomerBuilder {

	}
}
