package org.usfirst.frc.team3216.robot;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static enum Bot {BLUEFISH, MAINBOT;}
	public static Bot currentBot = Bot.BLUEFISH;
	
	/** USB ports *************************************************************/
	public static final int USB_GAMEPAD = 0;
	
	/** PWM ID numbers ********************************************************/
	public static final int PWM_LEFT_MOTOR 	= 0;
	public static final int PWM_RIGHT_MOTOR = 1;
	
	/** Drivetrain Settings ***************************************************/
	public static final boolean REVERSE_LEFT_MOTOR = false;
	public static final boolean REVERSE_RIGHT_MOTOR = false;
	
	
}
