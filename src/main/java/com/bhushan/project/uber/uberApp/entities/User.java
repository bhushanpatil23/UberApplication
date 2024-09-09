package com.bhushan.project.uber.uberApp.entities;

import java.util.Set;

import com.bhushan.project.uber.uberApp.entities.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="app_user",
		indexes = {
				@Index(name="idx_user_email" ,columnList="email")
})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
	@ElementCollection(fetch =FetchType.LAZY)	// this will create a normalized table app-user-roles
	@Enumerated(EnumType.STRING)
	private Set<Role> roles;
	
}
