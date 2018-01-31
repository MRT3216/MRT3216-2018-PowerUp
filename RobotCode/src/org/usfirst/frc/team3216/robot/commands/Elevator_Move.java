package org.usfirst.frc.team3216.robot.commands;

import org.usfirst.frc.team3216.robot.OI;
import org.usfirst.frc.team3216.robot.Robot;
import org.usfirst.frc.team3216.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Elevator_Move extends Command {
	Elevator elevator = Robot.elevator;
	OI oi = Robot.oi;


    public Elevator_Move() {
        requires(elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	elevator.stop();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double power = oi.getStickY();
    	elevator.setPower(power);
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
