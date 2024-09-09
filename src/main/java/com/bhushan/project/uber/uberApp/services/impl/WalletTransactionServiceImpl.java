package com.bhushan.project.uber.uberApp.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.bhushan.project.uber.uberApp.dtos.WalletTransactionDto;
import com.bhushan.project.uber.uberApp.entities.WalletTransaction;
import com.bhushan.project.uber.uberApp.repositories.WalletTransactionRepository;
import com.bhushan.project.uber.uberApp.services.WalletTransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService{

	private final WalletTransactionRepository walletTransactionRepository;
	private final ModelMapper modelMapper ;
	
	@Override
	public void createNewWalletTransaction(WalletTransaction walletTransaction) {
		walletTransactionRepository.save(walletTransaction);
	}

	

}
