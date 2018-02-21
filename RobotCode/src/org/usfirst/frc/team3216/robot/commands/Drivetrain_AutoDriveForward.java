package org.usfirst.frc.team3216.robot.commands;

import org.usfirst.frc.team3216.robot.Robot;
import org.usfirst.frc.team3216.robot.subsystems.RangeFinder;

/**
 *
 */
public class Drivetrain_AutoDriveForward extends Drivetrain_Drive {
	protected RangeFinder rangeFinder = Robot.rangeFinder;
	protected double initialHeading = 0;
	double distance;

	public Drivetrain_AutoDriveForward(double distance) {
		super();
		this.distance = distance;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		super.initialize();

		initialHeading = imu.getAngleZ();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		log.add(Double.toString(rangeFinder.getSmoothedDistancedInInches()), LOG_LEVEL);

		if (rangeFinder.getSmoothedDistancedInInches() > distance) {
			driveStraight(-.5, initialHeading);
		} else {
			drivetrain.setPower(0, 0);
		}
	}

	@Override
	protected boolean isFinished() {
		return (rangeFinder.getSmoothedDistancedInInches() <= distance);
	}
}
