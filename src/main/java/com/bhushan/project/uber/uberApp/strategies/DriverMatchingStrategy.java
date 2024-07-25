package com.bhushan.project.uber.uberApp.strategies;

import java.util.List;

import com.bhushan.project.uber.uberApp.dtos.RideRequestDto;
import com.bhushan.project.uber.uberApp.entities.Driver;
import com.bhushan.project.uber.uberApp.entities.RideRequest;

public interface DriverMatchingStrategy {

	List<Driver> findMatchingDriver(RideRequest rideRequest);
}
