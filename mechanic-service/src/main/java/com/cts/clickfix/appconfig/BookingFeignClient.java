package com.cts.clickfix.appconfig;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.clickfix.model.BookingDto;

@FeignClient(name = "BOOKING-SERVICE")
public interface BookingFeignClient {
	@GetMapping("/booking-api/bookings/{bookingId}")
	public BookingDto findBooking(@PathVariable int bookingId);
}
