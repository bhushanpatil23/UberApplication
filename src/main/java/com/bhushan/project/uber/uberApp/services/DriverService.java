package com.bhushan.project.uber.uberApp.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bhushan.project.uber.uberApp.dtos.DriverDto;
import com.bhushan.project.uber.uberApp.dtos.RideDto;
import com.bhushan.project.uber.uberApp.dtos.RiderDto;
import com.bhushan.project.uber.uberApp.entities.Driver;
@Service
public interface DriverService {

	RideDto acceptRide(Long rideRequestId);
	
	RideDto cancelRide(Long rideId);
	
	RideDto startRide(Long rideId,String otp);

	RideDto endRide(Long rideId);
	
	RiderDto rateRider(Long rideId, Integer rating);
	
	DriverDto getMyProfile();
	
	Page<RideDto> getAllMyRides(PageRequest pageRequest);

	Driver getCurrentDriver();
	
	Driver updateDriverAvailability(Driver driver, boolean available) ;
	
	Driver createNewDriver(Driver driver);

}
