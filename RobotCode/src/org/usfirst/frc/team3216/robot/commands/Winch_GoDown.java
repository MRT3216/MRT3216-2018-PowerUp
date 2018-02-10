package org.usfirst.frc.team3216.robot.commands;

import org.usfirst.frc.team3216.lib.Logger;
import org.usfirst.frc.team3216.robot.Robot;
import org.usfirst.frc.team3216.robot.RobotMap;
import org.usfirst.frc.team3216.robot.subsystems.Winch;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Winch_GoDown extends Command {
	private static final Logger.Level LOG_LEVEL = RobotMap.LOG_WINCH;
	
	Logger log = new Logger(LOG_LEVEL, getName());
	
	Winch winch = Robot.winch;

    public Winch_GoDown() {
        requires(winch);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	winch.stop();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	winch.goDown();
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
