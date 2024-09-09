package com.bhushan.project.uber.uberApp.strategies.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bhushan.project.uber.uberApp.entities.Driver;
import com.bhushan.project.uber.uberApp.entities.Payment;
import com.bhushan.project.uber.uberApp.entities.Rider;
import com.bhushan.project.uber.uberApp.entities.enums.PaymentStatus;
import com.bhushan.project.uber.uberApp.entities.enums.TransactionMethod;
import com.bhushan.project.uber.uberApp.repositories.PaymentRepository;
import com.bhushan.project.uber.uberApp.services.PaymentService;
import com.bhushan.project.uber.uberApp.services.WalletService;
import com.bhushan.project.uber.uberApp.strategies.PaymentStrategy;

import lombok.RequiredArgsConstructor;


/*Rider had X , Driver had Y
Ride cost is Z, commsion = 0.3*Z
Rider -> X - Z
Driver -> Y + Z - commsion


*/
@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {

	private final WalletService walletService;
	private final PaymentRepository paymentRepository;
	
	@Override
	@Transactional
	public void processPayment(Payment payment) {
		Driver driver = payment.getRide().getDriver();
		Rider rider = payment.getRide().getRider();
		
		walletService.deductMoneyFromWallet(rider.getUser(), payment.getAmount(), null, payment.getRide(), TransactionMethod.RIDE);
		
		double drivercut = payment.getAmount() * (1-PLATFORM_COMMISION);
		
		walletService.addMoneyToWallet(driver.getUser(), drivercut, null, payment.getRide(), TransactionMethod.RIDE);
		
		payment.setPaymentStatus(PaymentStatus.CONFIRMED);
		paymentRepository.save(payment);
	}

}
