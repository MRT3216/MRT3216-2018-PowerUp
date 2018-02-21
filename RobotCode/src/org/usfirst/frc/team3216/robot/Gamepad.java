package org.usfirst.frc.team3216.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class Gamepad extends Joystick {
	/** Gamepad Button and Axis Mapping ***************************************/
	public static final int BUTTON_A = 1;
	public static final int BUTTON_B = 2;
	public static final int BUTTON_X = 3;
	public static final int BUTTON_Y = 4;
	public static final int BUTTON_LB = 5;
	public static final int BUTTON_RB = 6;
	public static final int BUTTON_BACK = 7;
	public static final int BUTTON_START = 8;
	public static final int BUTTON_LEFT_JOY = 9;
	public static final int BUTTON_RIGHT_JOY = 10;

	/** Axis Mapping for a single joystick ************************************/
	public static final int RIGHT_JOY_X_AXIS = 4;
	public static final int RIGHT_JOY_Y_AXIS = 5;
	public static final int RIGHT_TRIGGER = 2;
	public static final int LEFT_TRIGGER = 3;
	public static final int LEFT_JOY_X_AXIS = 0;
	public static final int LEFT_JOY_Y_AXIS = 1;

	/** Button Declarations ***************************************************/
	Button A = new JoystickButton(this, BUTTON_A);
	Button B = new JoystickButton(this, BUTTON_B);
	Button X = new JoystickButton(this, BUTTON_X);
	Button Y = new JoystickButton(this, BUTTON_Y);
	Button LB = new JoystickButton(this, BUTTON_LB);
	Button RB = new JoystickButton(this, BUTTON_RB);
	Button Back = new JoystickButton(this, BUTTON_BACK);
	Button Start = new JoystickButton(this, BUTTON_START);
	Button LeftJoy = new JoystickButton(this, BUTTON_LEFT_JOY);
	Button RightJoy = new JoystickButton(this, BUTTON_RIGHT_JOY);

	public Gamepad(int port) {
		super(port);
	}
}
