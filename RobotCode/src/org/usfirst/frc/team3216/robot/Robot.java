package org.usfirst.frc.team3216.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc.team3216.lib.Logger;
import org.usfirst.frc.team3216.robot.RobotMap.AutonomousModes;
import org.usfirst.frc.team3216.robot.RobotMap.StartingPositions;
import org.usfirst.frc.team3216.robot.commands.Drivetrain_AutoDriveForward;
import org.usfirst.frc.team3216.robot.subsystems.ADIS16448_IMU;
import org.usfirst.frc.team3216.robot.subsystems.Drivetrain;
import org.usfirst.frc.team3216.robot.subsystems.Elevator;
import org.usfirst.frc.team3216.robot.subsystems.BaseEncoder;
import org.usfirst.frc.team3216.robot.subsystems.ClimbArm;
import org.usfirst.frc.team3216.robot.subsystems.AirCompressor;
import org.usfirst.frc.team3216.robot.subsystems.Pneumatics;
import org.usfirst.frc.team3216.robot.subsystems.RangeFinder;
import org.usfirst.frc.team3216.robot.subsystems.Shifter;
import org.usfirst.frc.team3216.robot.subsystems.Winch;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	/** Configuration Constants ***********************************************/
	private static final Logger.Level LOG_LEVEL = RobotMap.LOG_ROBOT;
	
	/** Create Subsystems *****************************************************/
	private Logger log = new Logger(LOG_LEVEL, "Robot");
	public static DigitalInput topSwitch;	//= new DigitalInput(RobotMap.DIO_TOP_SWITCH);
	public static DigitalInput bottomSwitch; // = new DigitalInput(RobotMap.DIO_BOTTOM_SWITCH);
	public static Elevator elevator; // = new Elevator();

	public static AirCompressor airCompressor; // = new AirCompressor();
	public static Drivetrain drivetrain;
	public static ClimbArm climbArm; // = new ClimbArm();
	public static Winch winch; // = new Winch();
	public static final RangeFinder rangeFinder = new RangeFinder();
	public static final BaseEncoder leftEncoder = 
			new BaseEncoder(RobotMap.LEFT_ENCODER_CHANNEL_A, RobotMap.LEFT_ENCODER_CHANNEL_B, "Left Encoder", false);
	

	public static final BaseEncoder rightEncoder = 
			new BaseEncoder(RobotMap.RIGHT_ENCODER_CHANNEL_A, RobotMap.RIGHT_ENCODER_CHANNEL_B, "Right Encoder", true);	
	
	public static Pneumatics pneumatics = new Pneumatics(); 
	public static Shifter shifter = new Shifter();
	public static ADIS16448_IMU imu;
	public static OI oi;

	StartingPositions startingPosition = RobotMap.STARTING_POSITION;
	AutonomousModes autonomousMode = RobotMap.AUTONOMOUS_MODE;
	
	Command autonomousCommand;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {		
		log.add("Robot Init", LOG_LEVEL);
		
		drivetrain = new Drivetrain();

		if(RobotMap.hasIMU) {
			imu = new ADIS16448_IMU();
			imu.calibrate();
			imu.reset();	
		}	
		
		if(RobotMap.hasElevator) {
			elevator = new Elevator();
			topSwitch = new DigitalInput(RobotMap.DIO_TOP_SWITCH);
			bottomSwitch = new DigitalInput(RobotMap.DIO_BOTTOM_SWITCH);
		}
		
		if(RobotMap.hasWinch) {
			winch = new Winch();
		}
		
		if(RobotMap.hasPneumatics) {
			airCompressor  = new AirCompressor();
		}
		
		if(RobotMap.hasClimbArm) {
			 climbArm = new ClimbArm();
		}
		
		oi  = new OI();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		log.add("Disabled Init", LOG_LEVEL);
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		log.add("Autonomous Init", LOG_LEVEL);
		
		switch(autonomousMode) {
		
		case CROSS_LINE: 
			if(startingPosition == StartingPositions.CENTER) {
				autonomousCommand = new Drivetrain_AutoDriveForward(25); //For now it drives straight, but when we have 
	   																	 //motion profiling working, we'll want to have it 
																		 //turn and go around the center stack of cubes.

			}
			else {
				autonomousCommand = new Drivetrain_AutoDriveForward(25);

			}
			
		case SWITCH:
			if(startingPosition == StartingPositions.LEFT) {
				
			}
			else if(startingPosition == StartingPositions.CENTER) {
				
			}
			else {
				
			}
		
		case SCALE:
			
		}
		

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		syncWithNetworkTables();
		Scheduler.getInstance().run();
		log.add("autonomousRangeFinderDistance: " + RobotMap.AUTONOMOUS_RANGEFINDER_DISTANCE, LOG_LEVEL);
	}

	@Override
	public void teleopInit() {
		log.add("Teleop Init", LOG_LEVEL); 		
		
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
		leftEncoder.initEncoder();
		rightEncoder.initEncoder();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		//log.add("X: " + imu.getAngleX() + " Y: " + imu.getAngleY() + " Z: " + imu.getAngleZ() + "/n Angle: " + imu.getAngle(), Logger.Level.TRACE);
		log.add("Deadzone: " + RobotMap.JOYSTICK_DEADZONE, Logger.Level.TRACE);
		syncWithNetworkTables();
		Scheduler.getInstance().run();
		
		log.add("Smoothing Readings: " + RobotMap.MEDIAN_SMOOTHING_READINGS, LOG_LEVEL);
		log.add("autonomousRangeFinderDistance: " + RobotMap.AUTONOMOUS_RANGEFINDER_DISTANCE, LOG_LEVEL);
		log.add("Climb Speed: " + RobotMap.CLIMB_ARM_SPEED, LOG_LEVEL);

	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
	
	public static void syncWithNetworkTables() {
		NetworkTableInstance defaultTable =  NetworkTableInstance.getDefault();
		NetworkTable settings = defaultTable.getTable(RobotMap.networkTableName);
		
		/** Read from NetworkTable **/
		// Control Settings
		RobotMap.JOYSTICK_DEADZONE = 
				settings.getEntry(RobotMap.ntDeadzone).getDouble(RobotMap.JOYSTICK_DEADZONE);
		RobotMap.JOYSTICK_SENSITIVITY = 
				settings.getEntry(RobotMap.ntSensitivity).getDouble(RobotMap.JOYSTICK_SENSITIVITY);
		RobotMap.DRIVESTRAIGHT_KP = 
				settings.getEntry(RobotMap.ntDriveStraightKP).getDouble(RobotMap.DRIVESTRAIGHT_KP);			
		// Rangefinder
		double m = 
				settings.getEntry(RobotMap.ntMedianSmoothingReadings).getDouble(RobotMap.MEDIAN_SMOOTHING_READINGS);
		RobotMap.MEDIAN_SMOOTHING_READINGS = (int) m;
		// Robot Status
		RobotMap.currentBot = 
				RobotMap.Bot.valueOf(settings.getEntry(RobotMap.ntBot).getString(RobotMap.currentBot.name()));
		// Robot Settings
		RobotMap.CLIMB_ARM_SPEED = 
				settings.getEntry(RobotMap.ntClimbArmSpeed).getDouble(RobotMap.CLIMB_ARM_SPEED);		
		RobotMap.ELEVATOR_THRESHOLD =
				settings.getEntry(RobotMap.ntElevatorThreshold).getDouble(RobotMap.ELEVATOR_THRESHOLD);		
		// Autonomous
		RobotMap.AUTONOMOUS_MODE = 
				RobotMap.AutonomousModes.valueOf(settings.getEntry(RobotMap.ntAutonomousMode).getString(RobotMap.AUTONOMOUS_MODE.name()));	
		RobotMap.STARTING_POSITION = 
				RobotMap.StartingPositions.valueOf(settings.getEntry(RobotMap.ntStartingPosition).getString(RobotMap.STARTING_POSITION.name()));	
						
		/** Write to NetworkTable **/		
		settings.getEntry(RobotMap.ntRangeFinderDistance).setDouble(rangeFinder.getDistanceInInches());
		settings.getEntry(RobotMap.ntRangeFinderAverageDistance).setDouble(rangeFinder.getSmoothedDistancedInInches());
		settings.getEntry(RobotMap.ntLeftDriveEncoderDistance).setDouble(leftEncoder.getDistance());
		settings.getEntry(RobotMap.ntRightDriveEncoderDistance).setDouble(rightEncoder.getDistance());
		settings.getEntry(RobotMap.ntAutonomousMode).setString(RobotMap.AUTONOMOUS_MODE.name());
		settings.getEntry(RobotMap.ntPincher).setString(RobotMap.PINCHER_STATUS.name());
		settings.getEntry(RobotMap.ntPopper).setString(RobotMap.POPPER_STATUS.name());
		settings.getEntry(RobotMap.ntElevatorHeight).setDouble(RobotMap.ELEVATOR_HEIGHT);
		settings.getEntry(RobotMap.ntGear).setString(RobotMap.CURRENT_GEAR.name());
		settings.getEntry(RobotMap.ntTime).setDouble(DriverStation.getInstance().getMatchTime());
		settings.getEntry(RobotMap.ntColor).setString(DriverStation.getInstance().getAlliance().name());
	}
}
