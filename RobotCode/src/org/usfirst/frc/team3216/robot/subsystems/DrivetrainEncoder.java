package org.usfirst.frc.team3216.robot.subsystems;

import org.usfirst.frc.team3216.robot.RobotMap;

public class DrivetrainEncoder extends BaseEncoder {
	public DrivetrainEncoder(int channelA, int channelB, String encoderName, boolean reversed) {
		super(channelA, channelB, encoderName, reversed);
		
		//GEAR_RATIO = RobotMap.DRIVETRAIN_GEAR_RATIO;
		//PULSE_PER_REVOLUTION = RobotMap.DRIVETRAIN_ENCODER_PULSE_PER_REVOLUTION;
		//WHEEL_DIAMETER = RobotMap.WHEEL_DIAMETER;
	}
}
