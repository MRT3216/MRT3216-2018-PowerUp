package org.usfirst.frc.team3216.robot.subsystems;

import org.usfirst.frc.team3216.lib.Logger;
import org.usfirst.frc.team3216.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shifter extends Subsystem {
	private static final Logger.Level LOG_LEVEL = RobotMap.LOG_SHIFTER;
	Logger log = new Logger(LOG_LEVEL, "Shifter");
	private final DoubleSolenoid.Value high = DoubleSolenoid.Value.kForward;
	private final DoubleSolenoid.Value low = DoubleSolenoid.Value.kReverse;
	DoubleSolenoid left;
	DoubleSolenoid right;
	
	public Shifter() {
		log.add("Constuctor", LOG_LEVEL);
		
		if(RobotMap.hasPneumatics) {
			left = new DoubleSolenoid(0,1);
			right = new DoubleSolenoid(2, 3);
			
			initShifter();
		}
	}
	
	public void shiftUp() {
		if(left.get() != high) {
			left.set(high);
		}
		if(right.get() != high) {
			right.set(high);
		}
	}
	
	public void shiftDown() {
		if(left.get() != low) {
			left.set(low);
		}
		if(right.get() != low) {
			right.set(low);
		}
	}

    private void initShifter() {
    	left.set(low);
    	right.set(low);
	}
    
    getGear()

	public void initDefaultCommand() {
        setDefaultCommand(new MySpecialCommand());
    }
}

