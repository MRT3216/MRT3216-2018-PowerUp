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
public class BaseEncoder extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Logger.Level LOG_LEVEL = RobotMap.LOG_ENCODER;
	private Logger log = new Logger(LOG_LEVEL, getName());
	private Encoder encoder;
	private String encoderName;
	protected double GEAR_RATIO;
	protected double PULSE_PER_REVOLUTION;	
	protected static final double WHEEL_DIAMETER = 4;
	private static final double FUDGE_FACTOR = 1.0;
	
	public BaseEncoder(int channelA, int channelB, String encoderName, boolean reversed) {
		log.add("Constructor", LOG_LEVEL);
		this.encoderName = encoderName;
		
    	encoder = new Encoder(channelA, channelB, false, EncodingType.k4X);
    	encoder.reset(); 
    	encoder.setReverseDirection(reversed);
    	encoder.setSamplesToAverage(7);
	}
    
    public void initDefaultCommand() {

    }
    
    public void initEncoder() {
    	double distancePerPulse = Math.PI * WHEEL_DIAMETER / PULSE_PER_REVOLUTION
    			/ GEAR_RATIO * FUDGE_FACTOR; 	
    	encoder.setDistancePerPulse(distancePerPulse);
    	
    	encoder.reset();
    }
    
    public double getDistance() {       	
    	double distance = encoder.getDistance();
    	log.add(this.encoderName + " distance: " + distance, LOG_LEVEL);
    	return distance;	
    }
    
    public double getRate() {
    	double rate = encoder.getRate();
    	
    	log.add(this.encoderName + " rate: " + rate, LOG_LEVEL);
    	
    	return rate;
    }
    
    public int getCount() {
    	return encoder.get();
    }
}