package com.bhushan.project.uber.uberApp.services;

import com.bhushan.project.uber.uberApp.dtos.WalletTransactionDto;
import com.bhushan.project.uber.uberApp.entities.WalletTransaction;

public interface WalletTransactionService {

	void createNewWalletTransaction(WalletTransaction wallletTransaction);
	
}
