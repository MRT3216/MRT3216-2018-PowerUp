package org.usfirst.frc.team3216.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class ControlPanel extends Joystick{
	/** Gamepad Button and Axis Mapping ***************************************/
	public static final int BUTTON_1 = 1;
	public static final int BUTTON_2 = 2;
	public static final int BUTTON_3 = 3;
	public static final int BUTTON_4 = 4;
	public static final int BUTTON_5 = 5;
	public static final int BUTTON_6 = 6;
	public static final int SWITCH_1 = 7;
	
	/** Button Declarations ***************************************************/
	Button button1 = new JoystickButton(this, BUTTON_1);
	Button button2 = new JoystickButton(this, BUTTON_2);
	Button button3 = new JoystickButton(this, BUTTON_3);
	Button button4 = new JoystickButton(this, BUTTON_4);
	Button button5 = new JoystickButton(this, BUTTON_5);
	Button button6 = new JoystickButton(this, BUTTON_6);
	Button Switch_1 = new JoystickButton(this, SWITCH_1);

	public ControlPanel(int port) {
		super(port);
	}
}
