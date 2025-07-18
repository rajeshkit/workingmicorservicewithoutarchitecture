package com.cts.bookingservice.model;

import java.time.LocalDate;
import java.time.LocalTime;


import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder()
public class BookingDto {
	@NotNull(message = "booking id should not be null please enter booking id")
	private int bookingId;
	@NotNull(message = "user id should not be null please enter user id")
	private int userId;
	@NotNull(message = "vehicle id should not be null please enter vehicle id")
	private int vehicleId;
	@NotNull(message = "service ceneter id should not be null please enter center id")
	private int serviceCenterId;
	@FutureOrPresent
	private LocalDate date;
	@FutureOrPresent
	private LocalTime timeSlot;
	private String status;

}