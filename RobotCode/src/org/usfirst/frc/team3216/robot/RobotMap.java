package org.usfirst.frc.team3216.robot;

import org.usfirst.frc.team3216.lib.Logger;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;



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
	
	/** Drivetrain Settings ***************************************************/
	public static final boolean REVERSE_LEFT_MOTOR = false;
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
	public static final Logger.Level	LOG_OI 					= Logger.Level.TRACE;
	public static final Logger.Level	LOG_AXIS_TRIGGER 		= Logger.Level.ERROR;
	public static final Logger.Level	LOG_POV_BUTTON			= Logger.Level.ERROR;
	
	/** Subsystems **/                                                   
	public static final Logger.Level	LOG_DRIVETRAIN			= Logger.Level.TRACE;
	public static final Logger.Level	LOG_DRIVETRAIN_FOLLOWERS= Logger.Level.TRACE;
	
	public static double JOYSTICK_DEADZONE = 0.05;
	public static final double JOYSTICK_DRIVE_SCALE = 1.0;
	
	/** Network Tables **/
	public static final String networkTableName = "RobotSettings";
	public static final String ntDeadzone  = "deadzone";
	
	public void syncWithNetworkTables() {
		NetworkTableInstance defaultTable =  NetworkTableInstance.getDefault();
		NetworkTable settings = defaultTable.getTable(networkTableName);
		
		JOYSTICK_DEADZONE = settings.getEntry(ntDeadzone).getDouble(JOYSTICK_DEADZONE);	
	}
}
