package org.usfirst.frc.team3216.robot.subsystems;

import org.usfirst.frc.team3216.lib.Logger;
import org.usfirst.frc.team3216.robot.RobotMap;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
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
	protected double WHEEL_DIAMETER;
	private static final double FUDGE_FACTOR = 1.0;
	
	public BaseEncoder(int channelA, int channelB, String encoderName, boolean reversed) {
		log.add("Constructor", LOG_LEVEL);
		this.encoderName = encoderName;
		
    	encoder = new Encoder(channelA, channelB, false, EncodingType.k2X);
    	encoder.reset(); 
		GEAR_RATIO = RobotMap.DRIVETRAIN_GEAR_RATIO;
		PULSE_PER_REVOLUTION = RobotMap.DRIVETRAIN_ENCODER_PULSE_PER_REVOLUTION;
		WHEEL_DIAMETER = RobotMap.WHEEL_DIAMETER_FEET;
    	encoder.setReverseDirection(reversed);
    	encoder.setSamplesToAverage(7);
	}
    
    public void initDefaultCommand() {

    }
    
    public void initEncoder() {
    	double distancePerPulse = Math.PI * WHEEL_DIAMETER / PULSE_PER_REVOLUTION
    			/ GEAR_RATIO * FUDGE_FACTOR; 	
    	
    	//double distancePerPulse = Math.PI * WHEEL_DIAMETER / 360;
    	encoder.setDistancePerPulse(distancePerPulse);
    	
    	encoder.reset();
    }
    
    public double getDistanceInInches() {       	
    	double distance = encoder.getDistance()/12;
    	log.add(this.encoderName + " distance (in): " + distance, LOG_LEVEL);
    	return distance;	
    }
    
    public double getDistanceInFeet() {       	
    	double distance = encoder.getDistance();
    	log.add(this.encoderName + " distance (ft): " + distance, LOG_LEVEL);
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
    
    public void reset() {
    	encoder.reset();
    }
}