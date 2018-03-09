package org.usfirst.frc.team3216.robot.commands;

import java.io.File;

import org.usfirst.frc.team3216.lib.Logger;
import org.usfirst.frc.team3216.robot.Robot;
import org.usfirst.frc.team3216.robot.RobotMap;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

/**
 *
 */
public class Drivetrain_AutoProfileTest extends Drivetrain_Drive {
	private static final Logger.Level LOG_LEVEL = RobotMap.LOG_AUTOPROFILE;
	private Logger log = new Logger(LOG_LEVEL, getName());
	private Trajectory trajectory;
	private TankModifier modifier;
	private EncoderFollower encLeft;
	private EncoderFollower encRight;

	public Drivetrain_AutoProfileTest() {
		super();

		// 3 Waypoints
		Waypoint[] points = new Waypoint[] {
				// new Waypoint(-4, -1, Pathfinder.d2r(-45)), // Waypoint @ x=-4, y=-1, exit
				// angle=-45 degrees
				// new Waypoint(-2, -2, 0),
				// new Waypoint(0, 0, 0) // Waypoint @ x=0, y=0, exit angle=0 radians
				// new Waypoint(-4, 0, 0),
				new Waypoint(0, 10, 0), new Waypoint(8, 10, 0),
				// new Waypoint(2, 2, 0),
		};

		log.add("hash of waypoint list: " + points.hashCode(), LOG_LEVEL);

		// Create the Trajectory Configuration
		//
		// Arguments:
		// Fit Method: HERMITE_CUBIC or HERMITE_QUINTIC
		// Sample Count: SAMPLES_HIGH (100 000)
		// SAMPLES_LOW (10 000)
		// SAMPLES_FAST (1 000)
		// Time Step: 0.05 Seconds
		// Max Velocity: 1.7 m/s
		// Max Acceleration: 2.0 m/s/s
		// Max Jerk: 60.0 m/s/s/s
		Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC,
				Trajectory.Config.SAMPLES_HIGH, 0.05, RobotMap.MAX_VELOCITY, 3.0, 60.0);
		/*
		 * File saveFile = new File(RobotMap.TRAJECTORY_CACHE); if(saveFile.exists() &&
		 * !saveFile.isDirectory()) {
		 * log.add("trajectory file already found, usng cached paths", LOG_LEVEL);
		 * trajectory = Pathfinder.readFromFile(saveFile); } else {
		 * log.add("trajectory file not found, generating new path", LOG_LEVEL);
		 * trajectory = Pathfinder.generate(points, config);
		 * log.add("saving path to cache", LOG_LEVEL);
		 * //Pathfinder.writeToFile(saveFile,trajectory); }
		 */
		File saveFile = new File(RobotMap.TRAJECTORY_CSV);
		trajectory = Pathfinder.generate(points, config);
		log.add(saveFile.getAbsolutePath(), LOG_LEVEL);
		Pathfinder.writeToCSV(saveFile, trajectory);
		log.add("Trajectory generated:", LOG_LEVEL);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		super.initialize();
		Robot.leftEncoder.reset();
		Robot.rightEncoder.reset();
		imu.reset();

		// The distance between the left and right sides of the wheelbase
		double wheelbase_width = RobotMap.WHEEL_WIDTH_FEET;

		// Create the Modifier Object
		modifier = new TankModifier(trajectory);

		// Generate the Left and Right trajectories using the original trajectory
		// as the center
		modifier.modify(wheelbase_width);

		Trajectory left = modifier.getLeftTrajectory(); // Get the Left Side
		Trajectory right = modifier.getRightTrajectory(); // Get the Right Side

		File leftFile = new File(RobotMap.TRAJECTORY_LEFTCSV);
		File rightFile = new File(RobotMap.TRAJECTORY_RIGHTCSV);
		Pathfinder.writeToCSV(leftFile, left);
		Pathfinder.writeToCSV(rightFile, right);

		encLeft = new EncoderFollower(left);
		encRight = new EncoderFollower(right);

		int encoder_position_left = Robot.leftEncoder.getCount();
		int encoder_position_right = Robot.rightEncoder.getCount();

		// Encoder Position is the current, cumulative position of your encoder. If
		// you're using an SRX, this will be the
		// 'getEncPosition' function.
		// encoder ticks per full revolution
		// Wheel Diameter is the diameter of your wheels (or pulley for a track system)
		// in meters
		encLeft.configureEncoder(encoder_position_left, RobotMap.DRIVETRAIN_ENCODER_PULSE_PER_REVOLUTION,
				RobotMap.WHEEL_DIAMETER_FEET);
		encRight.configureEncoder(encoder_position_right, RobotMap.DRIVETRAIN_ENCODER_PULSE_PER_REVOLUTION,
				RobotMap.WHEEL_DIAMETER_FEET);

		double max_velocity = RobotMap.MAX_VELOCITY;

		// The first argument is the proportional gain. Usually this will be quite high
		// The second argument is the integral gain. This is unused for motion profiling
		// The third argument is the derivative gain. Tweak this if you are unhappy with
		// the tracking of the trajectory
		// The fourth argument is the velocity ratio. This is 1 over the maximum
		// velocity you provided in the
		// trajectory configuration (it translates m/s to a -1 to 1 scale that your
		// motors can read)
		// The fifth argument is your acceleration gain. Tweak this if you want to get
		// to a higher or lower speed quicker
		encLeft.configurePIDVA(0.9, 0.0, 0.0, 1.0 / max_velocity, 0);
		encRight.configurePIDVA(0.9, 0.0, 0.0, 1.0 / max_velocity, 0);

		log.add("initialized", LOG_LEVEL);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		// Get the current position of the encoder
		int encoder_position_left = Robot.leftEncoder.getCount();
		int encoder_position_right = Robot.rightEncoder.getCount();
		log.add("Enc pos left: " + encoder_position_left, LOG_LEVEL);
		log.add("Enc pos right: " + encoder_position_right, LOG_LEVEL);

		double l = encLeft.calculate(encoder_position_left);
		double r = encRight.calculate(encoder_position_right);

		// Assuming the gyro is giving a value in degrees
		double gyro_heading = (-1 * Robot.imu.getAngleZ()) % 360;

		gyro_heading = (gyro_heading < 0) ? 360 + gyro_heading : gyro_heading;
		double desired_heading = Pathfinder.r2d(encLeft.getHeading()); // Should also be in degrees

		double angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading);
		double turn = 0.8 * (-1.0 / 80.0) * angleDifference;

		// log.add("angleZ: " + gyro_heading, LOG_LEVEL);
		// log.add("Desired Heading: " + desired_heading, LOG_LEVEL);
		log.add("l: " + l, LOG_LEVEL);
		log.add("r: " + r, LOG_LEVEL);
		log.add("turn: " + turn, LOG_LEVEL);

		drivetrain.setPower((l + turn), (r - turn));
	}

	@Override
	protected boolean isFinished() {
		return encLeft.isFinished() && encRight.isFinished();
	}
}
