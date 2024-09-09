package com.bhushan.project.uber.uberApp.dtos;

import java.time.LocalDateTime;


import com.bhushan.project.uber.uberApp.entities.enums.PaymentMethod;
import com.bhushan.project.uber.uberApp.entities.enums.RideRequestStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideRequestDto {
	private Long id;
	
	private PointDto pickupLocation;
	private PointDto dropoffLocation;
	private PaymentMethod paymentMethod;

	
	private LocalDateTime requestedTime;

	private RiderDto rider;
	private Double fare;
	 
	private RideRequestStatus rideRequestStatus;
	
}
