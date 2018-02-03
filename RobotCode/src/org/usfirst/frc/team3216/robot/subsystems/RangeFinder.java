package org.usfirst.frc.team3216.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
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
	private Queue<Double> lastTenDistances;
	
	/** Instance Variables ****************************************************
	    A MB1013 distance sensor - http://www.maxbotix.com/documents/HRLV-MaxSonar-EZ_Datasheet.pdf
	    (pins 3, 6 and 7 from sensor to analog input 0)
	**/
	private static final AnalogInput MB1013 = new AnalogInput(RobotMap.RANGEFINDER);
	
	// Volts per 5mm
	private static final double V5mm = .004885;
	private static final int OVERSAMPLED_BITS = 3;

	public RangeFinder() {
		log.add("Constructor", LOG_LEVEL);
		
		lastTenDistances = new LinkedList<>();
		
		MB1013.setOversampleBits(OVERSAMPLED_BITS);
	}
	
    public void initDefaultCommand() {

    }
    
	public double getVoltage() {
		//log.add("Voltage: " + MB1013.getVoltage(), LOG_LEVEL);
		
	    return MB1013.getVoltage();
	}
	
	public double getAverageVoltage() {
	    return MB1013.getAverageVoltage();
	}
		  
	public double getDistanceInMM() {
		double distInMM = this.voltageToDistance(getVoltage());
		
		//log.add("dist in mm: " + distInMM, LOG_LEVEL);
		
	    return distInMM;
	}
	
	public double getAverageDistanceInMM() {		
		double aveDistInMM = this.voltageToDistance(getAverageVoltage());
		
		//log.add("ave dist in mm: " + aveDistInMM, LOG_LEVEL);
		
		return aveDistInMM;
	}
	
	public double getDistanceInInches() {
		double distInInches = this.getDistanceInMM() * this.mmToInches;
		
		//log.add("dist: " + distInInches, LOG_LEVEL);
		
	    return distInInches;
	}
	
	public double getAverageDistanceInInches() {		
		double aveDistInInches = this.getAverageDistanceInMM() * this.mmToInches;
		
		log.add("ave dist: " + aveDistInInches, LOG_LEVEL);
		
		return aveDistInInches;
	}
	
	public double getSmoothedDistancedInInches() {
		if(lastTenDistances.size() > 10){
			lastTenDistances.remove();
		}
		
		lastTenDistances.add(this.getDistanceInInches());		
	
		return smooth(lastTenDistances);
	}
	
	private double smooth(Queue<Double> distances) {
		LinkedList<Double> linkedList = (LinkedList<Double>) distances;
		
		//Collections.sort(linkedList);
		
		int length = linkedList.size();

		linkedList.remove();
		linkedList.remove(4);
		//linkedList.remove(length-1);
		//linkedList.remove(length-2);
		//linkedList.remove(0);
		//linkedList.remove(1);
		
		//linkedList.remove(Collections.min(linkedList));
		//linkedList.remove(Collections.max(linkedList));
		//linkedList.remove(Collections.max(linkedList));
		
		
		double total = 0;
		for (Double item : linkedList) {
			total += item;
		}		
		
		//removeHighAndLow(linkedList);
				
		double smoothedDistance = total / linkedList.size();
		
		log.add("Smoothed Dist: " + smoothedDistance, LOG_LEVEL);
		
		return smoothedDistance;
	}
	
	public void removeHighAndLow(LinkedList<Double> linkedList) {
		Double min = 1000.0;
		Double minSecond = 1000.0;
		Double max = 0.0;
		for(int i = 0; i < linkedList.size(); i++){
		    if(linkedList.get(i) < min)
		        min = linkedList.get(i);
		}
				
		linkedList.remove(min);
	}
	
	
	private double voltageToDistance(double voltageMeasured) {
		// Divide the voltage measured by the volts per 5mm and then 
		// multiple by 5 to get the distance in mm
		return (voltageMeasured/V5mm) * 5;	
	}
}