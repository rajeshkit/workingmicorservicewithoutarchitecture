package com.cts.clickfix.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ServiceCenter {
	@Id
	private int serviceCenterId;
	private String name;
	private String location;
	private long contact;

}
