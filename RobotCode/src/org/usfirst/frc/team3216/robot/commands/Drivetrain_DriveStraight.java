package org.usfirst.frc.team3216.robot.commands;

import org.usfirst.frc.team3216.lib.Logger;
import org.usfirst.frc.team3216.robot.OI;
import org.usfirst.frc.team3216.robot.Robot;
import org.usfirst.frc.team3216.robot.RobotMap;
import org.usfirst.frc.team3216.robot.subsystems.ADIS16448_IMU;
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
	ADIS16448_IMU staticIMU = Robot.staticIMU;
	
	
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
		//double leftPower = oi.getDriveLeft();
		//double rightPower = oi.getDriveRight();
		
		
		//Just for testing purposes 
		//execute(leftPower, rightPower);
		execute(0.1, 0.1);
    }
    
    protected void execute(double leftPower, double rightPower) {
		double dt = timer.get();
		timer.reset();
		double angle = staticIMU.getAngleZ();
		angle = adjustAngle(angle);
		leftPower = Drivetrain_TankDrive.restrictAcceleration(leftPower, leftPowerOld, dt);
		rightPower = Drivetrain_TankDrive.restrictAcceleration(rightPower, rightPowerOld, dt);	
		
		
		drivetrain.setPower(leftPower, angle*RobotMap.KP*rightPower, true);
		
		leftPowerOld = leftPower;
		rightPowerOld = rightPower;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }
    
    //adjusts angle to be on interval [-180, 180]
    protected double adjustAngle(double angle) {
    	angle = angle % 360;
    	log.add("Input Angle: " + angle, Logger.Level.TRACE);
    	if(angle >= 180) 	{angle = angle - 360;}
    	else if(angle < -180) {angle = 360 + angle;}
    	log.add("Output Angle: " + angle, Logger.Level.TRACE);
    	return angle;
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
