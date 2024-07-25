package com.bhushan.project.uber.uberApp.advices;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
@Data
public class ApiResponse<T> {
	@JsonFormat(pattern="hh:mm:ss dd-MM-yyyy")
	private LocalDateTime timestamp;
	
	private T data;
	
	private ApiError error;
	
	private ApiResponse() {this.timestamp = LocalDateTime.now();}
	
	public ApiResponse(T data) {
		this();
		this.data = data;
	}
	
	public ApiResponse(ApiError apiError) {
		this();
		this.error = apiError;
	}
}
