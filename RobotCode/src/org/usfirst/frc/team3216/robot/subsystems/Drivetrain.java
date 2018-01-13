package org.usfirst.frc.team3216.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team3216.lib.Logger;
import org.usfirst.frc.team3216.robot.RobotMap;
import org.usfirst.frc.team3216.robot.commands.Drivetrain_TankDrive;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 */
public class Drivetrain extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Logger.Level LOG_LEVEL = RobotMap.LOG_DRIVETRAIN;
	
	/** Instance Variables ****************************************************/
	private SpeedController leftMotors, rightMotors;
	private Logger log = new Logger(LOG_LEVEL, getName());

	public Drivetrain() {
		log.add("Constructor", LOG_LEVEL);
		
    	if(RobotMap.currentBot == RobotMap.Bot.MAINBOT) {
    		leftMotors = new VictorSP(RobotMap.PWM_LEFT_MOTOR);
    		rightMotors = new VictorSP(RobotMap.PWM_RIGHT_MOTOR);
    		
    		initMotor((VictorSP) leftMotors, RobotMap.REVERSE_LEFT_MOTOR);
    		initMotor((VictorSP) rightMotors, RobotMap.REVERSE_RIGHT_MOTOR);
    	} else {
    		leftMotors = new Talon(RobotMap.PWM_LEFT_MOTOR);
    		rightMotors = new Talon(RobotMap.PWM_RIGHT_MOTOR);
    		
    		initMotor((Talon) leftMotors, RobotMap.REVERSE_LEFT_MOTOR);
    		initMotor((Talon) rightMotors, RobotMap.REVERSE_RIGHT_MOTOR);
    	}		
	}
	
	private void initMotor(Talon motor, boolean reverse) {
		motor.setInverted(reverse); 	// affects percent Vbus mode???
		//motor.setSensorPhase(reverse); 	// affects closed-loop mode
	}
	
	private void initMotor(VictorSP motor, boolean reverse) {
		motor.setInverted(reverse);
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new Drivetrain_TankDrive());
    }
    

	/** Methods for setting the motors *************************************/
	public void setPower(double leftPower, double rightPower) {
		leftPower = safetyCheck(leftPower);
		rightPower = safetyCheck(rightPower);				

		leftMotors.set(leftPower);
		rightMotors.set(rightPower);	
	}

	public void stop() {
		setPower(0.0, 0.0);
	}

	private double safetyCheck(double power) {
		power = Math.min(1.0, power);
		power = Math.max(-1.0, power);
		return power;
	}
}