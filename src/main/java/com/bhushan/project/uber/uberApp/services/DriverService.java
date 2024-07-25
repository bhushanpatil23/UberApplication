package com.bhushan.project.uber.uberApp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bhushan.project.uber.uberApp.dtos.DriverDto;
import com.bhushan.project.uber.uberApp.dtos.RideDto;
@Service
public interface DriverService {

	RideDto acceptRide(Long rideId);
	
	RideDto cancelRide(Long rideId);
	
	RideDto startRide(Long rideId);

	RideDto endRide(Long rideId);
	
	RideDto rateRider(Long rideId, Integer rating);
	
	DriverDto getMyProfile();
	
	List<RideDto> getAllMyRides();


}
