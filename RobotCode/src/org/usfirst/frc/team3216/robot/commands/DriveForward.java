package org.usfirst.frc.team3216.robot.commands;

import org.usfirst.frc.team3216.lib.Logger;
import org.usfirst.frc.team3216.robot.OI;
import org.usfirst.frc.team3216.robot.Robot;
import org.usfirst.frc.team3216.robot.RobotMap;
import org.usfirst.frc.team3216.robot.subsystems.Drivetrain;
import org.usfirst.frc.team3216.robot.subsystems.RangeFinder;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForward extends Command {
	private static final Logger.Level LOG_LEVEL = RobotMap.LOG_DRIVEFORWARD;
	
	Logger log = new Logger(LOG_LEVEL, getName());
	Drivetrain drivetrain = Robot.drivetrain;
	OI oi = Robot.oi;
	Timer timer = new Timer();
	RangeFinder rangeFinder = Robot.rangeFinder;
	
    public DriveForward() {    	
    	requires(drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	drivetrain.stop();
    	
    	timer.reset();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	log.add(Double.toString(rangeFinder.getAverageDistanceInInches()), Logger.Level.TRACE);
    	if(rangeFinder.getAverageDistanceInInches() > 25) {
    		drivetrain.setPower(0.1, .1);
    	}
    	else {
    		drivetrain.setPower(0,0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	terminate();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	terminate();
    }
    
	/** Graceful End **********************************************************/
    private void terminate() {
		drivetrain.stop();
	} 
}
