package com.bhushan.project.uber.uberApp.dtos;

import java.util.Set;

import com.bhushan.project.uber.uberApp.entities.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private String name;
	
	private String email;
	
	private Set<Role> roles;
}
