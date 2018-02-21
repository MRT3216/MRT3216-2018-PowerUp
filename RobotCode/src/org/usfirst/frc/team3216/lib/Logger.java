package org.usfirst.frc.team3216.lib;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.usfirst.frc.team3216.robot.RobotMap;

/**
 * Logger ********************************************************************
 * Only entries with a logging level that is less than or equal to the local
 * level, as well as less than or equal to the global level, will be entered.
 */
public class Logger {
	/** Configuration Constants ***********************************************/
	private static final boolean USE_CONSOLE = RobotMap.LOG_TO_CONSOLE;
	private static final boolean USE_FILE = RobotMap.LOG_TO_FILE;
	private static final String FILE_FORMAT = RobotMap.LOG_FILE_FORMAT;
	private static final String DIRECTORY_PATH = RobotMap.LOG_DIRECTORY_PATH;
	private static final String TIME_ZONE = RobotMap.LOG_TIME_ZONE;
	private static final String TIME_FORMAT = RobotMap.LOG_TIME_FORMAT;
	private static final Level GLOBAL_LEVEL = RobotMap.LOG_GLOBAL;

	/** List of Logging Levels ************************************************/
	public static enum Level {
		OFF, ERROR, WARNING, TRACE, DEBUG, DEBUG_PERIODIC, ALL;
		public boolean isLessThanOrEqualTo(Level b) {
			return this.ordinal() <= b.ordinal();
		}

		public boolean isGreaterThan(Level b) {
			return this.ordinal() > b.ordinal();
		}
	}

	/** Initialize the log file ***********************************************/
	static private FileIO file = new FileIO();
	static {
		if (USE_FILE) {
			String filename = formatDateTime(FILE_FORMAT);
			file.create(DIRECTORY_PATH, filename);
		}
	}

	/** Instance Variables ****************************************************/
	Level level; // logging level for this instance
	String caller; // The calling class name for this instance

	/**
	 * Logger **************************************************************** Level
	 * level the local logging level String caller the class name for the local
	 * instance
	 */
	public Logger(Level level, String caller) {
		this.level = level;
		this.caller = caller;
	}

	/**
	 * formatDateTime ********************************************************
	 * Return the current date and time as a String following the input formatting
	 * string.
	 * 
	 * String format A parameterized string used by SimpleDateFormat to format the
	 * date and time.
	 */
	static private String formatDateTime(String format) {
		Date now = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		formatter.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
		return formatter.format(now);
	}

	/**
	 * add ******************************************************************* Write
	 * a formatted entry into the log, including the time and calling class.
	 * 
	 * String message the contents of the log entry Level level the logging level of
	 * the entry
	 */
	public void add(String message, Level level) {
		if (level.isLessThanOrEqualTo(GLOBAL_LEVEL) && level.isLessThanOrEqualTo(this.level)
				&& (level.isGreaterThan(Level.OFF))) {

			String time = formatDateTime(TIME_FORMAT);
			message = time + "\t" + "[" + caller + "] " + message;

			if (USE_FILE)
				file.write(message);
			if (USE_CONSOLE)
				System.out.println(message);
		}
	}

	public void add(String label, double number, Level level) {
		String message = label + " = " + number;
		add(message, level);
	}
}