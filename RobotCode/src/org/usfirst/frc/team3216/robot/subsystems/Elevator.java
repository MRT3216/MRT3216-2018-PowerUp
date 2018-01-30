package org.usfirst.frc.team3216.robot.subsystems;

import org.usfirst.frc.team3216.lib.Logger;
import org.usfirst.frc.team3216.robot.OI;
import org.usfirst.frc.team3216.robot.Robot;
import org.usfirst.frc.team3216.robot.RobotMap;
import org.usfirst.frc.team3216.robot.commands.Elevator_Move;

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
	
	public Elevator() {
		motor = new Talon(2);
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
	

    public void initDefaultCommand() {
        setDefaultCommand(new Elevator_Move());
    }
}

