package com.loyaltyone.problem.airportsim;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.loyaltyone.problem.airportsim.constants.Constants;


public class Main {
	private static Logger LOG = Logger.getLogger(Main.class);
	private static ClassPathXmlApplicationContext context;


	public static void main(String[] args) throws Exception {
		LOG.info("Starting Airport Simulator.... from "+System.getProperty("user.dir"));

		ApplicationContext context = 
				new ClassPathXmlApplicationContext(Constants.SPRING_MAIN);

		LOG.info(context);

		addShutdownHook();


	}

	private static void addShutdownHook()
	{
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable()
		{
			public void run()
			{
				if (context != null)
				{
					context.stop();
				}

				LOG.info("Airport Simulator has been shut down from "+System.getProperty("user.dir"));
			}
		}));
	}

}
