package org.usfirst.frc.team3216.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import javax.management.ImmutableDescriptor;

import org.usfirst.frc.team3216.lib.Logger;
import org.usfirst.frc.team3216.robot.Robot;
import org.usfirst.frc.team3216.robot.RobotMap;
import org.usfirst.frc.team3216.robot.commands.Drivetrain_TankDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class Drivetrain extends Subsystem {
	
	/** Configuration Constants ***********************************************/
	private static final Logger.Level LOG_LEVEL = RobotMap.LOG_DRIVETRAIN;
	
	/** Instance Variables ****************************************************/
	private SpeedController leftMotors, rightMotors;
	private Logger log = new Logger(LOG_LEVEL, getName());
	private double headingGoal = 0.0;
	//private double currentHeading = Robot.imu.getAngle();
	private boolean holdHeading = false;
	double leftPowerOld, rightPowerOld;
	Timer timer = new Timer();

	public Drivetrain() {
		log.add("Drivetrain Constructor", LOG_LEVEL);
		
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
	}
	
	private void initMotor(VictorSP motor, boolean reverse) {
		motor.setInverted(reverse);
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new Drivetrain_TankDrive());
    }    

	/** Methods for setting the motors *************************************/
	public void setPower(double leftPower, double rightPower) {
		this.setPower(leftPower, rightPower, false);	
	}    
    
	public void setPower(double leftPower, double rightPower, boolean driveStraight) {
		leftPower = safetyCheck(leftPower);
		rightPower = safetyCheck(rightPower);				

		if(Math.abs(rightPower - leftPower) < RobotMap.JOYSTICK_EQUALITY_THRESHHOLD || driveStraight) {
			if(!holdHeading) {
				holdHeading = true;
				headingGoal = Robot.imu.getAngle();
			} else {
				double magnitude = (rightPower + leftPower) / 2;
//				currentHeading = Robot.imu.getAngle();
//				rightPower = magnitude + currentHeading * RobotMap.KP; 
//				leftPower = magnitude - currentHeading * RobotMap.KP;
			}			
		} else {
			holdHeading = false;
		}	
	
		leftMotors.set(leftPower);
		rightMotors.set(rightPower);
				
		leftPowerOld = leftPower;
		rightPowerOld = rightPower;
	}
	
	public void driveStraight(double power) {
	
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