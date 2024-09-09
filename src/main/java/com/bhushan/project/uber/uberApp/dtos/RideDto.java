package com.bhushan.project.uber.uberApp.dtos;

import java.time.LocalDateTime;

import org.locationtech.jts.geom.Point;

import com.bhushan.project.uber.uberApp.entities.enums.PaymentMethod;
import com.bhushan.project.uber.uberApp.entities.enums.RideStatus;

import lombok.Data;


@Data
public class RideDto {

	private Long id;
	
	private PointDto pickupLocation;
	
	private PointDto dropoffLocation;
	
	private LocalDateTime createdTime;

	private RiderDto rider;
	
	private DriverDto driver;
	
	private PaymentMethod paymentMethod;
	
	private RideStatus rideStatus;
	
	private String otp;
	
	private Double fare;
	
	private LocalDateTime startedAt;
	private LocalDateTime endedAt;
}
