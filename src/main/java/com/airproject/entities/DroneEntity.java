package com.airproject.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "drones")
public class DroneEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5452563264946247520L;
	@Id
	@Column(name = "drone_id", unique = true)
	private String droneId;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "drone_position_id", referencedColumnName = "id")
	private PositionEntity dronePosition;
	@Column(name = "autonomy")
	private Integer autonomy;

}
