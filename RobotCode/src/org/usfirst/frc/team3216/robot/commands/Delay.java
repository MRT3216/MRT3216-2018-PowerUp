package org.usfirst.frc.team3216.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Delay extends Command {

	public Delay(double seconds) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		setTimeout(seconds);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
