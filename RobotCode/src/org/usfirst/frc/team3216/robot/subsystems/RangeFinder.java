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
	// Volts per 5mm
	private static final double V5mm = .004885;
	private static final int OVERSAMPLED_BITS = 3;
	private Queue<Double> previousDistances;
	
	/** MB1013 Settings ********************************************************
	    A MB1013 distance sensor - http://www.maxbotix.com/documents/HRLV-MaxSonar-EZ_Datasheet.pdf
	    (pins 3, 6 and 7 from sensor to analog input 0)
	**/
	private static final AnalogInput MB1013 = new AnalogInput(RobotMap.RANGEFINDER);
	
	public RangeFinder() {
		log.add("Constructor", LOG_LEVEL);
		
		previousDistances = new LinkedList<>();
		
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
		
		//log.add("ave dist: " + aveDistInInches, LOG_LEVEL);
		
		return aveDistInInches;
	}
	
	public double getSmoothedDistancedInInches() {
		if(previousDistances.size() >= RobotMap.MEDIAN_SMOOTHING_READINGS){
			previousDistances.remove();
		}
		
		previousDistances.add(this.getAverageDistanceInInches());	
			
		return smooth(previousDistances);
	}
	
	private double smooth(Queue<Double> distances) {
		LinkedList<Double> linkedList = (LinkedList<Double>) distances;
		LinkedList<Double> newQueue = (LinkedList<Double>) linkedList.clone();
	
		double smoothedDistance = 0;
		
		if(linkedList.size() >= RobotMap.MEDIAN_SMOOTHING_READINGS) {
			smoothedDistance = this.medianSmoothing(newQueue);
		} else {
			smoothedDistance = linkedList.getLast();
		}
		
		log.add("Smoothed Dist: " + smoothedDistance, LOG_LEVEL);
		
		return smoothedDistance;
	}
	
	public double medianSmoothing(LinkedList<Double> linkedList) {
		Collections.sort(linkedList);
		
		return linkedList.get(linkedList.size()/2);
	}
	
	public void removeHighAndLow(LinkedList<Double> linkedList) {
		Double min = 1000.0;
		Double minSecond = 1000.0;
		Double max = 0.0;
		Double maxSecond = 0.0;
		for(int i = 0; i < linkedList.size(); i++){			
			Double currentItem = linkedList.get(i);
			
			if(currentItem < min) {		
				minSecond = min;
			    min = currentItem;				
		    } else if (currentItem < minSecond) {
		    	minSecond = currentItem;
		    } else if (currentItem > max) {
		    	maxSecond = max;
		    	max = currentItem;
		    } else if (currentItem > maxSecond) {
		    	maxSecond = currentItem;
		    }
		}	
		
		log.add("length before: ", linkedList.size(), LOG_LEVEL);
		log.add("min: " + min, LOG_LEVEL);
		log.add("min2: " + minSecond, LOG_LEVEL);
		log.add("max: " + max, LOG_LEVEL);
		log.add("max2: " + maxSecond, LOG_LEVEL);
		log.add("length after: ", linkedList.size(), LOG_LEVEL);
				
		linkedList.remove(min);
		linkedList.remove(minSecond);
		linkedList.remove(max);
		linkedList.remove(maxSecond);
	}
		
	private double voltageToDistance(double voltageMeasured) {
		// Divide the voltage measured by the volts per 5mm and then 
		// multiple by 5 to get the distance in mm
		return (voltageMeasured/V5mm) * 5;	
	}
}