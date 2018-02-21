package org.usfirst.frc.team3216.robot.commands;

import org.usfirst.frc.team3216.lib.Logger;
import org.usfirst.frc.team3216.robot.Robot;
import org.usfirst.frc.team3216.robot.RobotMap;
import org.usfirst.frc.team3216.robot.subsystems.Winch;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Winch_Stop extends Command {
	private static final Logger.Level LOG_LEVEL = RobotMap.LOG_WINCH;

	Logger log = new Logger(LOG_LEVEL, getName());

	Winch winch = Robot.winch;

	public Winch_Stop() {
		// Use requires() here to declare subsystem dependencies
		requires(winch);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		winch.stop();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		log.add("Winch Stopped", LOG_LEVEL);
		winch.stop();

	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
