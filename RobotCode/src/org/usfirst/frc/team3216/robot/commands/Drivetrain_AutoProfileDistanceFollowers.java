package org.usfirst.frc.team3216.robot.commands;

import java.io.File;

import org.usfirst.frc.team3216.lib.Logger;
import org.usfirst.frc.team3216.robot.Robot;
import org.usfirst.frc.team3216.robot.RobotMap;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.DistanceFollower;

/** 
 * 
 */
public class Drivetrain_AutoProfileDistanceFollowers extends Drivetrain_Drive {
	private static final Logger.Level LOG_LEVEL = RobotMap.LOG_AUTOPROFILE;
	private Logger log = new Logger(LOG_LEVEL, getName());
	private DistanceFollower followerLeft;
	private DistanceFollower followerRight;
	private int count = 0;

	public Drivetrain_AutoProfileDistanceFollowers() {
		super();
		log.add("Constructor", LOG_LEVEL);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		super.initialize();
		// Reset the values of the encoder
		Robot.leftEncoder.reset();
		Robot.rightEncoder.reset();
		// Reset doesn't take a long time, calibrate does
		imu.reset();
		log.add("About to read files", LOG_LEVEL);
		// Read in the Trajectory for the left and right
		File leftFile = new File(RobotMap.TRAJECTORY_LEFTCSV);
		File rightFile = new File(RobotMap.TRAJECTORY_RIGHTCSV);
		Trajectory left = Pathfinder.readFromCSV(leftFile);
		Trajectory right = Pathfinder.readFromCSV(rightFile);

		// Create a distance follower to follow the the path
		followerLeft = new DistanceFollower(left);
		followerRight = new DistanceFollower(right);

		// Get the velocity
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
		followerLeft.configurePIDVA(1.0, 0.0, 0.0, 1 / max_velocity, 0);
		followerRight.configurePIDVA(1.0, 0.0, 0.0, 1 / max_velocity, 0);

		log.add("initialized", LOG_LEVEL);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		// Increment the count (how many times this has been run)
		count++;

		// Get the distance that each encoder has traveled
		double encoder_distance_left = Robot.leftEncoder.getDistanceInFeet();
		double encoder_distance_right = Robot.rightEncoder.getDistanceInFeet();
		log.add("Count: " + count, LOG_LEVEL);
		log.add("Enc distance left: " + encoder_distance_left, LOG_LEVEL);
		log.add("Enc distance right: " + encoder_distance_right, LOG_LEVEL);
		log.add("L Seg: " + followerLeft.getSegment().position, LOG_LEVEL);
		log.add("R Seg: " + followerRight.getSegment().position, LOG_LEVEL);

		// Read from both of the followers
		double l = followerLeft.calculate(encoder_distance_left);
		double r = followerRight.calculate(encoder_distance_right);

		// Get the current angle from the IMU (in degrees)
		double gyro_heading = (-1 * Robot.imu.getAngleZ()) % 360;
		gyro_heading = (gyro_heading < 0) ? 360 + gyro_heading : gyro_heading;

		// Get the heading that the the robot should be at
		double desired_heading = Pathfinder.r2d(followerLeft.getHeading()); // Should also be in degrees

		// Calculate the difference between the desired heading and the actual heading
		double angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading);
		// Calculate the turn (??? ask Jaci ???)
		double turn = 0.8 * (-1.0 / 80.0) * angleDifference;
		log.add("gyro_heading: " + gyro_heading, LOG_LEVEL);
		log.add("desired_heading: " + desired_heading, LOG_LEVEL);
		log.add("turn: " + turn, LOG_LEVEL);
		log.add("l: " + l, LOG_LEVEL);
		log.add("r: " + r, LOG_LEVEL);
		log.add("l - turn: " + (l - turn), LOG_LEVEL);
		log.add("r + turn: " + (r + turn), LOG_LEVEL);

		// Set the power to each motor
		// IMPORTANT: For some reason this has to be sent in backwards (r is sent to
		// left motor, r is sent to right)
		// No idea why, but for now it just is?
		drivetrain.setPower((r - turn), (l + turn));
	}

	@Override
	protected boolean isFinished() {
		return followerLeft.isFinished() && followerRight.isFinished();
	}
}
