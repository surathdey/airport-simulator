package com.loyaltyone.problem.airportsim.model;

import java.util.Date;

/**
 * This is a small helper class for holder Start time of the simulator
 * @author sudey
 *
 */
public final class StartDateTime {
	private final Date startDate;
	
	public Date getStartDate() {
		return (Date) startDate.clone();
	}

	public StartDateTime(){
		this.startDate = new Date();
		
	}
	
	
}
