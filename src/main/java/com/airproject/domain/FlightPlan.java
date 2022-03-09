package com.airproject.domain;

import com.airproject.domain.FlightPlan.FlightPlanBuilder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = FlightPlanBuilder.class)
@ToString
public class FlightPlan {
	
	private String droneId;
	
	private String storeId; 
	
	private String productId;
	
	private String CustomerId;
	
	@JsonPOJOBuilder(withPrefix = "")
	public static class FlightPlanBuilder {

	}

}
