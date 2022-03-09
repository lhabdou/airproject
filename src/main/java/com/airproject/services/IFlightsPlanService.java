package com.airproject.services;

import java.util.List;

import com.airproject.domain.FlightPlan;
import com.airproject.domain.Order;

public interface IFlightsPlanService {

	List<FlightPlan> getFlightsPlan(List<Order> orders);

}
