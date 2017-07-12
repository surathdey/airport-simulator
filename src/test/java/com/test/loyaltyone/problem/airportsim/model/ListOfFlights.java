package com.test.loyaltyone.problem.airportsim.model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.loyaltyone.problem.airportsim.constants.*;
import com.loyaltyone.problem.airportsim.model.*;

import static org.mockito.Mockito.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/config/context.xml")

public class ListOfFlights {

	@SuppressWarnings("SpringJavaAutowiringInspection")
	@Autowired
	protected ApplicationContext ac;
	
	@Test
	public void test() {
		List<FlightForLandNDepart> mockedList = mock(List.class);
		FlightForLandNDepart flight = (FlightForLandNDepart)ac.getBean(BeanConstants.FLIGHT_LAND_DEPART);
		flight.setFlight("IA213");
		flight.setDelay(250);
		flight.setDuration(32);
		flight.setActivity("Departure");
		
			
		verify(mockedList).add(flight);
		verify(mockedList).clear();
		
		
	}

}
