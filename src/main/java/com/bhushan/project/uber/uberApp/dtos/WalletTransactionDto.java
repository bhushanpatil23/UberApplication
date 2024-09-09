package com.bhushan.project.uber.uberApp.dtos;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.bhushan.project.uber.uberApp.entities.Ride;
import com.bhushan.project.uber.uberApp.entities.Wallet;
import com.bhushan.project.uber.uberApp.entities.enums.TransactionMethod;
import com.bhushan.project.uber.uberApp.entities.enums.TransactionType;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WalletTransactionDto {

	private Long id;
	
	private Double amount;
	
	private TransactionType transactionType;
	
	private TransactionMethod transactionMethod;
	
	private RideDto rideDto;
	
	private String transactionId;
	
	private WalletDto walletDto;
	
	private LocalDateTime timestamp;
}
