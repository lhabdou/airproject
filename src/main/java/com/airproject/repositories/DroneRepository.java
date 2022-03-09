package com.airproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airproject.entities.DroneEntity;

public interface DroneRepository extends JpaRepository<DroneEntity, String> {

}
