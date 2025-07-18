package com.cts.bookingservice.exception;

public class UserIdIsNotFoundException extends RuntimeException {
	public UserIdIsNotFoundException(String msg) {
		super(msg);
	}
}