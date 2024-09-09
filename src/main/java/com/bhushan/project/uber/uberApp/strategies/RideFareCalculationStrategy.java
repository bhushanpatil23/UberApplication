package com.bhushan.project.uber.uberApp.strategies;

import org.springframework.stereotype.Service;

import com.bhushan.project.uber.uberApp.entities.RideRequest;

@Service
public interface RideFareCalculationStrategy {
	
	static final double RIDE_FARE_MULTIPLIER = 10;
	double calculateFare(RideRequest rideRequest);
}
