package org.usfirst.frc.team3216.robot;

import org.usfirst.frc.team3216.lib.Logger;
import org.usfirst.frc.team3216.robot.RobotMap.AutonomousModes;
import org.usfirst.frc.team3216.robot.RobotMap.StartingPositions;
import org.usfirst.frc.team3216.robot.commands.Drivetrain_AutoProfileDistanceFollowers;
import org.usfirst.frc.team3216.robot.subsystems.ADIS16448_IMU;
import org.usfirst.frc.team3216.robot.subsystems.AirCompressor;
import org.usfirst.frc.team3216.robot.subsystems.ClimbArm;
import org.usfirst.frc.team3216.robot.subsystems.Drivetrain;
import org.usfirst.frc.team3216.robot.subsystems.DrivetrainEncoder;
import org.usfirst.frc.team3216.robot.subsystems.Elevator;
import org.usfirst.frc.team3216.robot.subsystems.Pneumatics;
import org.usfirst.frc.team3216.robot.subsystems.RangeFinder;
import org.usfirst.frc.team3216.robot.subsystems.Shifter;
import org.usfirst.frc.team3216.robot.subsystems.Winch;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
	/** Configuration Constants ***********************************************/
	private static final Logger.Level LOG_LEVEL = RobotMap.LOG_ROBOT;

	/** Create Subsystems *****************************************************/
	private Logger log = new Logger(LOG_LEVEL, "Robot");
	public static DigitalInput topSwitch;
	public static DigitalInput bottomSwitch;
	public static Elevator elevator;

	public static AirCompressor airCompressor;
	public static Drivetrain drivetrain;
	public static ClimbArm climbArm;
	public static Winch winch;
	public static final RangeFinder rangeFinder = new RangeFinder();
	public static final DrivetrainEncoder leftEncoder = new DrivetrainEncoder(RobotMap.LEFT_ENCODER_CHANNEL_A,
			RobotMap.LEFT_ENCODER_CHANNEL_B, "Left Encoder", true);

	public static final DrivetrainEncoder rightEncoder = new DrivetrainEncoder(RobotMap.RIGHT_ENCODER_CHANNEL_A,
			RobotMap.RIGHT_ENCODER_CHANNEL_B, "Right Encoder", false);

	public static Pneumatics pneumatics = new Pneumatics();
	public static Shifter shifter = new Shifter();
	public static ADIS16448_IMU imu;
	public static OI oi;
	public static AutonomousChooser autonomousChooser;
	// Network tables
	private static NetworkTableInstance defaultTable = NetworkTableInstance.getDefault();
	private static NetworkTable settings = defaultTable.getTable(RobotMap.networkTableName);

	StartingPositions startingPosition = RobotMap.STARTING_POSITION;
	AutonomousModes autonomousMode = RobotMap.AUTONOMOUS_MODE;

	Command autonomousCommand;

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		log.add("Robot Init", LOG_LEVEL);

		drivetrain = new Drivetrain();

		if (RobotMap.hasIMU) {
			imu = new ADIS16448_IMU();
			imu.calibrate();
			imu.reset();
		}

		if (RobotMap.hasElevator) {
			topSwitch = new DigitalInput(RobotMap.DIO_TOP_SWITCH);
			bottomSwitch = new DigitalInput(RobotMap.DIO_BOTTOM_SWITCH);
			elevator = new Elevator();
		}

		if (RobotMap.hasWinch) {
			winch = new Winch();
		}

		if (RobotMap.hasPneumatics) {
			airCompressor = new AirCompressor();
		}

		if (RobotMap.hasClimbArm) {
			climbArm = new ClimbArm();
		}

		oi = new OI();

		autonomousCommand = new Drivetrain_AutoProfileDistanceFollowers();

		setupNetworkTableListeners();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
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
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString code to get the
	 * auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons to
	 * the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		log.add("Autonomous Init", LOG_LEVEL);

		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		/*
		 * switch(autonomousMode) { case SWITCH: autonomousCommand =
		 * autonomousChooser.Switch(gameData); case SCALE: autonomousCommand =
		 * autonomousChooser.Scale(gameData); default: autonomousCommand =
		 * autonomousChooser.Cross_Line(gameData); }
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
		shifter.shiftUp();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		// log.add("X: " + imu.getAngleX() + " Y: " + imu.getAngleY() + " Z: " +
		// imu.getAngleZ() + "/n Angle: " + imu.getAngle(), Logger.Level.TRACE);
		// log.add("Deadzone: " + RobotMap.JOYSTICK_DEADZONE, Logger.Level.TRACE);
		syncWithNetworkTables();

		log.add("Median Smoothing: " + RobotMap.MEDIAN_SMOOTHING_READINGS, LOG_LEVEL);
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}

	public void setupNetworkTableListeners() {
		settings.addEntryListener((table, key, entry, value, flags) -> {
			// log.add("Key: " + key + " Value: " + value.getValue() + " Flags: " + flags,
			// this.Log_Level);

			switch (key) {
			// Control Settings
			case RobotMap.ntDeadzone:
				RobotMap.JOYSTICK_DEADZONE = settings.getEntry(RobotMap.ntDeadzone)
						.getDouble(RobotMap.JOYSTICK_DEADZONE);
				break;
			case RobotMap.ntSensitivity:
				RobotMap.JOYSTICK_SENSITIVITY = settings.getEntry(RobotMap.ntSensitivity)
						.getDouble(RobotMap.JOYSTICK_SENSITIVITY);
				break;
			case RobotMap.ntDriveStraightKP:
				RobotMap.DRIVESTRAIGHT_KP = settings.getEntry(RobotMap.ntDriveStraightKP)
						.getDouble(RobotMap.DRIVESTRAIGHT_KP);
				break;
			// Rangefinder
			case RobotMap.ntMedianSmoothingReadings:
				double m = settings.getEntry(RobotMap.ntMedianSmoothingReadings)
						.getDouble(RobotMap.MEDIAN_SMOOTHING_READINGS);
				RobotMap.MEDIAN_SMOOTHING_READINGS = (int) m;
				break;
			// Robot Status
			case RobotMap.ntBot:
				RobotMap.currentBot = RobotMap.Bot
						.valueOf(settings.getEntry(RobotMap.ntBot).getString(RobotMap.currentBot.name()));
				break;
			// Robot Settings
			case RobotMap.ntClimbArmSpeed:
				RobotMap.CLIMB_ARM_SPEED = settings.getEntry(RobotMap.ntClimbArmSpeed)
						.getDouble(RobotMap.CLIMB_ARM_SPEED);
				break;
			case RobotMap.ntElevatorThreshold:
				RobotMap.ELEVATOR_THRESHOLD = settings.getEntry(RobotMap.ntElevatorThreshold)
						.getDouble(RobotMap.ELEVATOR_THRESHOLD);
				break;
			// Autonomous
			case RobotMap.ntAutonomousMode:
				RobotMap.AUTONOMOUS_MODE = RobotMap.AutonomousModes.valueOf(
						settings.getEntry(RobotMap.ntAutonomousMode).getString(RobotMap.AUTONOMOUS_MODE.name()));
			case RobotMap.ntStartingPosition:
				RobotMap.STARTING_POSITION = RobotMap.StartingPositions.valueOf(
						settings.getEntry(RobotMap.ntStartingPosition).getString(RobotMap.STARTING_POSITION.name()));

			}
		}, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);
	}

	public static void syncWithNetworkTables() {
		/** Write to NetworkTable **/
		settings.getEntry(RobotMap.ntRangeFinderDistance).setDouble(rangeFinder.getDistanceInInches());
		settings.getEntry(RobotMap.ntRangeFinderAverageDistance).setDouble(rangeFinder.getSmoothedDistancedInInches());
		settings.getEntry(RobotMap.ntLeftDriveEncoderDistance).setDouble(leftEncoder.getDistanceInFeet());
		settings.getEntry(RobotMap.ntRightDriveEncoderDistance).setDouble(rightEncoder.getDistanceInFeet());
		settings.getEntry(RobotMap.ntAutonomousMode).setString(RobotMap.AUTONOMOUS_MODE.name());
		settings.getEntry(RobotMap.ntPincher).setString(RobotMap.PINCHER_STATUS.name());
		settings.getEntry(RobotMap.ntPopper).setString(RobotMap.POPPER_STATUS.name());
		settings.getEntry(RobotMap.ntElevatorHeight).setDouble(RobotMap.ELEVATOR_HEIGHT);
		settings.getEntry(RobotMap.ntGear).setString(RobotMap.CURRENT_GEAR.name());
		settings.getEntry(RobotMap.ntTime).setDouble(DriverStation.getInstance().getMatchTime());
		settings.getEntry(RobotMap.ntColor).setString(DriverStation.getInstance().getAlliance().name());
		settings.getEntry(RobotMap.ntGameData).setString(DriverStation.getInstance().getGameSpecificMessage());
		settings.getEntry(RobotMap.ntYaw).setDouble(imu.getAngleZ());
	}
}
