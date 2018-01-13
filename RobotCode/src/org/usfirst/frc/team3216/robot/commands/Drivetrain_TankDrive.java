package org.usfirst.frc.team3216.robot.commands;

import org.usfirst.frc.team3216.robot.OI;
import org.usfirst.frc.team3216.robot.Robot;
import org.usfirst.frc.team3216.robot.RobotMap;
import org.usfirst.frc.team3216.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Drivetrain_TankDrive extends Command {
	Drivetrain drivetrain = Robot.drivetrain;
	OI oi = Robot.oi;
	double leftPowerOld, rightPowerOld;
	Timer timer = new Timer();
	
    public Drivetrain_TankDrive() {
    	requires(drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
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
		
		double dt = timer.get();
		timer.reset();
		leftPower = restrictAcceleration(leftPower, leftPowerOld, dt);
		rightPower = restrictAcceleration(rightPower, rightPowerOld, dt);	
		
		drivetrain.setPower(leftPower, rightPower);
		
		leftPowerOld = leftPower;
		rightPowerOld = rightPower;
    }
    
	/** restrictAcceleration **************************************************/
	private double restrictAcceleration(double goalPower, 
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
    	drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
