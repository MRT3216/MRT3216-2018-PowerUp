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
	Timer timer = new Timer();
	double currentAngle;
	double angleAdjustment;
	
    public Drivetrain_ArcadeDrive() {
    	log.add("Constructor", Logger.Level.TRACE);
    	
    	requires(drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	log.add("Initialize", Logger.Level.TRACE);
    	
    	drivetrain.stop();
		throttleOld = 0.0;

		
		timer.start();
		timer.reset();
    }
        

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		double throttle = oi.getLeftY();
		double turn = oi.getRightX();
		if(turn == 0) {
			driveStraight(throttle, imu.getAngleZ());
			
		}
		execute(throttle, turn);
		
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
    	currentAngle = imu.getAccelZ();
    	angleAdjustment = heading - currentAngle;
    	double turn = angleAdjustment * RobotMap.KP;
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
