package com.airproject.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Entity
@Data
@Table(name = "stores_products", uniqueConstraints = @UniqueConstraint(columnNames = { "store_id", "product_id" }))
public class StoreProduct implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2236499774974369102L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "store_id")
	private StoreEntity store;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductEntity product;

	@Column(name = "quantity")
	private Integer quantity;

}
