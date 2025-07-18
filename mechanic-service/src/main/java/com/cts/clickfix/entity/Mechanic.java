package com.cts.clickfix.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Mechanic {
	@Id
	private int mechanicId;
	private int serviceCenterId;
	private String name;
	private String expertise;
	//private String email;
}
