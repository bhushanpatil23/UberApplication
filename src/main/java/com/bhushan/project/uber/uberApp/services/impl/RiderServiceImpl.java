package com.bhushan.project.uber.uberApp.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.bhushan.project.uber.uberApp.dtos.DriverDto;
import com.bhushan.project.uber.uberApp.dtos.RideDto;
import com.bhushan.project.uber.uberApp.dtos.RideRequestDto;
import com.bhushan.project.uber.uberApp.dtos.RiderDto;
import com.bhushan.project.uber.uberApp.entities.RideRequest;
import com.bhushan.project.uber.uberApp.entities.Rider;
import com.bhushan.project.uber.uberApp.entities.User;
import com.bhushan.project.uber.uberApp.entities.enums.RideRequestStatus;
import com.bhushan.project.uber.uberApp.repositories.RideRepository;
import com.bhushan.project.uber.uberApp.repositories.RideRequestRepository;
import com.bhushan.project.uber.uberApp.repositories.RiderRepository;
import com.bhushan.project.uber.uberApp.services.RiderService;
import com.bhushan.project.uber.uberApp.strategies.DriverMatchingStrategy;
import com.bhushan.project.uber.uberApp.strategies.RideFareCalculationStrategy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImpl implements RiderService{

	private final ModelMapper modelMapper;
	private final RideFareCalculationStrategy rideFareCalculationStrategy;
	private final DriverMatchingStrategy driverMatchingStrategy;
	private final RideRequestRepository rideRequestRepository;
	private final RiderRepository riderRepository;
	
	@Override
	public RideRequestDto requestRide(RideRequestDto riderideRequestDto) {
		RideRequest rideRequest = modelMapper.map(riderideRequestDto, RideRequest.class); 	
		rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);

		Double fare = rideFareCalculationStrategy.calculateFare(rideRequest);
		rideRequest.setFare(fare);
		
	    RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);

		
	    driverMatchingStrategy.findMatchingDriver(rideRequest);
				
		return modelMapper.map(savedRideRequest, RideRequestDto.class);
	}

	@Override
	public RideDto cancelRide(Long rideId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RideDto startRide(Long rideId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RideDto endRide(Long rideId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DriverDto rateDriver(Long rideId, Integer rating) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RiderDto getMyProfile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RideDto> getAllMyRides() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rider createNewRider(User user) {
		
		Rider rider = Rider
				.builder()
				.user(user)
				.rating(0.0)
				.build();
		
		return riderRepository.save(rider);
	}

}
