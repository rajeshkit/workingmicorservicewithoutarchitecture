package com.cts.bookingservice.entity;

import java.time.LocalDate;
import java.time.LocalTime;


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
public class Booking {
	@Id
	private int bookingId;
	private int userId;
	private int vehicleId;
	private int serviceCenterId;
	private LocalDate date;
	private LocalTime timeSlot;
	private String status;
}