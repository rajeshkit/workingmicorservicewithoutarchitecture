package com.cts.bookingservice.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cts.bookingservice.model.BookingDto;
import com.cts.bookingservice.service.BookingService;


@RestController
@RequestMapping("/booking-api")
public class BookingController {
	@Autowired
	private BookingService bookingService;
	
	@PostMapping(value = "/bookings")
	public BookingDto addBooking(@RequestBody BookingDto bookingDto) {
		bookingDto.setDate(LocalDate.now());
		bookingDto.setTimeSlot(LocalTime.now());
		return bookingService.addBooking(bookingDto);
	}
	@GetMapping(value="/bookings/{bookingId}")
	public BookingDto getBookingById(@PathVariable int bookingId) {
		return bookingService.getBookingById(bookingId);
	}
	@GetMapping("/bookings/user/{userId}")
	public ResponseEntity<List<BookingDto>> getBookingsByUserId(@PathVariable int userId) {
	    List<BookingDto> bookings = bookingService.getBookingsByUserId(userId);
	    return ResponseEntity.ok(bookings);
	}

	@DeleteMapping(value="/bookings/{bookingId}")
	public String deleteBookingById(@PathVariable int bookingId) {
		return bookingService.deleteBookingById(bookingId);
	}

	@PutMapping("/bookings/{bookingId}")
	public ResponseEntity<BookingDto> updateBooking(@PathVariable int bookingId, @RequestBody BookingDto bookingDto) {
		bookingDto.setBookingId(bookingId); // ensure ID is set
		BookingDto updated = bookingService.updateBooking(bookingDto);
		return ResponseEntity.ok(updated);
	}

	@GetMapping(value="/bookings/status/{bookingId}")
	public String getBookingStatus(@PathVariable int bookingId) {
		return bookingService.getBookingStatus(bookingId);
	}
	
}
