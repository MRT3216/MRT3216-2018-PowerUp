package org.usfirst.frc.team3216.robot.commands;

import org.usfirst.frc.team3216.lib.Logger;
import org.usfirst.frc.team3216.robot.Robot;
import org.usfirst.frc.team3216.robot.RobotMap;
import org.usfirst.frc.team3216.robot.subsystems.ClimbArm;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimbArm_GoForward extends Command {

	private static final Logger.Level LOG_LEVEL = RobotMap.LOG_CLIMB_ARM;

	Logger log = new Logger(LOG_LEVEL, getName());
	ClimbArm climbArm = Robot.climbArm;

	public ClimbArm_GoForward() {
		// Use requires() here to declare subsystem dependencies
		requires(climbArm);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		climbArm.setPower(RobotMap.CLIMB_ARM_SPEED);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
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
