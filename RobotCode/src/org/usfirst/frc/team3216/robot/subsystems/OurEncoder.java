package org.usfirst.frc.team3216.robot.subsystems;

import org.usfirst.frc.team3216.lib.Logger;
import org.usfirst.frc.team3216.robot.RobotMap;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class OurEncoder extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Logger.Level LOG_LEVEL = RobotMap.LOG_RANGEFINDER;
	private Logger log = new Logger(LOG_LEVEL, getName());
	private int portNumber;
	private Encoder encoder;
	
	public OurEncoder(int portNumber) {
		log.add("Constructor", LOG_LEVEL);
		
		this.portNumber = portNumber;
	}
    
    public void initDefaultCommand() {
   	
    	encoder = new Encoder(0,0);
    	encoder.setReverseDirection(true);
    	encoder.setDistancePerPulse(1/1513.0);
        
        int count = encoder.get();
        double rawDistance = encoder.getRaw();
        double distance = encoder.getDistance();
        double period = encoder.getPeriod();
        double rate = encoder.getRate();
        boolean direction = encoder.getDirection();
        boolean stopped = encoder.getStopped();
    }
}

