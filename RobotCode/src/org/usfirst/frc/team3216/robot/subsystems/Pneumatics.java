package org.usfirst.frc.team3216.robot.subsystems;

import org.usfirst.frc.team3216.lib.Logger;
import org.usfirst.frc.team3216.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Pneumatics extends Subsystem {

	private static final Logger.Level LOG_LEVEL = RobotMap.LOG_PNEUMATICS;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public Pneumatics() {
		
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

