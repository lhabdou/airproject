package com.airproject.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Drone {
	
	private String droneId; 
	private Position dronePosition;
	private Integer autonomy; 
	private Integer distanceParcourue;

}
