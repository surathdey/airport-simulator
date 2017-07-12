package com.loyaltyone.problem.airportsim.constants;

/**
 * This constant class holds various constants which are passed through 
 * configuration files.
 * @author sudey
 *
 */
public class ConfigConstants {
	public static final String POLL_INTERVAL_CRON = "${poller.cron}";
	public static final String INPUT_DIRECTORY = "${message.input.dir}";
	public static final String INPUT_FILENAMES_FORMAT = "${message.input.format}";
	public static final String INPUT_FILENAMES_FORMAT_REGEX = "${message.input.format.regex}";

}
