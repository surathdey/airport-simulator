package com.loyaltyone.problem.airportsim.model;

import java.util.List;


import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;

import com.loyaltyone.problem.airportsim.constants.BeanConstants;

/**
 * This is mapper class for JSON mapping.
 * It holds list of flights {@link com.loyaltyone.problem.airportsim.model.FlightForLandNDepart }
 * object.
 * 
 * @author sudey
 *
 */

public class ListOfFlights {

	@Inject
	@Qualifier(BeanConstants.FLIGHT_LAND_DEPART)
	private List<FlightForLandNDepart> flights;

	public List<FlightForLandNDepart> getFlights() {
		return flights;
	}

	public void setFlights(List<FlightForLandNDepart> flights) {
		this.flights = flights;
	}

}
