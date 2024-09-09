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
import com.bhushan.project.uber.uberApp.dtos.RideRequestDto;
import com.bhushan.project.uber.uberApp.dtos.RiderDto;
import com.bhushan.project.uber.uberApp.services.RiderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/riders")
@RequiredArgsConstructor
public class RiderController {

	private final RiderService riderService;
	
	@PostMapping("/requestRide")
	public ResponseEntity<RideRequestDto> requestRide(@RequestBody RideRequestDto rideRequestDto){
		return ResponseEntity.ok(riderService.requestRide(rideRequestDto));
	}
	
	@PostMapping("/cancelRide")
	public ResponseEntity<RideDto> cancelRide(@PathVariable Long rideId){
		return ResponseEntity.ok(riderService.cancelRide(rideId));
	}
	
	@PostMapping("/rateDriver")
	public ResponseEntity<DriverDto> rateDriver(@RequestBody RatingDto ratingDto){
		return ResponseEntity.ok(riderService.rateDriver(ratingDto.getRideId(), ratingDto.getRating()));
	}
	
	@GetMapping("/getMyProfile")
	public ResponseEntity<RiderDto> getMyProfile(){
	 return ResponseEntity.ok(riderService.getMyProfile());	
	}
	
	@GetMapping("/getMyRides")
	public ResponseEntity<Page<RideDto>> getAllMyRides(@RequestParam(defaultValue = "0") Integer pageOffset,
														@RequestParam(defaultValue = "10",required = false)Integer pageSize ){
		PageRequest pageRequest = PageRequest.of(pageOffset, pageSize,
				Sort.by(Sort.Direction.DESC, "createdTime","id"));
		return ResponseEntity.ok(riderService.getAllMyRides(pageRequest));
	}
	
	@PostMapping("/rateDriver/{rideId}/{rating}")
	public ResponseEntity<DriverDto> rateDriver(@PathVariable Long rideId, @PathVariable Integer rating){
		return ResponseEntity.ok(riderService.rateDriver(rideId, rating));
	}
}
