package com.airproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airproject.domain.FlightPlan;
import com.airproject.orders.GenerateOrders;
import com.airproject.services.IFlightsPlanService;

@RestController
public class FlightsPlanController {

	@Autowired
	public IFlightsPlanService flightPlan;
	
	@Autowired
	public GenerateOrders generateOrders;
	
	@GetMapping("/flights/plan")
	public ResponseEntity<List<FlightPlan>> getFlightsPlan() {

		return ResponseEntity.ok(flightPlan.getFlightsPlan(generateOrders.generateOrders()));
	}

}
