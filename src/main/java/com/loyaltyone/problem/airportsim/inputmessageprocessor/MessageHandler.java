package com.loyaltyone.problem.airportsim.inputmessageprocessor;

import java.io.File;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;

import com.loyaltyone.problem.airportsim.utility.StringUtil;
import com.loyaltyone.problem.airportsim.constants.BeanConstants;
import com.loyaltyone.problem.airportsim.constants.Constants;

/**
 * <h1> Handle incoming JSON message from input file</h1>
 * This class takes incoming JSON message from input file and does 2 things
 * 		1. Process the JSON message using {@link com.loyaltyone.problem.airportsim.inputmessageprocessor.JSONProcessor#processInputJSON(File)}
 * 		2. Rename the processed file to FileName.PROCESSED so that the same file
 * 		   will not be picked up by FileReceiver class.
 * 
 * @author sudey
 * @version 1.0
 *
 */
public class MessageHandler {
	private static Logger LOG = Logger.getLogger(MessageHandler.class);

	@Inject
	@Qualifier(BeanConstants.JSON_PROCESSOR)
	private JSONProcessor jsonProcessor;



	/**
	 * This is method to handle input file.
	 * This method takes incoming input file and does 2 things
	 * 		1. Process the JSON message using {@link com.loyaltyone.problem.airportsim.inputmessageprocessor.JSONProcessor#processInputJSON(File)}
	 * 		2. Rename the processed file to FileName.PROCESSED so that the same file
	 * 		   will not be picked up by FileReceiver class.
	 * @param file This is input file.
	 */
	public void handleMessage(File file) {
		jsonProcessor.processInputJSON(file);
		renameFileToProcessed(file);
	}

	/**
	 * This is a helper method to create PROCESSED file.
	 * 
	 * @param file This is the input file.
	 */
	private void renameFileToProcessed(File file) {		
		LOG.info("File to be renamed " + file);
		File renamedFile = new File(StringUtil.concat(file,
				Constants.PROCESSED_FILE_EXTENSION));
		if(!file.renameTo(renamedFile)){
			LOG.info(file + " Can't be renamed to " + renamedFile);
		}

	}

}
