package com.bhushan.project.uber.uberApp.services;

import com.bhushan.project.uber.uberApp.entities.RideRequest;

public interface RideRequestService {

	RideRequest findRideRequestById(Long rideRequestId);

	void update(RideRequest rideRequest);
}
