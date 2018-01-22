package org.usfirst.frc.team3216.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Encoder extends Subsystem {

    Encoder driveEncoderLeft = RobotMap.driveEncoderLeft;
    Encoder driveEncoderRight = RobotMap.driveEncoderRight;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

