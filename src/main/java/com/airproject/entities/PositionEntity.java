package com.airproject.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name= "positions")
@Data
public class PositionEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8960758316916042673L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	@Column(name="x", nullable = false)
	private Integer x;
	@Column(name="y", nullable = false)
	private Integer y;
	
	@OneToOne(mappedBy = "storePosition")
    private StoreEntity store;
	
	@OneToOne(mappedBy = "dronePosition")
    private DroneEntity drone;
	
	@OneToOne(mappedBy = "customerPosition")
    private CustomerEntity customer;
	
}
