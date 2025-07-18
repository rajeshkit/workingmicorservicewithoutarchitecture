package com.cts.bookingservice.exception;

import java.util.Date;
import java.util.List;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cts.bookingservice.model.ApiException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ApplicationGlobalException {
	@ExceptionHandler(exception = BookingIdIsNotFoundException.class)
	public ApiException handleBookingIdIsNotAvailable(BookingIdIsNotFoundException e,
			HttpServletRequest request,HttpServletResponse response) {
		ApiException apiException=ApiException.builder().code(response.getStatus())
		.message(e.getMessage()).path(request.getRequestURI())
		.when(new Date()).build();
		return apiException;
	}
	@ExceptionHandler(exception = BookingUpdateFailureException.class)
	public ApiException handleMecahnicIdIsNotAvailable(BookingUpdateFailureException e,
			HttpServletRequest request,HttpServletResponse response) {
		ApiException apiException=ApiException.builder().code(response.getStatus())
				.message(e.getMessage()).path(request.getRequestURI())
				.when(new Date()).build();
		return apiException;
	}
	@ExceptionHandler(exception = MethodArgumentNotValidException.class)
	public ApiException handleMethodArgumentNotValidException(MethodArgumentNotValidException e,
			HttpServletRequest request, HttpServletResponse response) {
			List<FieldError> listFieldErrors=e.getFieldErrors();
			StringBuilder sb=new StringBuilder();
			for (FieldError fieldError : listFieldErrors) {
				sb.append(fieldError.getField()+" : "+fieldError.getDefaultMessage());
				sb.append(System.lineSeparator());
				
			}
			ApiException apiException=ApiException.builder().code(response.getStatus())
			.path(request.getRequestURI())
			.when(new Date()).message(sb.toString()).build();
		return apiException;
	}

}
