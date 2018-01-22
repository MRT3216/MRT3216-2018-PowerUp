package org.usfirst.frc.team3216.robot.commands;

import org.usfirst.frc.team3216.lib.Logger;
import org.usfirst.frc.team3216.robot.OI;
import org.usfirst.frc.team3216.robot.Robot;
import org.usfirst.frc.team3216.robot.RobotMap;
import org.usfirst.frc.team3216.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Drivetrain_DriveStraight extends Command {
	/** Configuration Constants ***********************************************/
	private static final Logger.Level LOG_LEVEL = RobotMap.LOG_DRIVETRAIN_DRIVESTRAIGHT;
	
	/** Instance Variables ****************************************************/
	Logger log = new Logger(LOG_LEVEL, getName());
	Drivetrain drivetrain = Robot.drivetrain;
	OI oi = Robot.oi;
	double leftPowerOld, rightPowerOld;
	Timer timer = new Timer();
	
	
    public Drivetrain_DriveStraight() {
    	log.add("Constructor", Logger.Level.TRACE);
    	
    	requires(drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	log.add("Initialize", Logger.Level.TRACE);
    	
    	drivetrain.stop();
		leftPowerOld = 0.0;
		rightPowerOld = 0.0;
		
		timer.start();
		timer.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		double leftPower = oi.getDriveLeft();
		double rightPower = oi.getDriveRight();
		 
		execute(leftPower, rightPower);
    }
    
    protected void execute(double leftPower, double rightPower) {
		double dt = timer.get();
		timer.reset();
		leftPower = Drivetrain_TankDrive.restrictAcceleration(leftPower, leftPowerOld, dt);
		rightPower = Drivetrain_TankDrive.restrictAcceleration(rightPower, rightPowerOld, dt);	
		
		drivetrain.setPower(leftPower, rightPower, true);
		
		leftPowerOld = leftPower;
		rightPowerOld = rightPower;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	log.add("End", Logger.Level.TRACE);
    	terminate();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	log.add("Interrupted", Logger.Level.TRACE);
    	terminate();
    }
    
	/** Graceful End **********************************************************/
	private void terminate() {
		drivetrain.stop();
	}   
}
