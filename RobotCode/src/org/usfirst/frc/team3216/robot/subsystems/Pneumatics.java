package org.usfirst.frc.team3216.robot.subsystems;

import org.usfirst.frc.team3216.lib.Logger;
import org.usfirst.frc.team3216.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Pneumatics extends Subsystem {
	private static final Logger.Level LOG_LEVEL = RobotMap.LOG_PNEUMATICS;
	Logger log = new Logger(LOG_LEVEL, "Pneumatics");

	private DoubleSolenoid pincher;
	private DoubleSolenoid popper;
	private final DoubleSolenoid.Value forward = DoubleSolenoid.Value.kForward;
	private final DoubleSolenoid.Value reverse = DoubleSolenoid.Value.kReverse;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public Pneumatics() {
		log.add("Constuctor", LOG_LEVEL);
		
		if(RobotMap.hasPneumatics) {
			pincher = new DoubleSolenoid(0,1);
			popper = new DoubleSolenoid(2, 3);
			
			initPneumatics();
		}
	}
	
	private void initPneumatics() {
		closePincher();
		closePopper();
	}
	
	//opens arms to release cube
	public void openPincher() {
		if(pincher.get() != forward)  { 
			pincher.set(forward);
			log.add("OpenPincher executed", LOG_LEVEL);
		}
	}
	
	//closes arms to grab cube
	public void closePincher() {
		if(pincher.get() != reverse) {
			pincher.set(reverse);
			log.add("ClosePincher Closed", LOG_LEVEL);
		}
	}
	
	//activates ejecting arm
	public void openPopper() {
		if(popper.get() != forward) {
			popper.set(forward);
		}
	}
	
	public void closePopper() {
		if(popper.get() != reverse) {
			popper.set(reverse);
		}
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

