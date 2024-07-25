package com.bhushan.project.uber.uberApp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverDto {

	private UserDto userDto;
	
	private Double rating;
	
	
}
