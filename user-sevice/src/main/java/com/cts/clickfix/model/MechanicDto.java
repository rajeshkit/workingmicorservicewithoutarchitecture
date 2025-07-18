package com.cts.clickfix.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MechanicDto { //Java bean 
	@NotNull(message = "mechanic id should not be null please enter mechanic id")
	private Integer mechanicId;
	@NotNull(message = "service ceneter id should not be null please enter center id")
	private Integer serviceCenterId;
	@NotBlank(message = "name should not be empty enter the name")
	private String name;
	@NotBlank(message = "expertise should not be empty enter the name")
	private String expertise;
	@Email(message="Email is invalid",regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}")
	private String email;
}
