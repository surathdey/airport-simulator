package com.test.loyaltyone.problem.airportsim.model;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import com.loyaltyone.problem.airportsim.constants.*;
import com.loyaltyone.problem.airportsim.model.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/config/context.xml")
public class FlightForLandNDepartTest {

	@SuppressWarnings("SpringJavaAutowiringInspection")
	@Autowired
	protected ApplicationContext ac;


	@Test
	public void testActivity() {
		FlightForLandNDepart flight = (FlightForLandNDepart)ac.getBean(BeanConstants.FLIGHT_LAND_DEPART);
		flight.setActivity("Departure");
		assertEquals(flight.getActivity(),"Departure");

	}

	@Test
	public void testDelay() {
		FlightForLandNDepart flight = (FlightForLandNDepart)ac.getBean(BeanConstants.FLIGHT_LAND_DEPART);
		flight.setDelay(25);
		assertEquals(flight.getDelay(),(Integer)25);

	}

	@Test
	public void testName() {
		FlightForLandNDepart flight = (FlightForLandNDepart)ac.getBean(BeanConstants.FLIGHT_LAND_DEPART);
		flight.setFlight("IS213");
		assertEquals(flight.getFlight(),"IS213");

	}

	@Test
	public void testDuration() {
		FlightForLandNDepart flight = (FlightForLandNDepart)ac.getBean(BeanConstants.FLIGHT_LAND_DEPART);
		flight.setDuration(25);
		assertEquals(flight.getDuration(),(Integer)25);

	}
	
	@Test
	public void testCompareTo() {
		FlightForLandNDepart flight1 = (FlightForLandNDepart)ac.getBean(BeanConstants.FLIGHT_LAND_DEPART);
		FlightForLandNDepart flight2 = (FlightForLandNDepart)ac.getBean(BeanConstants.FLIGHT_LAND_DEPART);
		
		flight1.setFlight("IA213");
		flight1.setDelay(250);
		flight1.setDuration(32);
		flight1.setActivity("Departure");
		
		flight2.setFlight("IA214");
		flight2.setDelay(43);
		flight2.setDuration(23);
		flight2.setActivity("Arrival");
		assertEquals(flight1.compareTo(flight2),2);

	}
}
