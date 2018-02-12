package org.usfirst.frc.team3216.robot;

import org.usfirst.frc.team3216.lib.Logger;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static enum Bot {TESTBOARD, BLUEFISH, MAINBOT;}
	public static enum Pincher {OPEN, CLOSED;}
	public static enum Popper {EXTENDED, RETRACTED;}
	public static enum Gear {HIGH, LOW;}	
	public static Bot currentBot = Bot.BLUEFISH;
	
	/** Subsystems for each bot ***********************************************/
	public static boolean hasIMU = (currentBot == Bot.MAINBOT) ? false : true;
	public static boolean hasJoystick = (currentBot == Bot.BLUEFISH) ? false : true;
	public static boolean hasPneumatics = (currentBot == Bot.BLUEFISH) ? false : true;
	public static boolean hasShifter = (currentBot == Bot.BLUEFISH) ? false : true;
	public static boolean hasDrivetrain = (currentBot == Bot.TESTBOARD) ? false : true;
	public static boolean hasWinch = (currentBot == Bot.MAINBOT) ? true : false;
	public static boolean hasElevator = (currentBot == Bot.MAINBOT) ? true : false;
	public static boolean hasClimbArm = (currentBot == Bot.MAINBOT) ? true: false;
	
	/** Autonomous ************************************************************/
	public static enum AutonomousModes {CROSS_LINE};
	
	/** USB ports *************************************************************/
	public static final int USB_GAMEPAD 		= 0;
	public static final int USB_CONTROL_STICK 	= 2;
	
	/** PWM ID numbers ********************************************************/
	public static final int PWM_LEFT_MOTOR 	= 0;
	public static final int PWM_RIGHT_MOTOR = 1;
	public static final int PWM_ELEVATOR_MOTOR = 2;
	public static final int PWM_WINCH_MOTOR = 3;
	public static final int PWM_CLIMB_ARM_MOTOR = 4;
	
	/** Analog Input ports ****************************************************/
	public static final int RANGEFINDER = 0;
	
	/** Digital Input ports ***************************************************/
	public static final int LEFT_ENCODER_CHANNEL_A = 8;
	public static final int LEFT_ENCODER_CHANNEL_B = 9;
	public static final int RIGHT_ENCODER_CHANNEL_A = 6;
	public static final int RIGHT_ENCODER_CHANNEL_B = 7;
	
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
	public static final Logger.Level	LOG_RANGEFINDER  	    = Logger.Level.OFF;
	public static final Logger.Level    LOG_IMU					= Logger.Level.OFF;	
	public static final Logger.Level    LOG_ENCODER             = Logger.Level.OFF;
	public static final Logger.Level	LOG_PNEUMATICS			= Logger.Level.OFF;
	public static final Logger.Level 	LOG_ELEVATOR 			= Logger.Level.OFF;
	public static final Logger.Level 	LOG_SHIFTER				= Logger.Level.OFF;
	public static final Logger.Level	LOG_WINCH				= Logger.Level.TRACE;
	public static final Logger.Level 	LOG_CLIMB_ARM			= Logger.Level.OFF;

	
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
	public static final String ntRightDriveEncoderDistance		= "rightDriveEncoderDistance";
	public static final String ntRightDriveEncoderRate			= "rightDriveEncoderRate";
	public static final String ntLeftDriveEncoderDistance		= "leftDriveEncoderDistance";
	public static final String ntLeftDriveEncoderRate			= "leftDriveEncoderRate";
	public static final String ntAutonomousMode					= "/autonomous/modes";
	public static final String ntPincher						= "pincher";
	public static final String ntPopper							= "popper";
	public static final String ntElevatorHeight					= "elevatorHeight";
	public static final String ntGear							= "gear";
	public static final String ntShootCubeDelay					= "shootCubeDelay";
	public static final String ntMedianSmoothingReadings		= "medianSmoothingReadings";
	public static final String ntAutonomousRangefinderDistance  = "autonomousRangefinderDistance";
	public static final String ntClimbArmSpeed					= "climbArmSpeed";
	public static final String ntBot							= "bot";

	/** Network Table Values **/
	public static AutonomousModes AUTONOMOUS_MODE				= AutonomousModes.CROSS_LINE;
	public static double JOYSTICK_DEADZONE 						= 0.05;
	public static double JOYSTICK_SENSITIVITY 					= 1.0;
	public static double JOYSTICK_EQUALITY_THRESHHOLD			= 0.05;
	public static double SPEED 									= 1.0;
	public static double AUTONOMOUS_RANGEFINDER_DISTANCE 		= 25;
	public static double KP 									= 0.01;
	public static int MEDIAN_SMOOTHING_READINGS					= 15;
	public static Pincher PINCHER_STATUS						= RobotMap.Pincher.CLOSED;
	public static Popper POPPER_STATUS							= RobotMap.Popper.RETRACTED;
	public static double ELEVATOR_HEIGHT						= 0.0;
	public static Gear CURRENT_GEAR                             = Gear.LOW;
	public static double SHOOT_CUBE_DELAY						= 0.1;
	public static double CLIMB_ARM_SPEED						= 0.1;

}
