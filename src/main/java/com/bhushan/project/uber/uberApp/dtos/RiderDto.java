package com.bhushan.project.uber.uberApp.dtos;

import com.bhushan.project.uber.uberApp.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RiderDto {

	private Long id;
	private User user;
	
	private Double rating;
	
	
}
