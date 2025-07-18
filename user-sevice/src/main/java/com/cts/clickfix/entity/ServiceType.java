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
public class ServiceType {
	@Id
	private int serviceTypeId;
	private String description;
	private double price;
}
