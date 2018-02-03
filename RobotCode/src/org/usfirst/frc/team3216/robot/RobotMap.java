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
	public static enum Pincher {OPEN, CLOSED;}
	public static enum Popper {EXTENDED, RETRACTED;}
	public static enum Gear {HIGH, LOW;}	
	public static Bot currentBot = Bot.BLUEFISH;
	public static boolean hasJoystick = (currentBot == Bot.MAINBOT) ? true : false;
	public static boolean hasPneumatics = (currentBot == Bot.MAINBOT) ? true : false;
	
	/** Autonomous ************************************************************/
	public static enum AutonomousModes {CROSS_LINE};
	
	/** USB ports *************************************************************/
	public static final int USB_GAMEPAD 		= 0;
	public static final int USB_CONTROL_STICK 	= 2;
	
	/** PWM ID numbers ********************************************************/
	public static final int PWM_LEFT_MOTOR 	= 0;
	public static final int PWM_RIGHT_MOTOR = 1;
	
	/** Analog Input ports ****************************************************/
	public static final int RANGEFINDER = 0;
	
	/** Digital Input ports ***************************************************/
	public static final int LEFT_ENCODER_CHANNEL_A = 0;
	public static final int LEFT_ENCODER_CHANNEL_B = 1;
	public static final int RIGHT_ENCODER_CHANNEL_A = 2;
	public static final int RIGHT_ENCODER_CHANNEL_B = 3;
	
	/** Drive Mode Settings ***************************************************/
	public static enum DriveMode {TANK, ARCADE;}
	public static DriveMode currentDriveMode = DriveMode.ARCADE;
	
	/** Drivetrain Settings ***************************************************/
	public static final boolean REVERSE_LEFT_MOTOR = true;
	public static final boolean REVERSE_RIGHT_MOTOR = false;
	public static final double ACCELERATION_MAX = 1.5;
	// Determines the threshold at which the robot drives straight
	public static final double TURN_RATE_THRESHOLD = 0.5;
		
	/** Logger Settings *******************************************************/
	public static final String 		LOG_FILE_FORMAT = "yyyy-MM-dd-hhmmss";
	public static final String 		LOG_TIME_FORMAT = "hh:mm:ss:SSS";
	public static final String 		LOG_DIRECTORY_PATH = "c:\\logs";
	public static final String 		LOG_TIME_ZONE = "America/Denver";
	public static final boolean 	LOG_TO_CONSOLE 				= true;
	public static final boolean 	LOG_TO_FILE 				= false;
	public static final Logger.Level 	LOG_GLOBAL 				= Logger.Level.ALL;
	public static final Logger.Level 	LOG_ROBOT 				= Logger.Level.OFF;
	public static final Logger.Level	LOG_OI 					= Logger.Level.OFF;
	
	/** Subsystems **/                                                   
	public static final Logger.Level	LOG_DRIVETRAIN			= Logger.Level.OFF;
	public static final Logger.Level	LOG_RANGEFINDER  	    = Logger.Level.TRACE;
	public static final Logger.Level    LOG_IMU					= Logger.Level.OFF;	
	public static final Logger.Level    LOG_ENCODER             = Logger.Level.OFF;
	public static final Logger.Level	LOG_PNEUMATICS			= Logger.Level.OFF;
	public static final Logger.Level 	LOG_ELEVATOR 			= Logger.Level.OFF;
	public static final Logger.Level 	LOG_SHIFTER				= Logger.Level.OFF;

	
	/** Commands **/
	public static final Logger.Level LOG_DRIVEFORWARD			= Logger.Level.OFF;
	public static final Logger.Level LOG_DRIVETRAIN_DRIVESTRAIGHT = Logger.Level.OFF;
	
	
	/** Network Table Names **/
	public static final String networkTableName 				= "SmartDashboard";
	public static final String ntDeadzone  						= "deadzone";
	public static final String ntSensitivity 					= "sensitivity";
	public static final String ntSpeed 							= "speed";
	public static final String ntRangeFinderDistance 			= "rangeFinderDistance";
	public static final String ntRangeFinderAverageDistance		= "rangeFinderAverageDistance";
	public static final String ntKP 							= "kp";
	public static final String ntExampleVariable 				= "example_variable";
	public static final String ntEncoderDistance				= "encoderDistance";
	public static final String ntEncoderRate					= "encoderRate";
	public static final String ntAutonomousMode					= "/autonomous/modes";
	public static final String ntPincher						= "pincher";
	public static final String ntPopper							= "popper";
	public static final String ntElevatorHeight					= "elevatorHeight";
	public static final String ntGear							= "gear";
	public static final String ntShootCubeDelay					= "shootCubeDelay";
	

	/** Network Table Values **/
	public static AutonomousModes AUTONOMOUS_MODE				= AutonomousModes.CROSS_LINE;
	public static double JOYSTICK_DEADZONE 						= 0.05;
	public static double JOYSTICK_SENSITIVITY 					= 1.0;
	public static double JOYSTICK_EQUALITY_THRESHHOLD			= 0.05;
	public static double SPEED 									= 1.0;
	public static double RANGEFINDER_DISTANCE 					= 0.0;
	public static double KP 									= 0.01;
	public static boolean EXAMPLE 								= false;
	public static double SHOOT_CUBE_DELAY						= 0.1;
}
