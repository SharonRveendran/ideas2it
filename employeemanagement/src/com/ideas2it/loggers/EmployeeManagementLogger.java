package com.ideas2it.loggers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Custom Logger Class for employee management project
 * @author Sharon V
 * @created 23-04-2021
 */
public class EmployeeManagementLogger {
	private Logger logger;
	
	public EmployeeManagementLogger(Class<?> userClass) {
		logger = LogManager.getLogger(userClass);
	}
	
	/**
	 * Method to call info method in logger class
	 * @param log logging object
	 */
	public void logInfo(Object log) {
		logger.info(log);
	}
	
	/**
	 * Method to call warn method in logger class
	 * @param log logging object
	 */
	public void logWarn(Object log) {
		logger.warn(log);
	}
	
	/**
	 * Method to call error method in logger class
	 * @param log logging object
	 */
	public void logError(Object log) {
		logger.error(log);
	}
	
	/**
	 * Method to call fatal method in logger class
	 * @param log logging object
	 */
	public void logFatal(Object log) {
		logger.fatal(log);
	}
	
	/**
	 * Method to call debug method in logger class
	 * @param log logging object
	 */
	public void logDebug(Object log) {
		logger.debug(log);
	}
}
