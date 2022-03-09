package com.airproject.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "customers")
public class CustomerEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5717301429022541250L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="customer_id", unique = true)
	private String storeId;
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_position_id", referencedColumnName = "id")
	private PositionEntity customerPosition;
}
