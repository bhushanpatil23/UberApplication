package com.bhushan.project.uber.uberApp.services.impl;

import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bhushan.project.uber.uberApp.dtos.DriverDto;
import com.bhushan.project.uber.uberApp.dtos.SignupDto;
import com.bhushan.project.uber.uberApp.dtos.UserDto;
import com.bhushan.project.uber.uberApp.entities.User;
import com.bhushan.project.uber.uberApp.entities.enums.Role;
import com.bhushan.project.uber.uberApp.repositories.UserRepository;
import com.bhushan.project.uber.uberApp.services.AuthService;
import com.bhushan.project.uber.uberApp.services.RiderService;
import com.bhushan.project.uber.uberApp.exceptions.RuntimeConflictException;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

	private final ModelMapper modelMapper;
	private final UserRepository userRepository;
	@Autowired
	private final RiderService riderService;
	
	@Override
	public String login(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto signup(SignupDto signupDto) {
		User user = userRepository.findByEmail(signupDto.getEmail()).orElse(null);
		
		
		if(user!=null) {
			throw new RuntimeConflictException("Cannot signup, user already exist with email "+signupDto.getEmail());  
		}
			
		
		User mappedUser = modelMapper.map(signupDto, User.class);
		mappedUser.setRoles(Set.of(Role.RIDER));
		User savedUser = userRepository.save(mappedUser);
		
		// create user related entities
		riderService.createNewRider(savedUser);
		// TODO ADD Wallet related service
		
		return modelMapper.map(savedUser, UserDto.class);
	}

	@Override
	public DriverDto onboardNewDriver(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
