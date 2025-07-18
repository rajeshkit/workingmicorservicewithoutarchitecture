package com.cts.bookingservice.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


import com.cts.bookingservice.entity.Booking;
import com.cts.bookingservice.exception.BookingIdIsNotFoundException;
import com.cts.bookingservice.exception.BookingUpdateFailureException;
import com.cts.bookingservice.exception.UserIdIsNotFoundException;
import com.cts.bookingservice.model.BookingDto;
import com.cts.bookingservice.repository.BookingRepository;

@Service
public class BookingService {
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public BookingDto addBooking(BookingDto bookingDto) {
		Booking booking=modelMapper.map(bookingDto,Booking.class);
		Booking resultEntity=bookingRepository.save(booking);
		return modelMapper.map(resultEntity, BookingDto.class);
	}
	public BookingDto getBookingById(int bookingId) {
		BookingDto bookingDto=null;
		Optional<Booking> optionalBooking=bookingRepository.findById(bookingId);
		if(optionalBooking.isPresent()){
			bookingDto=modelMapper.map(optionalBooking.get(),BookingDto.class);
		}else {
			throw new BookingIdIsNotFoundException("booking id: "+bookingId+"is not there in db");
		}
		return bookingDto;	
	}
	public List<BookingDto> getBookingsByUserId(int userId) {
	    List<Booking> bookings = bookingRepository.findAllByUserId(userId);
	    if (bookings.isEmpty()) {
	        throw new UserIdIsNotFoundException("No bookings found for user ID: " + userId);
	    }
	    return bookings.stream()
	                   .map(booking -> modelMapper.map(booking, BookingDto.class))
	                   .collect(Collectors.toList());
	}

	public String deleteBookingById(@PathVariable int bookingId) {
		String response = null;
		boolean result = bookingRepository.existsById(bookingId);
		if (result) {
			bookingRepository.deleteById(bookingId);
			response = "booking with id: " + bookingId + " is deleted sucessfully";
		} else {
			response = "delete is failed due to none existence of booking id: " + bookingId;
		}
		return response;
	}


	public BookingDto updateBooking(BookingDto bookingDto) {
		boolean exists = bookingRepository.existsById(bookingDto.getBookingId());
		if (exists) {
			bookingDto.setDate(LocalDate.now());
			bookingDto.setTimeSlot(LocalTime.now());
			Booking booking = modelMapper.map(bookingDto, Booking.class);
			Booking updatedBooking = bookingRepository.save(booking);
			return modelMapper.map(updatedBooking, BookingDto.class);
		} else {
			throw new BookingUpdateFailureException("Booking ID does not exist");
		}
	}


	public String getBookingStatus(@PathVariable int bookingId) {
		Optional<Booking> optional = bookingRepository.findById(bookingId);
		if (optional.isPresent()) {
			return optional.get().getStatus();
		}else {
			throw new BookingIdIsNotFoundException("Booking with ID " + bookingId + " not found");
		}
	}

}

