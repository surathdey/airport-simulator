package com.loyaltyone.problem.airportsim.runway;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.Subscribe;
import com.loyaltyone.problem.airportsim.model.*;

/**
 * <h1> Main Event Bus Subscriber class which implements Runway </h1>
 * This class is the subscriber of Google Event Bus.
 * After subscribing and event, this class simulates Runway to do the activity
 * @author sudey
 *
 */
public class RunwaySimulator {
	private static Logger LOG = Logger.getLogger(RunwaySimulator.class);
	@Autowired
	AsyncEventBus eventBus;

	@Autowired
	StartDateTime startDate;

	private FlightForLandNDepart flightInformation;
	private Calendar cal = Calendar.getInstance();

	@PostConstruct
	public void init(){
		eventBus.register(this);
		flightInformation = new FlightForLandNDepart();
	}

	@Subscribe
	public void getPublishData(FlightsInBlockingQueue flightsInBlockingQueue){
		LOG.info("Got FlightsInBlockingQueue on Subscriber: ");
		LOG.info("Start time of the simulator" + startDate.getStartDate());


		while (!flightsInBlockingQueue.isEmpty()){
			this.getTopOFTheQueue(flightsInBlockingQueue);
			if (this.timeForActivity()){
				this.printTopOFTheQueue();
				this.useRunway(flightsInBlockingQueue);
			}

		}
	}



	/**
	 * This method simply prints the top of the element in the queue.
	 */
	private void printTopOFTheQueue(){
		LOG.info("flight " + flightInformation.getFlight() + 
				" is USING RUNWAY for " + flightInformation.getActivity() +
				" , will take " + flightInformation.getDuration() + 
				" sec for " + flightInformation.getActivity());

	}

	/**
	 * This method simulates runways and blocks the runway for specified duration
	 * {@link com.loyaltyone.problem.airportsim.model.FlightForLandNDepart#getDuration() }
	 * @param flightsInBlockingQueue {@link com.loyaltyone.problem.airportsim.model.FlightsInBlockingQueue}
	 */

	private void useRunway(FlightsInBlockingQueue flightsInBlockingQueue){
		try {
			TimeUnit.SECONDS.sleep(flightsInBlockingQueue.getQueue().getDuration());
			LOG.info("Runway Clear");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void getTopOFTheQueue(FlightsInBlockingQueue flightsInBlockingQueue){

		flightInformation.setActivity(flightsInBlockingQueue.topOfQueue().getActivity());
		flightInformation.setDelay(flightsInBlockingQueue.topOfQueue().getDelay());
		flightInformation.setDuration(flightsInBlockingQueue.topOfQueue().getDuration());
		flightInformation.setFlight(flightsInBlockingQueue.topOfQueue().getFlight());

	}

	/**
	 * Returns true if any flight has ready for land/take off depending on the delay 
	 * {@link com.loyaltyone.problem.airportsim.model.FlightForLandNDepart#getDelay()}of that flight
	 * @return
	 */
	private boolean timeForActivity() {
		cal.setTime(startDate.getStartDate());
		cal.add(Calendar.SECOND,flightInformation.getDelay());
		LOG.info("Next flight in QUEUE is "  + flightInformation.getFlight());
		LOG.info("Time for its activity is at [" + startDate.getStartDate() + 
				" + " + flightInformation.getDelay() + " secs = "
				+  cal.getTime() + " ] ");
		while(true){
			Date date = new Date();
			if(date.compareTo(cal.getTime()) > 0){

				break;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		return true;

	}

}
