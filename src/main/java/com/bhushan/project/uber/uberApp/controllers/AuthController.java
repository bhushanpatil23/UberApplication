package com.bhushan.project.uber.uberApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhushan.project.uber.uberApp.dtos.DriverDto;
import com.bhushan.project.uber.uberApp.dtos.OnboardDriverDto;
import com.bhushan.project.uber.uberApp.dtos.SignupDto;
import com.bhushan.project.uber.uberApp.dtos.UserDto;
import com.bhushan.project.uber.uberApp.services.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	
	@Autowired
	private final AuthService authService;
	
	@PostMapping("/signup")
	ResponseEntity<UserDto> signUp(@RequestBody SignupDto signupDto) {
		return new ResponseEntity<>(authService.signup(signupDto), HttpStatus.CREATED);
	}
	
	@PostMapping("/onBoardNewDriver/{userId}")
	ResponseEntity<DriverDto> onBoardNewDriver(@PathVariable Long userId, @RequestBody OnboardDriverDto onboardDriverDto){
		return new ResponseEntity<>(authService.onboardNewDriver(userId,onboardDriverDto.getVehicleId()), HttpStatus.CREATED);
	}
}
