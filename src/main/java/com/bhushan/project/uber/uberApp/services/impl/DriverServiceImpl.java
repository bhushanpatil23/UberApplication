package com.bhushan.project.uber.uberApp.services.impl;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bhushan.project.uber.uberApp.dtos.DriverDto;
import com.bhushan.project.uber.uberApp.dtos.RideDto;
import com.bhushan.project.uber.uberApp.dtos.RiderDto;
import com.bhushan.project.uber.uberApp.entities.Driver;
import com.bhushan.project.uber.uberApp.entities.Ride;
import com.bhushan.project.uber.uberApp.entities.RideRequest;
import com.bhushan.project.uber.uberApp.entities.enums.RideRequestStatus;
import com.bhushan.project.uber.uberApp.entities.enums.RideStatus;
import com.bhushan.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.bhushan.project.uber.uberApp.repositories.DriverRepository;
import com.bhushan.project.uber.uberApp.services.DriverService;
import com.bhushan.project.uber.uberApp.services.PaymentService;
import com.bhushan.project.uber.uberApp.services.RatingService;
import com.bhushan.project.uber.uberApp.services.RideRequestService;
import com.bhushan.project.uber.uberApp.services.RideService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService{

	private final RideRequestService rideRequestService;
	private final DriverRepository driverRepository;
	private final RideService rideService;
	private final ModelMapper modelMapper;
	private final PaymentService paymentService;
	private final RatingService ratingService;
	
	@Override
	@Transactional
	public RideDto acceptRide(Long rideRequestId) {
		RideRequest rideRequest = rideRequestService.findRideRequestById(rideRequestId);
		System.out.println("Accept RIde Service Called ....");

		if(!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)) {
			throw new RuntimeException("RideRequest cannot be accepted, status is "+rideRequest.getRideRequestStatus());
		}
		
		Driver currentDriver = getCurrentDriver();
		if(!currentDriver.getAvailable()) {
			throw new RuntimeException("Driver cannot accept ride due to unavailability");
		}
		
		Driver savedDriver = updateDriverAvailability(currentDriver, false);
		Ride ride = rideService.createNewRide(rideRequest, savedDriver);
		
		return modelMapper.map(ride,RideDto.class);
	}

	@Override
	public RideDto cancelRide(Long rideId) {
		Ride ride = rideService.getRideById(rideId);
		
		Driver driver = getCurrentDriver();
		
		if(!driver.equals(ride.getDriver())) {
			throw new RuntimeException("Driver cannot start a ride as he has not accepted it earlier");
		}
		if(ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
			throw new RuntimeException("Ride cannot be cancelled, invalid status: "+ride.getRideStatus());
		}
		
		rideService.updateRideStatus(ride, RideStatus.CANCELLED);
		updateDriverAvailability(driver, true);
		return modelMapper.map(ride, RideDto.class);
	}

	@Override
	public RideDto startRide(Long rideId,String otp) {
		
		Ride ride = rideService.getRideById(rideId);
		Driver driver = getCurrentDriver();
		
		if(!driver.equals(ride.getDriver())) {
			throw new RuntimeException("Driver cannot start a ride as he has not accepted it earlier");
		}
		if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
			throw new RuntimeException("Ride status is not CONFIRMED hence cannot be started, status "+ride.getRideStatus());
		}
		if(!otp.equals(ride.getOtp())) {
			throw new RuntimeException("otp is not valid, otp "+otp);
		}
		
		ride.setStartedAt(LocalDateTime.now());
		Ride savedRide = rideService.updateRideStatus(ride, RideStatus.CONFIRMED);
		
		paymentService.createNewPayment(savedRide);
		
		ratingService.createNewRating(savedRide);
		
		return modelMapper.map(savedRide, RideDto.class);
	}

	@Override
	@Transactional
	public RideDto endRide(Long rideId) {
		Ride ride = rideService.getRideById(rideId);
		Driver driver = getCurrentDriver();
		
		if(!driver.equals(ride.getDriver())) {
			throw new RuntimeException("Driver cannot start a ride as he has not accepted it earlier");
		}
//		if(!ride.getRideStatus().equals(RideStatus.ONGOING)) {
//			throw new RuntimeException("Ride status is not ONGOING hence cannot be started, status "+ride.getRideStatus());
//		}
		
		ride.setEndedAt(LocalDateTime.now());
		
		Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ENDED);
		updateDriverAvailability(driver, true);
		
		paymentService.processPayment(ride);
		return modelMapper.map(savedRide,RideDto.class);
		
	}

	@Override
	public RiderDto rateRider(Long rideId, Integer rating) {
		Ride ride = rideService.getRideById(rideId);
		Driver driver = getCurrentDriver();
		
		if(!driver.equals(ride.getDriver())) {
			throw new RuntimeException("Driver is not the owner of this ride with ID: "+rideId);
		}
		if(!ride.getRideStatus().equals(RideStatus.ENDED)) {
			throw new RuntimeException("Ride status is not ENDED hence cannot start rating, status: "+ride.getRideStatus());
		}
		
		return ratingService.rateRider(ride, rating);
	}

	@Override
	public DriverDto getMyProfile() {
		Driver currentDriver = getCurrentDriver();
		return modelMapper.map(currentDriver, DriverDto.class);
	}

	@Override
	public Page<RideDto> getAllMyRides(PageRequest pageRequest) {
		Driver currentDriver = getCurrentDriver();
		return rideService.getAllRidesOfDriver(currentDriver, pageRequest).map(
				ride -> modelMapper.map(ride, RideDto.class)
		);
	}

	@Override
	public Driver getCurrentDriver() {
		
		return driverRepository.findById(1L).orElseThrow(()-> new ResourceNotFoundException("Driver not found with Id "+2L));
	}

	@Override
	public Driver updateDriverAvailability(Driver driver, boolean available) {
		driver.setAvailable(available);
		return driverRepository.save(driver);
	}

	

	@Override
	public Driver createNewDriver(Driver driver) {
		return driverRepository.save(driver);
	}

}
