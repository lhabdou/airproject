package com.airproject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.airproject.entities.StoreEntity;

public interface StoreRepository extends JpaRepository<StoreEntity, String> {

	@Query("select distinct s from StoreEntity s INNER JOIN s.storePosition " + "INNER JOIN s.productStores ")
	List<StoreEntity> findAllStoresWithProducts();

}