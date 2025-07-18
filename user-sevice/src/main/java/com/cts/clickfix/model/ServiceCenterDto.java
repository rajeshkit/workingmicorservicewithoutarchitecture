package com.cts.clickfix.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceCenterDto {
	
	private int serviceCenterId;
	private String name;
	private String location;
	private long contact;
}
