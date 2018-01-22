package org.usfirst.frc.team3216.robot;

import org.usfirst.frc.team3216.lib.Logger;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static enum Bot {BLUEFISH, MAINBOT;}
	public static Bot currentBot = Bot.BLUEFISH;

	/** USB ports *************************************************************/
	public static final int USB_GAMEPAD = 0;
	
	/** PWM ID numbers ********************************************************/
	public static final int PWM_LEFT_MOTOR 	= 0;
	public static final int PWM_RIGHT_MOTOR = 1;
	
	/** Analog Input ports ****************************************************/
	public static final int RANGEFINDER = 0;
	
	/** Drivetrain Settings ***************************************************/
	public static final boolean REVERSE_LEFT_MOTOR = true;
	public static final boolean REVERSE_RIGHT_MOTOR = false;
	public static final double ACCELERATION_MAX = 1.5;
		
	/** Logger Settings *******************************************************/
	public static final String 		LOG_FILE_FORMAT = "yyyy-MM-dd-hhmmss";
	public static final String 		LOG_TIME_FORMAT = "hh:mm:ss:SSS";
	public static final String 		LOG_DIRECTORY_PATH = "c:\\logs";
	public static final String 		LOG_TIME_ZONE = "America/Denver";
	public static final boolean 	LOG_TO_CONSOLE 				= true;
	public static final boolean 	LOG_TO_FILE 				= false;
	public static final Logger.Level 	LOG_GLOBAL 				= Logger.Level.ALL;
	public static final Logger.Level 	LOG_ROBOT 				= Logger.Level.TRACE;
	public static final Logger.Level	LOG_OI 					= Logger.Level.OFF;
	
	/** Subsystems **/                                                   
	public static final Logger.Level	LOG_DRIVETRAIN			= Logger.Level.OFF;
	public static final Logger.Level	LOG_RANGEFINDER  	    = Logger.Level.OFF;
	public static final Logger.Level    LOG_IMU					= Logger.Level.TRACE;	
	public static final Logger.Level    LOG_DRIVETRAIN_DRIVESTRAIGHT = Logger.Level.TRACE;
	
	/** Commands **/
	public static final Logger.Level LOG_DRIVEFORWARD			= Logger.Level.OFF;
	
	
	/** Network Table Names **/
	public static final String networkTableName 				= "SmartDashboard";
	public static final String ntDeadzone  						= "deadzone";
	public static final String ntSensitivity 					= "sensitivity";
	public static final String ntSpeed 							= "speed";
	public static final String ntRangeFinderDistance 			= "rangeFinderDistance";
	public static final String ntRangeFinderAverageDistance		= "rangeFinderAverageDistance";
	public static final String ntKP 							=  "kp";
	public static final String ntExampleVariable 				= "example_variable";
	
	/** Network Table Values **/
	public static double JOYSTICK_DEADZONE 						= 0.05;
	public static double JOYSTICK_SENSITIVITY 					= 1.0;
	public static double JOYSTICK_EQUALITY_THRESHHOLD			= 0.05;
	public static double SPEED 									= 1.0;
	public static double RANGEFINDER_DISTANCE 					= 0.0;
	public static double KP 									= 0.03;
	public static boolean EXAMPLE 								= false;
}
