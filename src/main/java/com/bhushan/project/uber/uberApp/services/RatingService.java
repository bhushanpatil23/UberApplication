package com.bhushan.project.uber.uberApp.services;

import com.bhushan.project.uber.uberApp.dtos.DriverDto;
import com.bhushan.project.uber.uberApp.dtos.RiderDto;
import com.bhushan.project.uber.uberApp.entities.Ride;

public interface RatingService {

	DriverDto rateDriver(Ride ride, Integer rating);
	RiderDto rateRider(Ride ride, Integer rating);

	void createNewRating(Ride ride);
}
