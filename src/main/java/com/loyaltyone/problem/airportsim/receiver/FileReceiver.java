package com.loyaltyone.problem.airportsim.receiver;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.loyaltyone.problem.airportsim.constants.BeanConstants;
import com.loyaltyone.problem.airportsim.constants.ConfigConstants;
import com.loyaltyone.problem.airportsim.constants.Constants;

import com.loyaltyone.problem.airportsim.utility.Guard;
import com.loyaltyone.problem.airportsim.utility.StringUtil;
import com.loyaltyone.problem.airportsim.utility.ArrayUtil;
import com.loyaltyone.problem.airportsim.inputmessageprocessor.MessageHandler;

/**
 * <h1> File Receiver Class </h1>
 * This FileReceiver is a TimerTask type class which 
 * picks up file from ConfigConstants.INPUT_DIRECTORY.
 * 
 * Filename should conform ConfigConstants.INPUT_FILENAMES_FORMAT
 * and ConfigConstants.INPUT_FILENAMES_FORMAT_REGEX
 * 
 * It checks for Constants.PROCESSED_FILE_EXTENSION and omit that file
 * for picking up. 
 * 
 * @author sudey
 * @version 1.0
 * 
 *
 */
@Service(value = BeanConstants.FILE_RECEIVER)
public class FileReceiver extends TimerTask {
	static final Logger LOG = Logger.getLogger(FileReceiver.class);

	@Inject
	@Qualifier(BeanConstants.INPUT_FILE_SETS)
	private ArrayList<String> fileSetsString;

	@Value(value = ConfigConstants.INPUT_FILENAMES_FORMAT)
	private String fileNameFormat;

	@Value(value = ConfigConstants.INPUT_FILENAMES_FORMAT_REGEX)
	private String fileNameFormatRegex;

	@Value(value = ConfigConstants.INPUT_DIRECTORY)
	private String directory;

	@Inject
	@Qualifier(BeanConstants.MESSAGE_HANDLER)
	private MessageHandler messageHandler;


	private final List<String[]> fileSets = new ArrayList<String[]>();

	/**
	 * This is post construct method of TimerTask.
	 * It does validation 
	 * 		- Input directory existence
	 * 		- File existence
	 * 
	 * @throws Exception
	 * 
	 */
	@PostConstruct
	public void initialize() throws Exception {
		LOG.info("Input directory = " + directory);
		File dir = new File(directory);
		if (!dir.exists()) {
			LOG.error("Manual replay directory [" + directory
					+ "] does not exist.");
			throw new RuntimeException("Input directory [" + directory
					+ "] does not exist.");
		}

		Guard.notNull(fileSetsString, "fileSetsString");
		Guard.isTrueDependency(fileSetsString.size() >= 1,
				"fileSetsString.size() >= 1");

		for (String fileSetString : fileSetsString) {
			fileSets.add(fileSetString.split(","));
		}

		Guard.notNull(fileSets, "fileNames");

	}

	/**
	 * This is run method of TimerTask.
	 * It is scheduled on ConfigConstants.POLL_INTERVAL_CRON
	 * 
	 * It polls any new file present in the directory 
	 * and pass that new file to { @see com.loyaltyone.problem.airportsim.inputmessageprocessor.MessageHandler.handleMessage(file) }
	 * 
	 */
	@Override
	@Scheduled(cron = ConfigConstants.POLL_INTERVAL_CRON)
	public void run() {
		//LOG.info("Starting File Receiver");

		File dir = new File(directory);
		for (final String[] fileSet : fileSets) {
			File[] files = dir.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					File processedFile = new File(StringUtil.concat(dir,
							File.separator, name,
							Constants.PROCESSED_FILE_EXTENSION));


					String regex = fileSet[0].replace(fileNameFormat,
							fileNameFormatRegex);

					return name.matches(regex)
							&& !processedFile.exists();
				}
			});

			if (!ArrayUtil.isNullOrEmpty(files)){
				for (File file : files) {
					LOG.info("Got a New Request File ==>" + file);
					messageHandler.handleMessage(file);

				}
			}else{
				//LOG.info("No File Present");
			}
		}

	}

}
