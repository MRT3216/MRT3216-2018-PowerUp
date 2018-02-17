package org.usfirst.frc.team3216.robot.commands;

import org.usfirst.frc.team3216.lib.Logger;
import org.usfirst.frc.team3216.robot.Robot;
import org.usfirst.frc.team3216.robot.RobotMap;
import org.usfirst.frc.team3216.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Elevator_GoTo extends Command {
	private static final Logger.Level LOG_LEVEL = RobotMap.LOG_ELEVATOR;
	
	Logger log = new Logger(LOG_LEVEL, getName());
	Elevator elevator = Robot.elevator;
	double threshold = RobotMap.ELEVATOR_THRESHOLD;
	
	double height;
    public Elevator_GoTo(double height) {
        requires(elevator);
        this.height = Math.abs(height);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	elevator.stop();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/*if((elevatorEncoder < height) && ((height - elevatorEncoder.getDistance()) < threshold)) {
    		elevator.setPower(1);
    	}
    	else if((elevatorEncoder > height) && (((elevatorEncoder.getDistance()) - height) < threshold)) {
    		elevator.setPower(-1);
    	}
    	else {
    		elevator.stop();
    	}
    	*/
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;//(Math.abs(elevatorEncoder.getDistance() - height) < threshold) ;
    }

    // Called once after isFinished returns true
    protected void end() {
    	elevator.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	elevator.stop();
    }
}
