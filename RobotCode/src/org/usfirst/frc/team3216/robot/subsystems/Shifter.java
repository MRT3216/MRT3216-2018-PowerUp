package org.usfirst.frc.team3216.robot.subsystems;

import org.usfirst.frc.team3216.lib.Logger;
import org.usfirst.frc.team3216.robot.RobotMap;
import org.usfirst.frc.team3216.robot.RobotMap.Gear;

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
	DoubleSolenoid transmission;

	public Shifter() {
		log.add("Constuctor", LOG_LEVEL);

		if (RobotMap.hasShifter) {
			transmission = new DoubleSolenoid(4, 5);
			initShifter();
		}
	}

	public void shiftUp() {
		if (transmission.get() != high) {
			transmission.set(high);
		}
	}

	public void shiftDown() {
		if (transmission.get() != low) {
			transmission.set(low);
		}
	}

	private void initShifter() {
		transmission.set(low);
	}

	public Gear getGear() {
		if (transmission.get() == high) {
			return Gear.HIGH;
		} else {
			return Gear.LOW;
		}
	}

	@Override
	public void initDefaultCommand() {
		// setDefaultCommand(new Shifter_ShiftUp());
	}
}