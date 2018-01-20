package org.usfirst.frc.team3216.robot;

import org.usfirst.frc.team3216.lib.Logger;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	/** Configuration Constants ***********************************************/
	Gamepad gamepad;
	
	/** Instance Variables ****************************************************/
	Logger log = new Logger(RobotMap.LOG_OI, "OI");
	
	public OI() {
		log.add("OI Constructor", Logger.Level.TRACE);
		
		gamepad = new Gamepad(RobotMap.USB_GAMEPAD);
	}
	
	public double getDriveLeft() {
		double joystickValue = gamepad.getRawAxis(Gamepad.LEFT_JOY_Y_AXIS);
		joystickValue = scaleJoystick(joystickValue);
		log.add("getDriveLeft (" + joystickValue + ")", Logger.Level.TRACE);
		log.add("Deadzone = " + RobotMap.JOYSTICK_DEADZONE, Logger.Level.TRACE);
		return joystickValue;
	}
	
	public double getDriveRight() {
		double joystickValue = gamepad.getRawAxis(Gamepad.RIGHT_JOY_Y_AXIS);
		joystickValue = scaleJoystick(joystickValue);
		log.add("getDriveRight (" + joystickValue + ")", Logger.Level.TRACE);
		log.add("Deadzone = " + RobotMap.JOYSTICK_DEADZONE, Logger.Level.TRACE);
		return joystickValue;		
	}
	
	private double scaleJoystick(double joystickValue) {
		joystickValue = checkDeadZone(joystickValue);
		joystickValue *= RobotMap.SPEED;
		joystickValue *= -1.0;
		return joystickValue;
	}
	
	private double checkDeadZone(double joystickValue) {
		if (Math.abs(joystickValue) < RobotMap.JOYSTICK_DEADZONE) joystickValue = 0.0;
		return joystickValue;
	}
}
