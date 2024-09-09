package com.bhushan.project.uber.uberApp.strategies.impl;

import org.springframework.stereotype.Service;

import com.bhushan.project.uber.uberApp.dtos.RideRequestDto;
import com.bhushan.project.uber.uberApp.entities.RideRequest;
import com.bhushan.project.uber.uberApp.services.DistanceService;
import com.bhushan.project.uber.uberApp.strategies.RideFareCalculationStrategy;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RideFareSurgePricingFareCalculationStrategy implements RideFareCalculationStrategy{

	private final DistanceService distanceService;
	private static final double SURGE_FACTOR = 2;

	@Override
	public double calculateFare(RideRequest rideRequest) {
		double distance = distanceService.calculateDistance(rideRequest.getPickupLocation(), rideRequest.getDropoffLocation());
		return distance*RIDE_FARE_MULTIPLIER*SURGE_FACTOR;
	}

}
