package com.bhushan.project.uber.uberApp.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bhushan.project.uber.uberApp.dtos.DriverDto;
import com.bhushan.project.uber.uberApp.dtos.RideDto;
import com.bhushan.project.uber.uberApp.dtos.RideRequestDto;
import com.bhushan.project.uber.uberApp.dtos.RiderDto;
import com.bhushan.project.uber.uberApp.entities.Rider;
import com.bhushan.project.uber.uberApp.entities.User;

@Service
public interface RiderService {

	RideRequestDto requestRide(RideRequestDto riderideRequestDto);
	
	RideDto cancelRide(Long rideId);
	
	RideDto startRide(Long rideId);

	RideDto endRide(Long rideId);
	
	DriverDto rateDriver(Long rideId, Integer rating);
	
	RiderDto getMyProfile();
	
	Page<RideDto> getAllMyRides(PageRequest pageRequest);
	
	Rider createNewRider(User user);

	Rider getCurrentRider();
}
