package com.bhushan.project.uber.uberApp.strategies;

import com.bhushan.project.uber.uberApp.entities.Payment;

public interface PaymentStrategy {

	Double PLATFORM_COMMISION = 0.3;
	
	void processPayment(Payment payment);
	
}
