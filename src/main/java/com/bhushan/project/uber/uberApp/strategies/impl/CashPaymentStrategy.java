package com.bhushan.project.uber.uberApp.strategies.impl;

import org.springframework.stereotype.Service;

import com.bhushan.project.uber.uberApp.entities.Driver;
import com.bhushan.project.uber.uberApp.entities.Payment;
import com.bhushan.project.uber.uberApp.entities.Wallet;
import com.bhushan.project.uber.uberApp.entities.enums.PaymentStatus;
import com.bhushan.project.uber.uberApp.entities.enums.TransactionMethod;
import com.bhushan.project.uber.uberApp.repositories.PaymentRepository;
import com.bhushan.project.uber.uberApp.services.PaymentService;
import com.bhushan.project.uber.uberApp.services.WalletService;
import com.bhushan.project.uber.uberApp.strategies.PaymentStrategy;

import lombok.RequiredArgsConstructor;

//Rider -> 100
//Driver -> 70 Deduct 30RS from Drivers Wallet

@Service	
@RequiredArgsConstructor
public class CashPaymentStrategy implements PaymentStrategy {

	private final WalletService walletService;
	private final PaymentRepository paymentRepository;
	
	@Override
	public void processPayment(Payment payment) {
		Driver driver = payment.getRide().getDriver();
		double platformCommsion = payment.getAmount()*PLATFORM_COMMISION;
		
		walletService.deductMoneyFromWallet(driver.getUser(), PLATFORM_COMMISION,null,payment.getRide(),TransactionMethod.RIDE);
		
		payment.setPaymentStatus(PaymentStatus.CONFIRMED);
		paymentRepository.save(payment);
		
	}

}
