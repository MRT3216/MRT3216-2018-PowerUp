package org.usfirst.frc.team3216.robot.subsystems;

import org.usfirst.frc.team3216.lib.Logger;
import org.usfirst.frc.team3216.robot.OI;
import org.usfirst.frc.team3216.robot.Robot;
import org.usfirst.frc.team3216.robot.RobotMap;
import org.usfirst.frc.team3216.robot.commands.Elevator_Move;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
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
		motor = new Talon(RobotMap.PWM_ELEVATOR_MOTOR);
	}
	
	public void setPower(double power) {
		if(canGo()) {
			power = safetyCheck(power);
			log.add("Elevator Power: " + power, LOG_LEVEL);
			motor.set(power);	
		}
	}
	
	public void stop() {
		setPower(0.0);
	}

	private double safetyCheck(double power) {
		power = Math.min(1.0, power);
		power = Math.max(-1.0, power);
		return power;
	}
	
	private boolean canGo() {
		//TODO: Insert Code to check if it is within encoder range
		//may need separate methods for up and down.
		return true;
	}
	

    public void initDefaultCommand() {
        setDefaultCommand(new Elevator_Move());
    }
}

