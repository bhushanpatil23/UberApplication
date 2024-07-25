package com.bhushan.project.uber.uberApp.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.bhushan.project.uber.uberApp.entities.enums.TransactionMethod;
import com.bhushan.project.uber.uberApp.entities.enums.TransactionType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class WalletTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double amount;
	
	private TransactionType transactionType;
	
	private TransactionMethod transactionMethod;
	
	@OneToOne
	private Ride ride;
	
	private String transactionId;
	
	@ManyToOne
	private Wallet wallet;
	
	@CreationTimestamp
	private LocalDateTime timestamp;
	
}
