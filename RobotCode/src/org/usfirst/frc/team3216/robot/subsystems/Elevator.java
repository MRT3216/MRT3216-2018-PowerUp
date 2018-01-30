package org.usfirst.frc.team3216.robot.subsystems;

import org.usfirst.frc.team3216.lib.Logger;
import org.usfirst.frc.team3216.robot.OI;
import org.usfirst.frc.team3216.robot.Robot;
import org.usfirst.frc.team3216.robot.RobotMap;

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
	
	public Elevator() {
		
	}
	
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

