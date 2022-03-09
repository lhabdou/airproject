package com.airproject.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class ProductEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5932377027638902536L;

	@Id
	@Column(name = "product_id", unique = true)
	private String productId;
	@Column(name = "product_name")
	private String productName;
}
