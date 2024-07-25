package com.bhushan.project.uber.uberApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	UserDto signUup(@RequestBody SignupDto signupDto) {
		return authService.signup(signupDto);
	}
}
