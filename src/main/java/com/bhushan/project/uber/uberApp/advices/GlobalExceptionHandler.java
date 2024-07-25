package com.bhushan.project.uber.uberApp.advices;


import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bhushan.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.bhushan.project.uber.uberApp.exceptions.RuntimeConflictException;


@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse<?>> handleResourceNotFound(ResourceNotFoundException exception){
		ApiError apiError = ApiError.builder()
									.status(HttpStatus.NOT_FOUND)
									.message(exception.getMessage())
									.build();
		return buildErrorResponseEntity(apiError);
	}
	
	@ExceptionHandler(RuntimeConflictException.class)
	public ResponseEntity<ApiResponse<?>> handleRuntimeConflictException(ResourceNotFoundException exception){
		ApiError apiError = ApiError.builder()
									.status(HttpStatus.CONFLICT)
									.message(exception.getMessage())
									.build();
		return buildErrorResponseEntity(apiError);
	}
	
	

//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<ApiResponse<?>> handleInternalServerError(	Exception exception){
//		ApiError ApiError = ApiError.builder()
//									.status(HttpStatus.INTERNAL_SERVER_ERROR)
//									.message(exception.getMessage())
//									.build();
//		return buildErrorResponseEntity(ApiError);
//	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<?>> handleInputValidationErrors(MethodArgumentNotValidException exception){
		
		List<String> errors = exception
				.getBindingResult()
				.getAllErrors()
				.stream()
				.map(error -> error.getDefaultMessage())
				.collect(Collectors.toList());
		ApiError apiError = ApiError.builder()
									.status(HttpStatus.BAD_REQUEST)
									.message("Input Validation Failed")
									.subErrors(errors)
									.build();
		
		return buildErrorResponseEntity(apiError); 
	}

	private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError ApiError) {
		// TODO Auto-generated method stub
		return new ResponseEntity<>(new ApiResponse<>(ApiError),HttpStatus.NOT_FOUND);

	}
} 
