package com.cts.bookingservice.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ApiException {
	private int code;
	private String message;
	private String path;
	private Date when;

}