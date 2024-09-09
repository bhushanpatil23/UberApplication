package com.bhushan.project.uber.uberApp.services;

import org.springframework.stereotype.Service;

import com.bhushan.project.uber.uberApp.dtos.DriverDto;
import com.bhushan.project.uber.uberApp.dtos.SignupDto;
import com.bhushan.project.uber.uberApp.dtos.UserDto;

@Service
public interface AuthService {

	String login(String email, String password);
	
	UserDto signup(SignupDto signupDto);
	
	DriverDto onboardNewDriver(Long userId, String vehicleId);
}
