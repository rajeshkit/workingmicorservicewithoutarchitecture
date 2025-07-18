package com.cts.clickfix.controller;

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

import com.cts.clickfix.model.MechanicDto;
import com.cts.clickfix.service.MechanicService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/mechanic-api")
public class MechanicController {

	@Autowired
	private MechanicService mechanicService;
	
	@GetMapping(value = "/mechanics/users")
	public ResponseEntity<String> addUser() {
		ResponseEntity<String> response = new ResponseEntity<>("user created", HttpStatus.CREATED);
		return response;
	}
	
	@PostMapping(value = "/mechanics")
	public ResponseEntity<MechanicDto> addMechanic(@Valid @RequestBody MechanicDto mechanicDto) {
		MechanicDto mdto = mechanicService.addMechanic(mechanicDto);
		ResponseEntity<MechanicDto> response = new ResponseEntity<>(mdto, HttpStatus.CREATED);
		return response;
	}

	@GetMapping(value = "/mechanics/{mechanicId}")
	public MechanicDto getMechanicById(@PathVariable int mechanicId) {
		return mechanicService.getMechanicById(mechanicId);
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














