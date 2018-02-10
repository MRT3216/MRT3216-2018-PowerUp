package org.usfirst.frc.team3216.robot.commands;

import org.usfirst.frc.team3216.lib.Logger;
import org.usfirst.frc.team3216.robot.Robot;
import org.usfirst.frc.team3216.robot.RobotMap;
import org.usfirst.frc.team3216.robot.subsystems.ClimbArm;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimbArm_Rotate extends Command {

	private static final Logger.Level LOG_LEVEL = RobotMap.LOG_CLIMB_ARM;
	
	Logger log = new Logger(LOG_LEVEL, getName());
	ClimbArm climbArm = Robot.climbArm;
	private double degrees;
	private double encoderValue;
	double motorSpeed = RobotMap.CLIMB_ARM_SPEED;

    public ClimbArm_Rotate(double degrees) {
        requires(climbArm);
        this.degrees = degrees;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	encoderValue = 0; //Once we get the encoder for the arm working, we need to set this to the value of the encoder rotation
    	if(degrees > 0) { //checks if it is positive
	    	if(encoderValue < degrees) { //if the arm needs to continue rotating forwards
	    		climbArm.setPower(motorSpeed);
	    	}
	    	else { //stop arm when it reaches or passes goal
	    		climbArm.stop();
	    	}
    	}
    	else if (degrees > 0) { //checks if it is negative
    		if(encoderValue > degrees) { //if the arm needs to continue rotating backwards
    			climbArm.setPower(-1 * motorSpeed);
    		}
    		else { //stop arm when it reaches or passes goal
    			climbArm.stop();
    		}
    	}
    	else { //only called if degrees = 0
    		climbArm.stop();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
