package com.cts.clickfix.controller; 

import java.util.List;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;


import com.cts.clickfix.entity.ServiceCenter;
import com.cts.clickfix.model.ServiceCenterDto;
import com.cts.clickfix.repository.ServiceCenterRepository;

@Controller
public class ServiceCenterController {
	
	@Autowired
	private ServiceCenterRepository serviceCenterRepository;
	
	@Autowired
	ModelMapper modelMapper;	
	
	
	@RequestMapping(value= "/addServiceCenter", method = RequestMethod.POST)
	@ResponseBody
	public ServiceCenterDto addServiceCenter(@RequestBody ServiceCenterDto serviceCenterDto) {
		System.out.println(serviceCenterDto);
		
		ServiceCenter s1 = modelMapper.map(serviceCenterDto, ServiceCenter.class);
		ServiceCenter result = serviceCenterRepository.save(s1);
		
		ServiceCenterDto sdto = modelMapper.map(result, ServiceCenterDto.class);
		
		return sdto ;
	}
	
	
	@RequestMapping(value= "/getServiceCenterById/{serviceCenterId}", method = RequestMethod.GET)
	@ResponseBody
	public ServiceCenterDto getServiceCenterById(@PathVariable int serviceCenterId) {
		Optional<ServiceCenter> resEnt = serviceCenterRepository.findById(serviceCenterId);
		
		if (resEnt.isPresent()) {
			ServiceCenterDto serviceCenterDto = modelMapper.map(resEnt.get(), ServiceCenterDto.class);
				System.out.println("Service Center found!");
				System.out.println(serviceCenterDto);		
			ServiceCenter s1 = modelMapper.map(serviceCenterDto, ServiceCenter.class);
			ServiceCenter result = serviceCenterRepository.save(s1);
			ServiceCenterDto sdto = modelMapper.map(resEnt, ServiceCenterDto.class);
			return sdto;
		} else {
			System.out.println("Service Center not found!");
			return null;
			
		}
		
	}
	
	
	
	@RequestMapping(value = "/deleteServiceCenterById/{serviceCenterId}" , method = RequestMethod.DELETE)
	@ResponseBody
	private void deleteServiceCenterById(@PathVariable int serviceCenterId) {
		Optional<ServiceCenter> resEnt = serviceCenterRepository.findById(serviceCenterId);
		if(resEnt.isPresent()) {
			System.out.println("Service Center found!");
			serviceCenterRepository.deleteById(serviceCenterId);
			System.out.println("Service Center "+ serviceCenterId +" Deleted! ");
		 }
		else {
			System.out.println("Service Center not found!");
		}
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
