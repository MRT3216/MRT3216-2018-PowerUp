package org.usfirst.frc.team3216.robot;

import org.usfirst.frc.team3216.lib.Logger;
import org.usfirst.frc.team3216.robot.commands.Pneumatics_ClosePincher;
import org.usfirst.frc.team3216.robot.commands.Pneumatics_ExtendPopper;
import org.usfirst.frc.team3216.robot.commands.Pneumatics_OpenPincher;
import org.usfirst.frc.team3216.robot.commands.Pneumatics_RetractPopper;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	/** Configuration Constants ***********************************************/
	public Gamepad gamepad;
	public ControlStick controlStick;
	private static final Logger.Level LOG_LEVEL = RobotMap.LOG_DRIVEFORWARD;
	
	/** Instance Variables ****************************************************/
	Logger log = new Logger(RobotMap.LOG_OI, "OI");
	
	public OI() {
		log.add("OI Constructor", LOG_LEVEL);
		
		gamepad = new Gamepad(RobotMap.USB_GAMEPAD);
		
		if(RobotMap.hasJoystick) {
			controlStick = new ControlStick(RobotMap.USB_CONTROL_STICK);
			controlStick.button4.whenPressed(new Pneumatics_ClosePincher());
			controlStick.button5.whenPressed(new Pneumatics_OpenPincher());
			controlStick.button2.whenPressed(new Pneumatics_RetractPopper());
			controlStick.button3.whenPressed(new Pneumatics_ExtendPopper());
		}
	}
	
	/** Gamepad Functions *****************************************************/
	public double getDriveLeft() {
		double joystickValue = gamepad.getRawAxis(Gamepad.LEFT_JOY_Y_AXIS);
		joystickValue = scaleJoystick(joystickValue);
		joystickValue *= -.5;
		
		log.add("getDriveLeft (" + joystickValue + ")", LOG_LEVEL);
		log.add("Deadzone = " + RobotMap.JOYSTICK_DEADZONE, LOG_LEVEL);
		
		return joystickValue;
	}
	
	public double getDriveRight() {
		double joystickValue = gamepad.getRawAxis(Gamepad.RIGHT_JOY_Y_AXIS);
		joystickValue = scaleJoystick(joystickValue);
		joystickValue *= -.5;

		log.add("getDriveRight (" + joystickValue + ")", LOG_LEVEL);
		log.add("Deadzone = " + RobotMap.JOYSTICK_DEADZONE, LOG_LEVEL);
		return joystickValue;		
	}
	
	public double getLeftY() {
		double joystickValue = gamepad.getRawAxis(Gamepad.LEFT_JOY_Y_AXIS);
		joystickValue = checkDeadZone(joystickValue);
		return joystickValue;	
	}
	
	public double getRightX() {
		double joystickValue = gamepad.getRawAxis(Gamepad.RIGHT_JOY_X_AXIS);
		joystickValue = checkDeadZone(joystickValue);
		return joystickValue;	
	}
	
	/** Control Stick Functions ***********************************************/	
	public double getStickY() {
		double joystickValue = gamepad.getRawAxis(ControlStick.JOYSTICK_Y_AXIS);
		//TODO - add checkDeadZone (if needed)
		return joystickValue;
	}	
	
	private double scaleJoystick(double joystickValue) {
		joystickValue = checkDeadZone(joystickValue);
		joystickValue = scaleSensitivity(joystickValue);
		return joystickValue;
	}
	
    // Scale Joystick Sensitivity 
	// a = sensitivity, and x is the power parameter
    // y = a(x^3) + (1-a)x
    private double scaleSensitivity(double x) {
    	double a = RobotMap.JOYSTICK_SENSITIVITY;
    	return a * (Math.pow(x, 3)) + (a-1) * x;	
    }	
	
	private double checkDeadZone(double joystickValue) {
		if (Math.abs(joystickValue) < RobotMap.JOYSTICK_DEADZONE) {
			joystickValue = 0.0;
		}
		return joystickValue;
	}
}
