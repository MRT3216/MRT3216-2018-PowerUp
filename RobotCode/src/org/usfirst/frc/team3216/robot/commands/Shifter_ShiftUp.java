package org.usfirst.frc.team3216.robot.commands;

import org.usfirst.frc.team3216.lib.Logger;
import org.usfirst.frc.team3216.robot.Robot;
import org.usfirst.frc.team3216.robot.RobotMap;
import org.usfirst.frc.team3216.robot.subsystems.Shifter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Shifter_ShiftUp extends Command {
	private static final Logger.Level LOG_LEVEL = RobotMap.LOG_SHIFTER;
	Logger log = new Logger(LOG_LEVEL, "Shifter");
	
	private Shifter shifter = Robot.shifter;

    public Shifter_ShiftUp() {
        requires(shifter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	log.add("Initialize", LOG_LEVEL);

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	shifter.shiftUp();
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
