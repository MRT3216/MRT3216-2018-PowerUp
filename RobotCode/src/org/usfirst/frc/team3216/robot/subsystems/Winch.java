package org.usfirst.frc.team3216.robot.subsystems;

import org.usfirst.frc.team3216.lib.Logger;
import org.usfirst.frc.team3216.robot.OI;
import org.usfirst.frc.team3216.robot.Robot;
import org.usfirst.frc.team3216.robot.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Winch extends Subsystem {

	private static final Logger.Level LOG_LEVEL = RobotMap.LOG_WINCH;
	
	Logger log = new Logger(LOG_LEVEL, getName());
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private Talon motor;
	
	public Winch() {
		motor = new Talon(RobotMap.PWM_WINCH_MOTOR);
	}
	
	public void goUp() {
    	log.add("Winch Up: 1", LOG_LEVEL);
		motor.set(1);
	}
	
	public void goDown() {
    	log.add("Winch Down: -1", LOG_LEVEL);
		motor.set(-1);
	}
	
	public void stop() {
    	log.add("Winch Stop: 0", LOG_LEVEL);
		motor.set(0);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

