package com.bhushan.project.uber.uberApp.dtos;

import java.util.List;

import com.bhushan.project.uber.uberApp.entities.User;
import com.bhushan.project.uber.uberApp.entities.WalletTransaction;

import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class WalletDto {

	private Long id;
	
	private UserDto userDto;
	
	private Double balance;
	
	private List<WalletTransactionDto> transactions;
}
