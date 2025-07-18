package com.cts.bookingservice.controller;

import com.cts.bookingservice.model.BookingDto;
import com.cts.bookingservice.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@WebMvcTest(BookingController.class)
public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;

    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    private BookingDto bookingDto;

    @BeforeEach
    public void setUp() {
    	bookingDto = new BookingDto();
        bookingDto.setBookingId(100);
        bookingDto.setUserId(200);
        bookingDto.setVehicleId(300);
        bookingDto.setServiceCenterId(400);
        bookingDto.setDate(LocalDate.now());
        bookingDto.setTimeSlot(LocalTime.now());
        bookingDto.setStatus("Pending");
    }

    @Test
    public void testAddBooking() throws Exception {
        when(bookingService.addBooking(any(BookingDto.class))).thenReturn(bookingDto);

        mockMvc.perform(post("/booking-api/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookingDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.bookingId").value(100));
    }

    @Test
    public void testGetBookingById() throws Exception {
        when(bookingService.getBookingById(100)).thenReturn(bookingDto);

        mockMvc.perform(get("/booking-api/bookings/100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookingId").value(100));
    }
    

    @Test
    public void testGetBookingsByUserId() throws Exception {
    	List<BookingDto> bookings = List.of(bookingDto);
    	when(bookingService.getBookingsByUserId(1)).thenReturn(bookings);
    	mockMvc.perform(get("/booking-api/bookings/user/1"))
    		.andExpect(status().isOk())
    		.andExpect(jsonPath("$.size()").value(1))
    		.andExpect(jsonPath("$[0].userId").value(1));
    	}

    @Test
    public void testDeleteBookingById() throws Exception {
        when(bookingService.deleteBookingById(100)).thenReturn("booking with id: 100 is deleted sucessfully");

        mockMvc.perform(delete("/booking-api/bookings/100"))
                .andExpect(status().isOk())
                .andExpect(content().string("booking with id: 100 is deleted sucessfully"));
    }

    @Test
    public void testUpdateBooking() throws Exception {
        when(bookingService.updateBooking(any(BookingDto.class))).thenReturn(bookingDto);

        mockMvc.perform(put("/booking-api/bookings/100")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookingDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookingId").value(100));
    }

    @Test
    public void testGetBookingStatus() throws Exception {
        when(bookingService.getBookingStatus(100)).thenReturn("Scheduled");

        mockMvc.perform(get("/booking-api/bookings/status/100"))
                .andExpect(status().isOk())
                .andExpect(content().string("Scheduled"));
    }
}

