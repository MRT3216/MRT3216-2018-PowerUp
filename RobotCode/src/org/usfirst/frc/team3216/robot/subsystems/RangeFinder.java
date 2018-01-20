package org.usfirst.frc.team3216.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3216.lib.Logger;
import org.usfirst.frc.team3216.robot.RobotMap;
import edu.wpi.first.wpilibj.AnalogInput;

/**
 *
 */
public class RangeFinder extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Logger.Level LOG_LEVEL = RobotMap.LOG_RANGEFINDER;
	private Logger log = new Logger(LOG_LEVEL, getName());
	
	/** Instance Variables ****************************************************
	    A MB1013 distance sensor - http://www.maxbotix.com/documents/HRLV-MaxSonar-EZ_Datasheet.pdf
	    (pins 3, 6 and 7 from sensor to analog input 0)
	**/
	private static final AnalogInput MB1013 = new AnalogInput(0);
	private static final double Vi = 9.766;
	private static final double Vcc = 512;
	private static final double VOLTS_TO_DIST = 1.0;
	private static final int OVERSAMPLED_BITS = 3;
	
	public RangeFinder() {
		//log.add("Constructor", LOG_LEVEL);
		MB1013.setOversampleBits(OVERSAMPLED_BITS);
	}
	
    public void initDefaultCommand() {

    }
    
	public double getVoltage() {
	    return MB1013.getVoltage();
	}
	
	public double getAverageVoltage() {
	    return MB1013.getAverageVoltage();
	}
		  
	public double getDistance() {
		double Vm = getVoltage();
		
		double dist = Vm/Vi;
		
		log.add("dist: " + dist, LOG_LEVEL);
		
	    return dist;
	}
	
	public double getAverageDistance() {		
		double Vm = getVoltage();
		
		double aveDist = Vm/Vi;
		
		log.add("ave dist: " + aveDist, LOG_LEVEL);
		
		return aveDist;
	}
}