package org.usfirst.frc.team3216.robot;

import edu.wpi.first.wpilibj.buttons.Button;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	Gamepad gamepad;
	
	public OI() {
		gamepad = new Gamepad(RobotMap.USB_GAMEPAD);
	}
	
	public double getDriveLeft() {
		double joystickValue = gamepad.getRawAxis(Gamepad.LEFT_JOY_Y_AXIS);
		joystickValue = scaleJoystick(joystickValue);
		return joystickValue;
	}
	
	public double getDriveRight() {
		double joystickValue = gamepad.getRawAxis(Gamepad.RIGHT_JOY_Y_AXIS);
		joystickValue = scaleJoystick(joystickValue);
		return joystickValue;		
	}
	
	private double scaleJoystick(double joystickValue) {
		joystickValue = checkDeadZone(joystickValue);
		//joystickValue *= JOYSTICK_DRIVE_SCALE;
		//joystickValue *= -1.0;
		return joystickValue;
	}
	
	private double checkDeadZone(double joystickValue) {
		//if (Math.abs(joystickValue) < JOYSTICK_DEAD_ZONE) joystickValue = 0.0;
		return joystickValue;
	}
}
