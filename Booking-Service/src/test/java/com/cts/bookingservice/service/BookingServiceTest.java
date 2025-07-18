package com.cts.bookingservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.cts.bookingservice.entity.Booking;
import com.cts.bookingservice.exception.BookingIdIsNotFoundException;
import com.cts.bookingservice.exception.BookingUpdateFailureException;
import com.cts.bookingservice.exception.UserIdIsNotFoundException;
import com.cts.bookingservice.model.BookingDto;
import com.cts.bookingservice.repository.BookingRepository;
@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

    @InjectMocks
    private BookingService bookingService;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private ModelMapper modelMapper;
    

	private Booking booking;
	private BookingDto bookingDto;

    @BeforeEach
    public void setUp() {
    	MockitoAnnotations.openMocks(this);
    	booking = new Booking();
        booking.setBookingId(100);
        booking.setUserId(200);
        booking.setVehicleId(300);
        booking.setServiceCenterId(400);
        booking.setDate(LocalDate.now());
        booking.setTimeSlot(LocalTime.now());
        booking.setStatus("Pending");
        
        bookingDto = new BookingDto();
        bookingDto.setBookingId(100);
        bookingDto.setUserId(200);
        bookingDto.setVehicleId(300);
        bookingDto.setServiceCenterId(400);
        bookingDto.setDate(LocalDate.now());
        booking.setTimeSlot(LocalTime.now());
        bookingDto.setStatus("Pending");
    }

    @Test
    void testUpdateBooking_Success() {
		when(bookingRepository.existsById(100)).thenReturn(true);
		when(modelMapper.map(bookingDto, Booking.class)).thenReturn(booking);
		when(bookingRepository.save(booking)).thenReturn(booking);
		when(modelMapper.map(booking, BookingDto.class)).thenReturn(bookingDto);
		BookingDto result = bookingService.updateBooking(bookingDto);
		assertNotNull(result);
		assertEquals("Scheduled", result.getStatus());

    }

	@Test
    void testUpdateBooking_BookingNotFound() {
        // Arrange
        BookingDto bookingDto = new BookingDto();
        bookingDto.setBookingId(999);

        when(bookingRepository.existsById(999)).thenReturn(false);

        // Act & Assert
        assertThrows(BookingUpdateFailureException.class, () -> {
            bookingService.updateBooking(bookingDto);
        });
    }
	@Test
	public void testGetBookingsByUserId_NotFound() {
	    int userId = 999;

	    when(bookingRepository.findAllByUserId(userId)).thenReturn(Collections.emptyList());

	    assertThrows(UserIdIsNotFoundException.class, () -> {
	        bookingService.getBookingsByUserId(userId);
	    });

	    verify(bookingRepository).findAllByUserId(userId);
	}

	@Test
	void testDeleteBookingById_Success() {
	    when(bookingRepository.existsById(100)).thenReturn(true);

	    String response = bookingService.deleteBookingById(100);

	    assertEquals("booking with id: 100 is deleted sucessfully", response);
	    verify(bookingRepository).deleteById(100);
	}

	@Test
	void testDeleteBookingById_NotFound() {
	    when(bookingRepository.existsById(999)).thenReturn(false);

	    String response = bookingService.deleteBookingById(999);

	    assertEquals("delete is failed due to none existence of booking id: 999", response);
	}
	@Test
	void testGetBookingStatus_Success() {

	    when(bookingRepository.findById(100)).thenReturn(Optional.of(booking));

	    String status = bookingService.getBookingStatus(100);

	    assertEquals("Completed", status);
	    verify(bookingRepository).findById(100);
	}

	@Test
	void testGetBookingStatus_NotFound() {
	    when(bookingRepository.findById(999)).thenReturn(Optional.empty());

	    assertThrows(BookingIdIsNotFoundException.class, () -> {
	        bookingService.getBookingStatus(999);
	    });
	}

}


