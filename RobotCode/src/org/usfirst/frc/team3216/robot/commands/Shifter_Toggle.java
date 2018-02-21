package org.usfirst.frc.team3216.robot.commands;

import org.usfirst.frc.team3216.lib.Logger;
import org.usfirst.frc.team3216.robot.Robot;
import org.usfirst.frc.team3216.robot.RobotMap;
import org.usfirst.frc.team3216.robot.RobotMap.Gear;
import org.usfirst.frc.team3216.robot.subsystems.Shifter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Shifter_Toggle extends Command {
	private static final Logger.Level LOG_LEVEL = RobotMap.LOG_SHIFTER;
	Logger log = new Logger(LOG_LEVEL, "Shifter");
	private Shifter shifter = Robot.shifter;

	public Shifter_Toggle() {
		requires(shifter);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		log.add("Initialize", LOG_LEVEL);

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if (shifter.getGear() == Gear.HIGH) {
			shifter.shiftDown();
		} else {
			shifter.shiftUp();
		}
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
