package com.loyaltyone.problem.airportsim.model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

import org.apache.log4j.Logger;

/**
 * 
 * This class holds a wrapper of Blocking Queue {@link java.util.concurrent.BlockingQueue }
 * Objects in the blocking queue are of type {@link com.loyaltyone.problem.airportsim.model.FlightForLandNDepart }
 * 
 * @author sudey
 *
 */

public class FlightsInBlockingQueue {
	private static Logger LOG = Logger.getLogger(FlightsInBlockingQueue.class);
	
	
	private BlockingQueue<FlightForLandNDepart> flightQueue = new PriorityBlockingQueue<FlightForLandNDepart>() ;
	
	/**
	 * Adding one  to the Queue
	 * @param flight This is a object of a flight
	 */
	public void addQueue(FlightForLandNDepart flight){
		flightQueue.offer(flight);
	}
	
	/**
	 * Retrive an remove the head of the Queue.
	 * 
	 * @return {@link com.loyaltyone.problem.airportsim.model.FlightForLandNDepart }
	 * @throws InterruptedException
	 */
	public FlightForLandNDepart getQueue() throws InterruptedException {
		return flightQueue.take();
		
	}
	
	/**
	 * Returns true if this collection contains no elements.
	 * @return true if this collection contains no elements
	 */
	public boolean isEmpty(){
		return flightQueue.isEmpty();
	}
	
	/**
	 * Retrives but do not remove the top of the queue.
	 * @return {@link com.loyaltyone.problem.airportsim.model.FlightForLandNDepart }
	 */
	public FlightForLandNDepart topOfQueue(){
		return flightQueue.element();
	}

	
}
