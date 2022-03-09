package com.airproject.domain;

import com.airproject.domain.Position.PositionBuilder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Setter;
import lombok.Value;

@Value
@Builder
@Setter
@JsonDeserialize(builder = PositionBuilder.class)
public class Position {
	
	private Integer x;
	private Integer y;
	
	@JsonPOJOBuilder(withPrefix = "")
	public static class PositionBuilder {

	}
	
}
