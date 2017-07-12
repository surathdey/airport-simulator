package com.loyaltyone.problem.airportsim.model;

/**
 * <h1> POJO class for holding individual flight objects </h1>
 * This is a POJO class which holds individual flight objects
 *  
 * @author sudey
 *
 */
public class FlightForLandNDepart implements Comparable<FlightForLandNDepart>{

	/**
	 * Name of the flight.
	 */
	private String flight;

	/**
	 * Activity => Departure or Arrival.
	 */
	private String activity;

	/**
	 * Duration of the activity. For example duration = 45 means, it wil 
	 * take 45 secs to take off or land. 
	 */
	private Integer duration;

	/**
	 * Delay is the time delay from start of the simulation program after which aircraft
	 * ask for activity ( landing or take off )
	 */
	private Integer delay;

	public String getFlight() {
		return flight;
	}
	public void setFlight(String flight) {
		this.flight = flight;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public Integer getDelay() {
		return delay;
	}
	public void setDelay(Integer delay) {
		this.delay = delay;
	}

	@Override
	public String toString(){
		return "Flight [ flightNumber = " + flight + " , " + 
				" activity = " + activity + " , " +
				" duration = " + duration + " , " +
				" delay = " + delay + "]";
	}

	@Override
	public int compareTo(FlightForLandNDepart o) {
		return delay.compareTo(o.getDelay());
	}



}
