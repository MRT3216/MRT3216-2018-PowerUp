package org.usfirst.frc.team3216.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class AirCompressor extends Subsystem {
	Compressor compressor;
		
    public AirCompressor() {
    	compressor = new Compressor(0);
    	compressor.setClosedLoopControl(true);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

