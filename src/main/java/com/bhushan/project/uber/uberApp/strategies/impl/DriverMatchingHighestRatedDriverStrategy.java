package com.bhushan.project.uber.uberApp.strategies.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bhushan.project.uber.uberApp.dtos.RideRequestDto;
import com.bhushan.project.uber.uberApp.entities.Driver;
import com.bhushan.project.uber.uberApp.entities.RideRequest;
import com.bhushan.project.uber.uberApp.strategies.DriverMatchingStrategy;

@Service
public class DriverMatchingHighestRatedDriverStrategy implements DriverMatchingStrategy{

	@Override
	public List<Driver> findMatchingDriver(RideRequest rideRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
