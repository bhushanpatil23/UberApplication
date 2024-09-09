package com.bhushan.project.uber.uberApp.strategies;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bhushan.project.uber.uberApp.entities.Driver;
import com.bhushan.project.uber.uberApp.entities.RideRequest;

@Service
public interface DriverMatchingStrategy {

	List<Driver> findMatchingDriver(RideRequest rideRequest);
}
