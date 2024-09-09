package com.bhushan.project.uber.uberApp.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bhushan.project.uber.uberApp.dtos.RideRequestDto;
import com.bhushan.project.uber.uberApp.entities.Driver;
import com.bhushan.project.uber.uberApp.entities.Ride;
import com.bhushan.project.uber.uberApp.entities.RideRequest;
import com.bhushan.project.uber.uberApp.entities.Rider;
import com.bhushan.project.uber.uberApp.entities.enums.RideStatus;

@Service
public interface RideService {

	Ride getRideById(Long rideId);
	
	
	Ride createNewRide(RideRequest rideRequest, Driver driver);
	
	Ride updateRideStatus(Ride ride, RideStatus rideStatus);
	
	Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest);
	
	Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest);

	
}
