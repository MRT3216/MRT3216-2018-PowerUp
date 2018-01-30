package org.usfirst.frc.team3216.robot.commands;

import org.usfirst.frc.team3216.lib.Logger;
import org.usfirst.frc.team3216.robot.Robot;
import org.usfirst.frc.team3216.robot.RobotMap;
import org.usfirst.frc.team3216.robot.subsystems.Pneumatics;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Pneumatics_RetractPopper extends Command {
	private static final Logger.Level LOG_LEVEL = RobotMap.LOG_PNEUMATICS;
	Logger log = new Logger(RobotMap.LOG_PNEUMATICS, "RetractPopper");

	Pneumatics pneumatics = Robot.pneumatics;

    public Pneumatics_RetractPopper() {
    	log.add("Contructor", LOG_LEVEL);
        requires(pneumatics);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	log.add("Initialize", LOG_LEVEL);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	log.add("Execute", LOG_LEVEL);
    	pneumatics.closePopper();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	log.add("End", LOG_LEVEL);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
