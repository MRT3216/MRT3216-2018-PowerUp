package org.usfirst.frc.team3216.robot.subsystems;

import org.usfirst.frc.team3216.lib.Logger;
import org.usfirst.frc.team3216.robot.RobotMap;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class OurEncoder extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Logger.Level LOG_LEVEL = RobotMap.LOG_ENCODER;
	private Logger log = new Logger(LOG_LEVEL, getName());
	private Encoder encoder;
	
	public OurEncoder(int channelA, int channelB) {
		log.add("Constructor", LOG_LEVEL);
		
    	encoder = new Encoder(channelA, channelB, false, EncodingType.k4X);
    	encoder.reset();
    	encoder.setSamplesToAverage(5);
    	encoder.setReverseDirection(true);
    	encoder.setDistancePerPulse(1/360);	
	}
    
    public void initDefaultCommand() {

    }
    
    public double getDistance() {    	
    	double distance = encoder.getDistance();
    	
    	log.add("Encoder distance: " + distance, LOG_LEVEL);
    	
    	return distance;	
    }
    
    public double getRate() {
    	double rate = encoder.getRate();
    	
    	log.add("Encoder rate: " + rate, LOG_LEVEL);
    	
    	return rate;
    }
}