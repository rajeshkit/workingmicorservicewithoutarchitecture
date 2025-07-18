package com.cts.clickfix.exception;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cts.clickfix.model.ApiException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class ApplicationGlobalException {
	@ExceptionHandler(exception = MechanicIdIsNotFoundException.class)
	public ApiException handleMecahnicIdIsNotAvailable(MechanicIdIsNotFoundException e,
			HttpServletRequest request,HttpServletResponse response) {
		ApiException apiException=ApiException.builder().code(response.getStatus())
		.message(e.getMessage()).path(request.getRequestURI())
		.when(new Date()).build();
		return apiException;
	}
	@ExceptionHandler(exception = MechanicUpdateFailureException.class)
	public ApiException handleMecahnicIdIsNotAvailable(MechanicUpdateFailureException e,
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












