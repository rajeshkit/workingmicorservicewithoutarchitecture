package com.cts.clickfix.service;

import java.io.Serializable;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.cts.clickfix.appconfig.BookingFeignClient;
import com.cts.clickfix.appconfig.UserFeignClient;
import com.cts.clickfix.entity.Mechanic;
import com.cts.clickfix.exception.MechanicIdIsNotFoundException;
import com.cts.clickfix.exception.MechanicUpdateFailureException;
import com.cts.clickfix.model.BookingDto;
import com.cts.clickfix.model.MechanicDto;
import com.cts.clickfix.model.UserDto;
import com.cts.clickfix.repository.MechanicRepository;

@Service
public class MechanicService  { //Source code under test or SUT
	@Autowired
	private MechanicRepository mechanicRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private BookingFeignClient bookingFeignClient;
	
	@Autowired
	private UserFeignClient userFeignClient;

	private Logger logger=LoggerFactory.getLogger(MechanicService.class);
	
	public MechanicDto addMechanic(MechanicDto mechanicDto) {  //MUT
		Mechanic mechanic = modelMapper.map(mechanicDto, Mechanic.class);
		Mechanic resultEntity = mechanicRepository.save(mechanic);
		
		return modelMapper.map(resultEntity, MechanicDto.class);
	}
	public MechanicDto getMechanicById(int mechanicId) { 
		MechanicDto mechanicDto = null;
		Optional<Mechanic> optionalMechanic = mechanicRepository.findById(mechanicId);
		if (optionalMechanic.isEmpty()) {
			logger.debug("mechanic is not there is the db");
			throw new MechanicIdIsNotFoundException("mechanic id: "+mechanicId+" is not there in the db");
		} else {
			logger.info("mechanic details found");
			mechanicDto = modelMapper.map(optionalMechanic.get(), MechanicDto.class);
		}
		return mechanicDto;

	}
	public UserDto getMechanicById(int mechanicId,String userEmail) {
//		UserDto response=restTemplate.getForObject("http://USER-SERVICE/user-api/users/"+userEmail,UserDto.class);
//		System.out.println(response);
//		
		return userFeignClient.findUser(userEmail);
	}
	public String deleteMechanicById(@PathVariable int mechanicId) {
		String response = null;
		boolean result = mechanicRepository.existsById(mechanicId);
		if (result) {
			mechanicRepository.deleteById(mechanicId);
			response = "mechanic with id: " + mechanicId + " is deleted sucessfully";
		} else {
			response = "delete is failed due to none existence of mechanic id: " + mechanicId;
		}
		return response;
	}

	public MechanicDto updateMechanic(MechanicDto mechanicDto) {
		MechanicDto mechanicDto2=null;
		boolean result=mechanicRepository.existsById(mechanicDto.getMechanicId());
		if(result) {
		    Mechanic m	=mechanicRepository.save(modelMapper.map(mechanicDto,Mechanic.class));
			mechanicDto2=modelMapper.map(m,MechanicDto.class);
		}else {
			throw new MechanicUpdateFailureException("mechanic id is not exists");
		}
		return mechanicDto2;	
	}													//8765
	public BookingDto getMechanicById(int mechanicId, int bookingId) {
//		BookingDto response = restTemplate.getForObject("http://BOOKING-SERVICE/booking-api/bookings/"+bookingId, BookingDto.class);
//		System.out.println(response);
//		//persist the booking object
		BookingDto response=bookingFeignClient.findBooking(bookingId); 
							
		return response;
	}

}












