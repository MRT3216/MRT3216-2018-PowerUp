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
public class Drivetrain_ArcadeDrive extends Command {
	/** Configuration Constants ***********************************************/
	private static final Logger.Level LOG_LEVEL = RobotMap.LOG_DRIVETRAIN;
	
	/** Instance Variables ****************************************************/
	Logger log = new Logger(LOG_LEVEL, getName());
	Drivetrain drivetrain = Robot.drivetrain;
	OI oi = Robot.oi;
	ADIS16448_IMU imu = Robot.imu;
	double throttleOld;
	double angleOld;
	Timer timer = new Timer();
	double currentAngle;
	double angleAdjustment;
	double heading;
	boolean hasHeading;
	
    public Drivetrain_ArcadeDrive() {
    	log.add("Constructor", LOG_LEVEL);
    	
    	requires(drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	log.add("Initialize", LOG_LEVEL);
    	
    	drivetrain.stop();
		throttleOld = 0.0;
		angleOld = 0.0;
		hasHeading = false;
		
		timer.start();
		timer.reset();
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
    
    protected void execute(double throttle, double turn) {
    	throttle *= -1;
		double dt = timer.get();
		timer.reset();
		throttle = restrictAcceleration(throttle, throttleOld, dt);	
		
		drivetrain.setPower((throttle-turn)/2, (throttle+turn)/2);
		
		throttleOld = throttle;
    }
    
    protected void driveStraight(double throttle, double heading) {
    	currentAngle = imu.getAngleZ();
    	angleAdjustment = heading - currentAngle;
		log.add("Heading: " + heading, LOG_LEVEL);
		log.add("Current: " + currentAngle, LOG_LEVEL);
		log.add("Adjustment: " + angleAdjustment, LOG_LEVEL);

    	double turn = angleAdjustment * RobotMap.KP;
    	log.add("Turn: " + turn, LOG_LEVEL);
    	execute(throttle, turn);    	
    }
    
	/** restrictAcceleration **************************************************/
	public static double restrictAcceleration(double goalPower, 
			double currentPower, double dt) {
		double maxDeltaPower = RobotMap.ACCELERATION_MAX * dt;
		double deltaPower = Math.abs(goalPower - currentPower);
		double deltaSign = (goalPower < currentPower) ? -1.0 : 1.0;
		
		deltaPower = Math.min(maxDeltaPower, deltaPower);
		goalPower = currentPower + deltaSign * deltaPower;

		return goalPower;
	}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }    
   
    // Called once after isFinished returns true
    protected void end() {
    	log.add("End", LOG_LEVEL);
    	terminate();
    }    

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	log.add("Interrupted", LOG_LEVEL);
    	terminate();
    }
    
	/** Graceful End **********************************************************/
	private void terminate() {
		drivetrain.stop();
	}    
}
