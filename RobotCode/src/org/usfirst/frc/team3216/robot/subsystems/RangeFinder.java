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
	private final double mmToInches = 0.03937007874;
	
	/** Instance Variables ****************************************************
	    A MB1013 distance sensor - http://www.maxbotix.com/documents/HRLV-MaxSonar-EZ_Datasheet.pdf
	    (pins 3, 6 and 7 from sensor to analog input 0)
	**/
	private static final AnalogInput MB1013 = new AnalogInput(0);
	// Volts per 5mm
	private static final double V5mm = .004885;
	private static final int OVERSAMPLED_BITS = 3;

	public RangeFinder() {
		log.add("Constructor", LOG_LEVEL);
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
		  
	public double getDistanceInMM() {
		double distInMM = this.voltageToDistance(getVoltage());
		
		log.add("dist in mm: " + distInMM, LOG_LEVEL);
		
	    return distInMM;
	}
	
	public double getAverageDistanceInMM() {		
		double aveDistInMM = this.voltageToDistance(getAverageVoltage());
		
		log.add("ave dist in mm: " + aveDistInMM, LOG_LEVEL);
		
		return aveDistInMM;
	}
	
	public double getDistanceInInches() {
		double distInInches = this.getDistanceInMM() * this.mmToInches;
		
		log.add("dist in mm: " + distInInches, LOG_LEVEL);
		
	    return distInInches;
	}
	
	public double getAverageDistanceInInches() {		
		double aveDistInInches = this.getAverageDistanceInMM() * this.mmToInches;
		
		log.add("ave dist in mm: " + aveDistInInches, LOG_LEVEL);
		
		return aveDistInInches;
	}
	
	private double voltageToDistance(double voltageMeasured) {
		// Divide the voltage measured by the volts per 5mm and then 
		// multiple by 5 to get the distance in mm
		return (voltageMeasured/V5mm) * 5;	
	}
}