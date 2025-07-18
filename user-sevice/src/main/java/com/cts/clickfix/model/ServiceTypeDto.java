package com.cts.clickfix.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceTypeDto {
	
	private int serviceTypeId;
	private String description;
	private double price;
}
