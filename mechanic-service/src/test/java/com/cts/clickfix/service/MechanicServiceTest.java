package com.cts.clickfix.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.cts.clickfix.entity.Mechanic;
import com.cts.clickfix.exception.MechanicIdIsNotFoundException;
import com.cts.clickfix.model.MechanicDto;
import com.cts.clickfix.repository.MechanicRepository;

@ExtendWith(MockitoExtension.class)
class MechanicServiceTest {
	@Mock
	private MechanicRepository mechanicRepository;

	@Mock
	private ModelMapper modelMapper;

	@InjectMocks
	private MechanicService mechanicService;

	private MechanicDto mechanicDto;
	private Mechanic mechanic;

	@BeforeEach
	public void setUp() {
		mechanicDto = MechanicDto.builder().email("rajesh.kit@gmail.com").expertise("Training").mechanicId(2000)
				.name("rajesh").serviceCenterId(300).build();
		mechanic = Mechanic.builder().expertise("Training").mechanicId(2000).name("rajesh").serviceCenterId(300)
				.build();
	}

	@Test
	void testAddMechanic() {
		Mockito.when(mechanicRepository.save(mechanic)).thenReturn(mechanic);
		Mockito.when(modelMapper.map(mechanicDto, Mechanic.class)).thenReturn(mechanic);
		Mockito.when(modelMapper.map(mechanic, MechanicDto.class)).thenReturn(mechanicDto);
		MechanicDto actual = mechanicService.addMechanic(mechanicDto);
		assertEquals(2000, actual.getMechanicId());
		assertEquals("rajesh", actual.getName());

	}

	@Test
	void testGetMechanicById() {
		Mockito.when(mechanicRepository.findById(anyInt())).thenReturn(Optional.of(mechanic));
		Mockito.when(modelMapper.map(mechanic, MechanicDto.class)).thenReturn(mechanicDto);
		MechanicDto actuals=mechanicService.getMechanicById(2000);
		assertEquals(2000, actuals.getMechanicId());
		assertEquals(300, mechanicDto.getServiceCenterId());
		assertEquals("rajesh", mechanic.getName());
	}
	@Test
	void testGetMechanicByIdThrowsException() {
		Mockito.when(mechanicRepository.findById(anyInt())).thenThrow(MechanicIdIsNotFoundException.class);
		assertThrows(MechanicIdIsNotFoundException.class,()->{
			mechanicService.getMechanicById(0);
		});
	}
//
//	@Test
//	void testDeleteMechanicById() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testUpdateMechanic() {
//		fail("Not yet implemented");
//	}

}
