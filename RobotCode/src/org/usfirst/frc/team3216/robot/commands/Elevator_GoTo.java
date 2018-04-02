package org.usfirst.frc.team3216.robot.commands;

import org.usfirst.frc.team3216.lib.Logger;
import org.usfirst.frc.team3216.robot.Robot;
import org.usfirst.frc.team3216.robot.RobotMap;
import org.usfirst.frc.team3216.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Elevator_GoTo extends Command {
	private static final Logger.Level LOG_LEVEL = RobotMap.LOG_ELEVATOR;

	Logger log = new Logger(LOG_LEVEL, getName());
	Elevator elevator = Robot.elevator;
	double threshold = RobotMap.ELEVATOR_THRESHOLD;
	
	DigitalInput topSwitch = Robot.topSwitch;
	DigitalInput bottomSwitch = Robot.bottomSwitch;

	boolean top;

	public Elevator_GoTo(boolean top) {
		requires(elevator);
		this.top = top;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		elevator.stop();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		/*
		 * if((elevatorEncoder < height) && ((height - elevatorEncoder.getDistance()) <
		 * threshold)) { elevator.setPower(1); } else if((elevatorEncoder > height) &&
		 * (((elevatorEncoder.getDistance()) - height) < threshold)) {
		 * elevator.setPower(-1); } else { elevator.stop(); }
		 */
		if(top) {elevator.setPower(1);}
		else {elevator.setPower(-1);}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		if(top) {return !topSwitch.get();}
		else {return !bottomSwitch.get();}
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		elevator.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		elevator.stop();
	}
}
