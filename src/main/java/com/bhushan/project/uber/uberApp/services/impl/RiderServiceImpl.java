package com.bhushan.project.uber.uberApp.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bhushan.project.uber.uberApp.dtos.DriverDto;
import com.bhushan.project.uber.uberApp.dtos.RideDto;
import com.bhushan.project.uber.uberApp.dtos.RideRequestDto;
import com.bhushan.project.uber.uberApp.dtos.RiderDto;
import com.bhushan.project.uber.uberApp.entities.Driver;
import com.bhushan.project.uber.uberApp.entities.Ride;
import com.bhushan.project.uber.uberApp.entities.RideRequest;
import com.bhushan.project.uber.uberApp.entities.Rider;
import com.bhushan.project.uber.uberApp.entities.User;
import com.bhushan.project.uber.uberApp.entities.enums.RideRequestStatus;
import com.bhushan.project.uber.uberApp.entities.enums.RideStatus;
import com.bhushan.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.bhushan.project.uber.uberApp.repositories.RideRequestRepository;
import com.bhushan.project.uber.uberApp.repositories.RiderRepository;
import com.bhushan.project.uber.uberApp.services.DriverService;
import com.bhushan.project.uber.uberApp.services.RatingService;
import com.bhushan.project.uber.uberApp.services.RideService;
import com.bhushan.project.uber.uberApp.services.RiderService;
import com.bhushan.project.uber.uberApp.strategies.RideStrategyManager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImpl implements RiderService{

	private final ModelMapper modelMapper;
	private final RideStrategyManager rideStrategyManager;
	private final RideRequestRepository rideRequestRepository;
	private final RiderRepository riderRepository;
	private final RideService rideService;
	private final DriverService driverService;
	private final RatingService ratingService;
	
	@Override
	@Transactional
	public RideRequestDto requestRide(RideRequestDto riderideRequestDto) {
		Rider rider = getCurrentRider();
		RideRequest rideRequest = modelMapper.map(riderideRequestDto, RideRequest.class); 	
		rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
		rideRequest.setRider(rider);
		
		Double fare = rideStrategyManager.rideFareCalculationStrategy().calculateFare(rideRequest);
		rideRequest.setFare(fare);
		
	    RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);

		
	    List<Driver> drivers = rideStrategyManager.driverMatchingStrategy(rider.getRating()).findMatchingDriver(rideRequest);
	    
	    //TODO : send the notification to all the drivers about this rideRequest
				
		return modelMapper.map(savedRideRequest, RideRequestDto.class);
	}

	@Override
	public RideDto cancelRide(Long rideId) {
		Rider rider = getCurrentRider();
		Ride ride = rideService.getRideById(rideId);
		
		if(!rider.equals(ride.getRider())) {
			throw new RuntimeException("rider does not won thid ride with Id "+rideId);
		}
		
		if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
			throw new RuntimeException("Ride status is not CONFIRMED hence cannot be started, status "+ride.getRideStatus());
		}
		
		Ride savedRide = rideService.updateRideStatus(ride, RideStatus.CANCELLED);
		driverService.updateDriverAvailability(ride.getDriver(), true);
		
		return modelMapper.map(savedRide, RideDto.class);
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
		Ride ride = rideService.getRideById(rideId);
		Rider rider = getCurrentRider();
		
		if(!rider.equals(ride.getRider())) {
			throw new RuntimeException("Rider is not the owner of this ride with ID: "+rideId);
		}
		if(!ride.getRideStatus().equals(RideStatus.ENDED)) {
			throw new RuntimeException("Ride status is not ENDED hence cannot start rating, status: "+ride.getRideStatus());
		}
		
		return ratingService.rateDriver(ride, rating);
	}

	@Override
	public RiderDto getMyProfile() {
		Rider currentRider = getCurrentRider();
		return modelMapper.map(currentRider, RiderDto.class);
	}

	@Override
	public Page<RideDto> getAllMyRides(PageRequest pageRequest) {
		Rider currentRider = getCurrentRider();
		return rideService.getAllRidesOfRider(currentRider, pageRequest).map(
				ride -> modelMapper.map(ride, RideDto.class)
		);
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

	@Override
	public Rider getCurrentRider() {
		// TODO implement Spring Security
		return riderRepository.findById(1L).orElseThrow(()->new ResourceNotFoundException(
				"Rider not found with Id: "+1
		));
	}

}
