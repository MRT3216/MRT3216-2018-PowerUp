package org.usfirst.frc.team3216.robot;

import org.usfirst.frc.team3216.lib.Logger;
import org.usfirst.frc.team3216.robot.commands.CGroup_ShootCube;
import org.usfirst.frc.team3216.robot.commands.ClimbArm_GoBackwards;
import org.usfirst.frc.team3216.robot.commands.ClimbArm_GoForward;
import org.usfirst.frc.team3216.robot.commands.ClimbArm_Stop;
import org.usfirst.frc.team3216.robot.commands.Pneumatics_ClosePincher;
import org.usfirst.frc.team3216.robot.commands.Pneumatics_OpenPincher;
import org.usfirst.frc.team3216.robot.commands.Pneumatics_TogglePincher;
import org.usfirst.frc.team3216.robot.commands.Shifter_ShiftDown;
import org.usfirst.frc.team3216.robot.commands.Shifter_ShiftUp;
import org.usfirst.frc.team3216.robot.commands.Winch_GoDown;
import org.usfirst.frc.team3216.robot.commands.Winch_GoUp;
import org.usfirst.frc.team3216.robot.commands.Winch_Stop;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	/** Configuration Constants ***********************************************/
	public Gamepad gamepad;
	public ControlStick controlStick;
	private static final Logger.Level LOG_LEVEL = RobotMap.LOG_OI;

	/** Instance Variables ****************************************************/
	Logger log = new Logger(RobotMap.LOG_OI, "OI");

	public OI() {
		log.add("OI Constructor", LOG_LEVEL);

		gamepad = new Gamepad(RobotMap.USB_GAMEPAD);
		if (RobotMap.hasShifter) {
			gamepad.Y.whenPressed(new Shifter_ShiftUp());
			gamepad.A.whenPressed(new Shifter_ShiftDown());
		}
		if (RobotMap.hasJoystick) {
			
			//Code to check which joystick is plugged in
			controlStick = new ControlStick(RobotMap.USB_ATTACK_CONTROL_STICK);
			RobotMap.currentControlStick = RobotMap.ControlStick.ATTACK;
			if(controlStick == null) {
				controlStick = new ControlStick(RobotMap.USB_EXTREME_CONTROL_STICK);
				RobotMap.currentControlStick = RobotMap.ControlStick.EXTREME;
			}
			
			if(RobotMap.currentControlStick == RobotMap.ControlStick.ATTACK) {
				log.add("Attack Joystick Contructed", LOG_LEVEL);
				controlStick.button5.whenPressed(new Pneumatics_ClosePincher());
				controlStick.button4.whenPressed(new Pneumatics_OpenPincher());
				controlStick.Trigger.whenPressed(new CGroup_ShootCube());
				controlStick.button8.whenPressed(new ClimbArm_GoBackwards());
				controlStick.button8.whenReleased(new ClimbArm_Stop());
				controlStick.button9.whenPressed(new ClimbArm_GoForward());
				controlStick.button9.whenReleased(new ClimbArm_Stop());
				if (RobotMap.hasWinch) {
					controlStick.button6.whenPressed(new Winch_GoUp());
					controlStick.button6.whenReleased(new Winch_Stop());
					controlStick.button7.whenPressed(new Winch_GoDown());
					controlStick.button7.whenReleased(new Winch_Stop());
				}
			}
			else {
				log.add("EXTREME Joystick Contructed", LOG_LEVEL);
				controlStick.button2.whenPressed(new Pneumatics_TogglePincher());
				controlStick.Trigger.whenPressed(new CGroup_ShootCube());
				controlStick.button3.whenPressed(new ClimbArm_GoBackwards());
				controlStick.button3.whenReleased(new ClimbArm_Stop());
				controlStick.button5.whenPressed(new ClimbArm_GoForward());
				controlStick.button5.whenReleased(new ClimbArm_Stop());
				if (RobotMap.hasWinch) {
					controlStick.button6.whenPressed(new Winch_GoUp());
					controlStick.button6.whenReleased(new Winch_Stop());
					controlStick.button4.whenPressed(new Winch_GoDown());
					controlStick.button4.whenReleased(new Winch_Stop());
				}
			}
		}

	}

	/** Gamepad Functions *****************************************************/
	public double getDriveLeft() {
		double joystickValue = gamepad.getRawAxis(Gamepad.LEFT_JOY_Y_AXIS);
		joystickValue = scaleJoystick(joystickValue);
		joystickValue *= -.5;

		// log.add("getDriveLeft (" + joystickValue + ")", LOG_LEVEL);
		// log.add("Deadzone = " + RobotMap.JOYSTICK_DEADZONE, LOG_LEVEL);

		return joystickValue;
	}

	public double getDriveRight() {
		double joystickValue = gamepad.getRawAxis(Gamepad.RIGHT_JOY_Y_AXIS);
		joystickValue = scaleJoystick(joystickValue);
		joystickValue *= -.5;

		// log.add("getDriveRight (" + joystickValue + ")", LOG_LEVEL);
		return joystickValue;
	}

	public double getLeftY() {
		double joystickValue = gamepad.getRawAxis(Gamepad.LEFT_JOY_Y_AXIS);
		joystickValue = checkDeadZone(joystickValue);
		// log.add("getLeftY (" + joystickValue + ")", LOG_LEVEL);
		log.add("Deadzone = " + RobotMap.JOYSTICK_DEADZONE, LOG_LEVEL);
		return joystickValue;
	}

	public double getRightX() {
		double joystickValue = gamepad.getRawAxis(Gamepad.RIGHT_JOY_X_AXIS);
		joystickValue = checkDeadZone(joystickValue);
		// log.add("getRightX (" + joystickValue + ")", LOG_LEVEL);
		return joystickValue;
	}

	/** Control Stick Functions ***********************************************/
	public double getStickY() {
		double joystickValue = controlStick.getRawAxis(ControlStick.JOYSTICK_Y_AXIS);
		// TODO - add checkDeadZone (if needed)
		log.add("StickY: " + joystickValue, LOG_LEVEL);
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
		return a * (Math.pow(x, 3)) + (a - 1) * x;
	}

	private double checkDeadZone(double joystickValue) {
		if (Math.abs(joystickValue) < RobotMap.JOYSTICK_DEADZONE) {
			joystickValue = 0.0;
		}
		return joystickValue;
	}
}
