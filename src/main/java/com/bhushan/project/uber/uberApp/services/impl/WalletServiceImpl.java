package com.bhushan.project.uber.uberApp.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bhushan.project.uber.uberApp.entities.Ride;
import com.bhushan.project.uber.uberApp.entities.User;
import com.bhushan.project.uber.uberApp.entities.Wallet;
import com.bhushan.project.uber.uberApp.entities.WalletTransaction;
import com.bhushan.project.uber.uberApp.entities.enums.TransactionMethod;
import com.bhushan.project.uber.uberApp.entities.enums.TransactionType;
import com.bhushan.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.bhushan.project.uber.uberApp.repositories.WalletRepository;
import com.bhushan.project.uber.uberApp.services.WalletService;
import com.bhushan.project.uber.uberApp.services.WalletTransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService{

	private final WalletRepository walletRepository;
	private final WalletTransactionService walletTransactionService;
	
	@Override
	@Transactional
	public Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod) {
		Wallet wallet = findByUser(user);
		wallet.setBalance(wallet.getBalance()+amount);
		
		WalletTransaction walletTransaction = WalletTransaction.builder()
				.transactionId(transactionId)
				.ride(ride)
				.wallet(wallet)
				.transactionType(TransactionType.CREDIT)
				.transactionMethod(transactionMethod)
				.amount(amount)
				.build();
		
		walletTransactionService.createNewWalletTransaction(walletTransaction);
		
		return walletRepository.save(wallet);
	}

	@Override
	@Transactional
	public Wallet deductMoneyFromWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod) {
		Wallet wallet = findByUser(user);
		wallet.setBalance(wallet.getBalance()-amount);
		
		WalletTransaction walletTransaction = WalletTransaction.builder()
				.transactionId(transactionId)
				.ride(ride)
				.wallet(wallet)
				.transactionType(TransactionType.DEBIT)
				.transactionMethod(transactionMethod)
				.amount(amount)
				.build();
		
		//walletTransactionService.createNewWalletTransaction(walletTransaction); created problem so added below line
		
		wallet.getTransactions().add(walletTransaction);
		
		return walletRepository.save(wallet);
	}
	
	@Override
	public void withdrawAllMyMoneyFromWallet() { 
		// TODO Auto-generated method stub
		
	}

	@Override
	public Wallet findWalletById(Long walletId) {
		return walletRepository.findById(walletId)
				.orElseThrow(()-> new ResourceNotFoundException("Wallet not found with Id "+walletId));
	}

	@Override
	public Wallet createNewWallet(User user) {
		Wallet wallet = new Wallet();
		wallet.setUser(user);
		return walletRepository.save(wallet);
	}

	@Override
	public Wallet findByUser(User user) {
		return walletRepository.findByUser(user)
				.orElseThrow(()-> new ResourceNotFoundException("User not found with Id "+user.getId()));
	}

	

}
