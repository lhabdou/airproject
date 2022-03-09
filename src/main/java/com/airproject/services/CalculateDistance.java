package com.airproject.services;

import com.airproject.domain.Position;

@FunctionalInterface
public interface CalculateDistance <P1, P2, D> {
	
	Double calculateDistance(Position p1,Position p2);

}
