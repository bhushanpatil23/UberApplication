package com.bhushan.project.uber.uberApp.services.impl;

import org.springframework.stereotype.Service;

import com.bhushan.project.uber.uberApp.entities.RideRequest;
import com.bhushan.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.bhushan.project.uber.uberApp.repositories.RideRequestRepository;
import com.bhushan.project.uber.uberApp.services.RideRequestService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService {

	private final RideRequestRepository rideRequestRepository;
	
	@Override
	public RideRequest findRideRequestById(Long rideRequestId) {
		return rideRequestRepository.findById(rideRequestId)
				 .orElseThrow(()-> new ResourceNotFoundException("RideRequest not found with ID: "+rideRequestId));
	}

	@Override
	public void update(RideRequest rideRequest) {
		rideRequestRepository.findById(rideRequest.getId())
				.orElseThrow(()->new ResourceNotFoundException("RideRequest not found with Id "+rideRequest.getId()));
		rideRequestRepository.save(rideRequest);
	}

}
