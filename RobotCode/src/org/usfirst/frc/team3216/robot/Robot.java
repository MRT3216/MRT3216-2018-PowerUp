
package org.usfirst.frc.team3216.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc.team3216.lib.Logger;
import org.usfirst.frc.team3216.robot.commands.Drivetrain_DriveStraight;
import org.usfirst.frc.team3216.robot.subsystems.ADIS16448_IMU;
import org.usfirst.frc.team3216.robot.subsystems.Drivetrain;
import org.usfirst.frc.team3216.robot.subsystems.Pneumatics;
import org.usfirst.frc.team3216.robot.subsystems.RangeFinder;

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
	public static final Drivetrain drivetrain = new Drivetrain();
	public static final RangeFinder rangeFinder = new RangeFinder();
	public static final Encoder leftEncoder = 
			new Encoder(RobotMap.LEFT_ENCODER_CHANNEL_A, RobotMap.LEFT_ENCODER_CHANNEL_B);
	public static OI oi;
	public static ADIS16448_IMU imu;
	public static Pneumatics pneumatics; 
	
	Command autonomousCommand;

	static {
		if(RobotMap.currentBot == RobotMap.Bot.MAINBOT) {
			pneumatics = new Pneumatics();
		}
	}
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		log.add("Robot Init", LOG_LEVEL);
		
		// Get the camera instance
		CameraServer camServer = CameraServer.getInstance();
		// Check if the camera is actually attached
		if(camServer != null) {
			camServer.startAutomaticCapture();
		}
		
		oi = new OI();			
		imu = new ADIS16448_IMU();
		imu.calibrate();
		imu.reset();	
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
		
		autonomousCommand = new Drivetrain_DriveStraight();

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
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		//log.add("X: " + imu.getAngleX() + " Y: " + imu.getAngleY() + " Z: " + imu.getAngleZ() + "/n Angle: " + imu.getAngle(), Logger.Level.TRACE);
		log.add("Example: " + RobotMap.EXAMPLE, Logger.Level.TRACE);
		log.add("Deadzone: " + RobotMap.JOYSTICK_DEADZONE, Logger.Level.TRACE);
		syncWithNetworkTables();
		Scheduler.getInstance().run();
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
		RobotMap.JOYSTICK_DEADZONE = 
				settings.getEntry(RobotMap.ntDeadzone).getDouble(RobotMap.JOYSTICK_DEADZONE);
		RobotMap.JOYSTICK_SENSITIVITY = 
				settings.getEntry(RobotMap.ntSensitivity).getDouble(RobotMap.JOYSTICK_SENSITIVITY);
		RobotMap.SPEED = 
				settings.getEntry(RobotMap.ntSpeed).getDouble(RobotMap.SPEED);
		RobotMap.KP = 
				settings.getEntry(RobotMap.ntKP).getDouble(RobotMap.KP);
		RobotMap.EXAMPLE = 
				settings.getEntry(RobotMap.ntExampleVariable).getBoolean(RobotMap.EXAMPLE);
		
				
		/** Write to NetworkTable **/		
		settings.getEntry(RobotMap.ntRangeFinderDistance).setDouble(rangeFinder.getDistanceInInches());
		settings.getEntry(RobotMap.ntRangeFinderAverageDistance).setDouble(rangeFinder.getAverageDistanceInInches());
		settings.getEntry(RobotMap.ntEncoderDistance).setDouble(leftEncoder.getDistance());
		settings.getEntry(RobotMap.ntEncoderRate).setDouble(leftEncoder.getRate());
		settings.getEntry(RobotMap.ntAutonomousMode).setString(RobotMap.AUTONOMOUS_MODE.name());
	}
}
