package com.cts.bookingservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.bookingservice.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking ,Integer>{
	List<Booking> findAllByUserId(int userId);

}