package org.usfirst.frc.team3216.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RangeFinder extends Subsystem {

	  // A MB1013 distance sensor - http://www.maxbotix.com/documents/HRLV-MaxSonar-EZ_Datasheet.pdf
	  // (pins 3, 6 and 7 from sensor to analog input 0)
	  private static final AnalogInput MB1013 = new AnalogInput(0);
	  private static final double VOLTS_TO_DIST = 1.0;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
	public static double getVoltage() {
	    return MB1013.getVoltage();
	}
		  
	public static double getDistance() {
	    return getVoltage() * VOLTS_TO_DIST;
	}
}