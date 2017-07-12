package com.loyaltyone.problem.airportsim.inputmessageprocessor;

import java.io.File;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.loyaltyone.problem.airportsim.constants.BeanConstants;
import com.loyaltyone.problem.airportsim.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.eventbus.AsyncEventBus;

/**
 *<h1> JSON Processor Class </h1>
 * This class is to Map JSON values to 
 * {@link com.loyaltyone.problem.airportsim.model.ListOfFlights} class
 * 
 * Then it adds all these flights to a blockingqueue
 * {@link com.loyaltyone.problem.airportsim.model.FlightsInBlockingQueue }
 * 
 * Then this blocking queue is passed to Event Bus
 * 
 * @author sudey
 *
 */

public class JSONProcessor {
	private static Logger LOG = Logger.getLogger(JSONProcessor.class);

	private ObjectMapper mapper = new ObjectMapper();
	private ListOfFlights value = null;
	
	@Inject
	@Qualifier(BeanConstants.FLIGHT_IN_BLOCKING_QUEUE)
	private FlightsInBlockingQueue bqueue;

	@Autowired
	AsyncEventBus eventBus;

	/**
	 * This method takes input file and 
	 * Use Jackson Object Mapper to map to {@link com.loyaltyone.problem.airportsim.model.ListOfFlights}. 
	 * Add JSON data to a blocking queue {@link com.loyaltyone.problem.airportsim.model.FlightsInBlockingQueue }
	 * and post blocking queue as event to Gauava Event Bus.
	 * 
	 * @param file This is input file.
	 */
	public void processInputJSON(File file){

		try{
			value = mapper.readValue(file, ListOfFlights.class);

			if(value != null ){
				for(FlightForLandNDepart flight : value.getFlights()){
					LOG.info("Request flight information= " + flight.toString());
					bqueue.addQueue(flight);
				}

				LOG.info("Posting to EventQueue");
				eventBus.post(bqueue);

			}
		}catch (Exception e){
			LOG.error("Exception in parsing JSON FILE =" + e.getMessage());

		}
	}
	
	

}
