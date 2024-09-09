package com.bhushan.project.uber.uberApp.services.impl;

import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bhushan.project.uber.uberApp.dtos.DriverDto;
import com.bhushan.project.uber.uberApp.dtos.SignupDto;
import com.bhushan.project.uber.uberApp.dtos.UserDto;
import com.bhushan.project.uber.uberApp.entities.Driver;
import com.bhushan.project.uber.uberApp.entities.User;
import com.bhushan.project.uber.uberApp.entities.enums.Role;
import com.bhushan.project.uber.uberApp.repositories.UserRepository;
import com.bhushan.project.uber.uberApp.services.AuthService;
import com.bhushan.project.uber.uberApp.services.DriverService;
import com.bhushan.project.uber.uberApp.services.RiderService;
import com.bhushan.project.uber.uberApp.services.WalletService;
import com.bhushan.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.bhushan.project.uber.uberApp.exceptions.RuntimeConflictException;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

	private final ModelMapper modelMapper;
	private final UserRepository userRepository;
	private final RiderService riderService;
	private final WalletService walletService;
	private final DriverService driverService;
	
	@Override
	public String login(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public UserDto signup(SignupDto signupDto) {
		User user = userRepository.findByEmail(signupDto.getEmail()).orElse(null);
		
		
		if(user!=null) {
			throw new RuntimeConflictException("Cannot signup, user with email "+signupDto.getEmail()+" already exist");  
		}
			
		
		User mappedUser = modelMapper.map(signupDto, User.class);
		mappedUser.setRoles(Set.of(Role.RIDER));
		User savedUser = userRepository.save(mappedUser);
		
		// create user related entities
		riderService.createNewRider(savedUser);
		walletService.createNewWallet(savedUser);
		
		return modelMapper.map(savedUser, UserDto.class);
	}

	@Override
	public DriverDto onboardNewDriver(Long userId, String vehicleId) {
		User user = userRepository.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User not found with id "+userId));
		
		if(user.getRoles().contains(Role.DRIVER)) {
			throw new RuntimeConflictException("User with id "+userId+" ia already DRIVER");
		}
		
		Driver createdDriver = Driver.builder()
				.user(user)
				.rating(0.0)
				.vehicleId(vehicleId)
				.available(true)
				.build();
		user.getRoles().add(Role.DRIVER); 
		userRepository.save(user);
		
		Driver savedDriver = driverService.createNewDriver(createdDriver);
		return modelMapper.map(savedDriver, DriverDto.class);
	}

}
