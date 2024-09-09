package com.bhushan.project.uber.uberApp.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bhushan.project.uber.uberApp.dtos.DriverDto;
import com.bhushan.project.uber.uberApp.dtos.RatingDto;
import com.bhushan.project.uber.uberApp.dtos.RideDto;
import com.bhushan.project.uber.uberApp.dtos.RideStartDto;
import com.bhushan.project.uber.uberApp.dtos.RiderDto;
import com.bhushan.project.uber.uberApp.services.DriverService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/drivers")
public class DriverController {

	private final DriverService driverService;
	
	@PostMapping("/acceptRide/{rideRequestId}")
	public ResponseEntity<RideDto> acceptRide(@PathVariable Long rideRequestId){
		System.out.println("Accept RIde API Called ....");
		return ResponseEntity.ok(driverService.acceptRide(rideRequestId));
	}
	
	@PostMapping("/startRide/{rideRequestId}")
	public ResponseEntity<RideDto> startRide(@PathVariable Long rideRequestId,
												@RequestBody RideStartDto rideStartDto){
		return ResponseEntity.ok(driverService.startRide(rideRequestId,rideStartDto.getOtp()));
	}
	
	@PostMapping("/endRide/{rideId}")
	public ResponseEntity<RideDto> startRide(@PathVariable Long rideId){
		return ResponseEntity.ok(driverService.endRide(rideId));
	}
	
	

	@PostMapping("/cancelRide")
	public ResponseEntity<RideDto> cancelRide(@PathVariable Long rideId){
		return ResponseEntity.ok(driverService.cancelRide(rideId));
	}
	
	@PostMapping("/rateRider")
	public ResponseEntity<RiderDto> rateRider(@RequestBody RatingDto ratingDto){
		return ResponseEntity.ok(driverService.rateRider(ratingDto.getRideId(), ratingDto.getRating()));
	}
	
	@GetMapping("/getMyProfile")
	public ResponseEntity<DriverDto> getMyProfile(){
	 return ResponseEntity.ok(driverService.getMyProfile());	
	}
	
	@GetMapping("/getMyRides")
	public ResponseEntity<Page<RideDto>> getAllMyRides(@RequestParam(defaultValue = "0") Integer pageOffset,
														@RequestParam(defaultValue = "10",required = false)Integer pageSize ){
		PageRequest pageRequest = PageRequest.of(pageOffset, pageSize,
				Sort.by(Sort.Direction.DESC, "createdTime","id"));
		return ResponseEntity.ok(driverService.getAllMyRides(pageRequest));
	}
	
	@PostMapping("/rateRider/{rideId}/{rating}")
	public ResponseEntity<RiderDto> rateRider(@PathVariable Long rideId, @PathVariable Integer rating){
		return ResponseEntity.ok(driverService.rateRider(rideId, rating));
	}
}
