package com.bhushan.project.uber.uberApp.services;

import com.bhushan.project.uber.uberApp.entities.Payment;
import com.bhushan.project.uber.uberApp.entities.Ride;
import com.bhushan.project.uber.uberApp.entities.enums.PaymentStatus;

public interface PaymentService {

	void processPayment(Ride ride);
	
	Payment createNewPayment(Ride ride);
	
	void upadtePaymentStatus(Payment payment, PaymentStatus paymentStatus);
	
}
