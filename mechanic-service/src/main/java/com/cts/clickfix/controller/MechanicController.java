package com.cts.clickfix.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.cts.clickfix.model.BookingDto;
import com.cts.clickfix.model.MechanicDto;
import com.cts.clickfix.model.UserDto;
import com.cts.clickfix.service.MechanicService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/mechanic-api")
public class MechanicController {
	
	private Logger logger=LoggerFactory.getLogger(MechanicController.class);

	@Autowired
	private MechanicService mechanicService;
	
	@GetMapping(value = "/mechanics/users")
	public ResponseEntity<String> addUser() {
		logger.info("post method is called");
		ResponseEntity<String> response = new ResponseEntity<>("user created", HttpStatus.CREATED);
		logger.info("response is created");
		return response;
	}
	
	@PostMapping(value = "/mechanics")
	public ResponseEntity<MechanicDto> addMechanic(@Valid @RequestBody MechanicDto mechanicDto) {
		logger.info("add mechananic controller method is called"+mechanicDto);
		MechanicDto mdto = mechanicService.addMechanic(mechanicDto);
		logger.info("response is created");
		ResponseEntity<MechanicDto> response = new ResponseEntity<>(mdto, HttpStatus.CREATED);
		return response;
	}

	@GetMapping(value = "/mechanics/{mechanicId}")
	public MechanicDto getMechanicById(@PathVariable int mechanicId) {
		return mechanicService.getMechanicById(mechanicId);
	}

	@GetMapping(value = "/mechanics/{mechanicId}/{userEmail}")
	public UserDto getMechanicByIdAndUserId(@PathVariable int mechanicId,
			@PathVariable String userEmail) {
		return mechanicService.getMechanicById(mechanicId,userEmail);
	}
	@GetMapping(value = "/mechanics/booking/{mechanicId}/{bookingId}")
	public BookingDto getMechanicByIdAndUserId(@PathVariable int mechanicId,
			@PathVariable int bookingId) {
		return mechanicService.getMechanicById(mechanicId,bookingId);
	}
	@DeleteMapping(value = "/mechanics/{mechanicId}")
	public String deleteMechanicById(@PathVariable int mechanicId) {
		return mechanicService.deleteMechanicById(mechanicId);
	}

	@PutMapping(value = "/mechanics")
	public MechanicDto updateMechanic(@RequestBody MechanicDto mechanicDto) {
		return mechanicService.updateMechanic(mechanicDto);
	}
	
}














