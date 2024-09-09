package com.bhushan.project.uber.uberApp.strategies.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bhushan.project.uber.uberApp.entities.Driver;
import com.bhushan.project.uber.uberApp.entities.RideRequest;
import com.bhushan.project.uber.uberApp.repositories.DriverRepository;
import com.bhushan.project.uber.uberApp.strategies.DriverMatchingStrategy;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DriverMatchingHighestRatedDriverStrategy implements DriverMatchingStrategy{

	private final DriverRepository driverRepository;
	
	@Override
	public List<Driver> findMatchingDriver(RideRequest rideRequest) {
		
		return driverRepository.findTenNearbyTopRatedDrivers(rideRequest.getPickupLocation());
	}

	

}
