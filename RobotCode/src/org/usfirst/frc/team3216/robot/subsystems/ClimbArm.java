package org.usfirst.frc.team3216.robot.subsystems;

import org.usfirst.frc.team3216.lib.Logger;
import org.usfirst.frc.team3216.robot.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClimbArm extends Subsystem {

	private static final Logger.Level LOG_LEVEL = RobotMap.LOG_CLIMB_ARM;

	Logger log = new Logger(LOG_LEVEL, getName());

	private Talon motor;

	public ClimbArm() {
		motor = new Talon(RobotMap.PWM_CLIMB_ARM_MOTOR);
	}

	public void setPower(double power) {
		power = safetyCheck(power);
		motor.set(power);
	}

	public void stop() {
		setPower(0.0);
	}

	private double safetyCheck(double power) {
		power = Math.min(1.0, power);
		power = Math.max(-1.0, power);
		return power;
	}

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
