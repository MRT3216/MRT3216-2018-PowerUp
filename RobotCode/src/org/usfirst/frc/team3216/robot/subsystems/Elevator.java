package org.usfirst.frc.team3216.robot.subsystems;

import org.usfirst.frc.team3216.lib.Logger;
import org.usfirst.frc.team3216.robot.OI;
import org.usfirst.frc.team3216.robot.Robot;
import org.usfirst.frc.team3216.robot.RobotMap;
import org.usfirst.frc.team3216.robot.commands.Elevator_Move;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {

	private static final Logger.Level LOG_LEVEL = RobotMap.LOG_ELEVATOR;

	Logger log = new Logger(LOG_LEVEL, getName());
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	OI oi = Robot.oi;
	private Talon motor;
	DigitalInput topSwitch = Robot.topSwitch;
	DigitalInput bottomSwitch = Robot.bottomSwitch;

	public Elevator() {
		motor = new Talon(RobotMap.PWM_ELEVATOR_MOTOR);
	}

	public void setPower(double power) {
		power = heightCheck(power);
		power = safetyCheck(power);
		log.add("Elevator Power: " + power, LOG_LEVEL);
		motor.set(power);
	}

	public void stop() {
		setPower(0.0);
	}

	private double safetyCheck(double power) {
		power = Math.min(1.0, power);
		power = Math.max(-1.0, power);
		/*
		 * if((!topSwitch.get() && power > 0) || (!bottomSwitch.get() && power < 0)) {
		 * return power; } else { return 0.0; }
		 */
		return power;
	}

	private double heightCheck(double power) {
		log.add("Top: " + topSwitch.get(), LOG_LEVEL);
		log.add("Bottom: " + bottomSwitch.get(), LOG_LEVEL);
		log.add("Power: " + power, LOG_LEVEL);
		if ((power < 0 && topSwitch.get()) || (power > 0 && bottomSwitch.get())) {
			return power;
		} else {
			return 0;
		}
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new Elevator_Move());
	}
}
