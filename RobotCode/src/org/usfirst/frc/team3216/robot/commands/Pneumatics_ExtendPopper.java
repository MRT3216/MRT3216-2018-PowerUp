package org.usfirst.frc.team3216.robot.commands;

import org.usfirst.frc.team3216.lib.Logger;
import org.usfirst.frc.team3216.robot.Robot;
import org.usfirst.frc.team3216.robot.RobotMap;
import org.usfirst.frc.team3216.robot.subsystems.Pneumatics;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Pneumatics_ExtendPopper extends Command {
	private static final Logger.Level LOG_LEVEL = RobotMap.LOG_PNEUMATICS;
	Logger log = new Logger(RobotMap.LOG_PNEUMATICS, "ExtendPopper");

	Pneumatics pneumatics = Robot.pneumatics;

	public Pneumatics_ExtendPopper() {
		log.add("Contructor", LOG_LEVEL);
		requires(pneumatics);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		log.add("Initialize", LOG_LEVEL);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		log.add("Execute", LOG_LEVEL);
		pneumatics.openPopper();
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		log.add("End", LOG_LEVEL);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
