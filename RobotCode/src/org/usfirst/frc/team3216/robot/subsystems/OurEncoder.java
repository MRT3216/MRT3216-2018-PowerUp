package org.usfirst.frc.team3216.robot.subsystems;

import org.usfirst.frc.team3216.lib.Logger;
import org.usfirst.frc.team3216.robot.RobotMap;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class OurEncoder extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Logger.Level LOG_LEVEL = RobotMap.LOG_ENCODER;
	private Logger log = new Logger(LOG_LEVEL, getName());
	private Encoder encoder;
	private String encoderName;
	
	private static final double WHEEL_DIAMETER = 4;
	private static final double PULSE_PER_REVOLUTION = 360;
	private static final double GEAR_RATIO = 12/13.0;
	private static final double FUDGE_FACTOR = 1.0;
	
	public OurEncoder(int channelA, int channelB, String encoderName, boolean reversed) {
		log.add("Constructor", LOG_LEVEL);
		this.encoderName = encoderName;
		
    	encoder = new Encoder(channelA, channelB, false, EncodingType.k4X);
    	encoder.reset();
    	
    	/*
    	encoder.setMaxPeriod(.1);
    	encoder.setMinRate(10);   	    	
		*/
    	
    	double distancePerPulse = Math.PI * WHEEL_DIAMETER / PULSE_PER_REVOLUTION
    			/ GEAR_RATIO * FUDGE_FACTOR;
    	
    	encoder.setReverseDirection(true);
    	encoder.setSamplesToAverage(7);
    	encoder.setDistancePerPulse(distancePerPulse);
	}
    
    public void initDefaultCommand() {

    }
    
    public void initEncoder() {
    	encoder.reset();
    }
    
    public double getDistance() {    	
    	
    	double distance = encoder.getDistance();
    	double count = encoder.get();
    	log.add(this.encoderName + " distance: " + distance, LOG_LEVEL);
    	log.add(this.encoderName + " count: " + count,LOG_LEVEL);
    	//log.add("Raw: " + encoder.getRaw(), LOG_LEVEL);
    	//log.add("Stopped: " + encoder.getStopped(), LOG_LEVEL);
    	
    	return distance;	
    }
    
    public double getRate() {
    	double rate = encoder.getRate();
    	
    	//log.add("Encoder rate: " + rate, LOG_LEVEL);
    	
    	return rate;
    }
}