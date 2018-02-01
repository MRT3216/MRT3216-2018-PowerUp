package org.usfirst.frc.team3216.robot.commands;

import org.usfirst.frc.team3216.lib.Logger;
import org.usfirst.frc.team3216.robot.RobotMap;

/**
 *
 */
public class Drivetrain_ArcadeDrive extends Drivetrain_Drive {
    public Drivetrain_ArcadeDrive() {
    	super();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		double throttle = oi.getLeftY();
		double turn = oi.getRightX();
		log.add("turning: " + turn, Logger.Level.TRACE);
		if(turn == 0 && !hasHeading && Math.abs(imu.getAngleZ()-angleOld) < RobotMap.TURN_RATE_THRESHOLD) {
			hasHeading = true;
			heading = imu.getAngleZ();
		}
		angleOld = imu.getAngleZ();

		if(turn == 0 && throttle != 0) {
			driveStraight(throttle, heading);			
		}
		else {
			hasHeading = false;
			execute(throttle, turn);
		}
    }
}
