package com.bhushan.project.uber.uberApp.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bhushan.project.uber.uberApp.dtos.RideRequestDto;
import com.bhushan.project.uber.uberApp.entities.Driver;
import com.bhushan.project.uber.uberApp.entities.Ride;
import com.bhushan.project.uber.uberApp.entities.enums.RideStatus;

@Service
public interface RideService {

	Ride getRideById(Long rideId);
	
	void matchWithDrivers(RideRequestDto rideRequestDto);
	
	Ride createNewRide(RideRequestDto rideRequestDto, Driver driver);
	
	Ride updateRideStatus(Long rideId, RideStatus rideStatus);
	
	Page<Ride> getAllRidesOfRider(Long riderId, PageRequest pageRequest);
	
	Page<Ride> getAllRidesOfDriver(Long driverId, PageRequest pageRequest);

	
}
