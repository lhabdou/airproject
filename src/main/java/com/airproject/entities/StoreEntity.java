package com.airproject.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "stores")
@EqualsAndHashCode(exclude={"storePosition","productStores"})
public class StoreEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5590053258084853715L;
	@Id
	@Column(name="store_id")
	private String storeId;
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "store_position_id", referencedColumnName = "id")
	private PositionEntity storePosition;	
	
    @ManyToMany(mappedBy = "store")
    private Set<StoreProduct> productStores;
}
