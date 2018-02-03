package org.usfirst.frc.team3216.robot.commands;

import org.usfirst.frc.team3216.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CGroup_ShootCube extends CommandGroup {

    public CGroup_ShootCube() {
    	addSequential(new Pneumatics_OpenPincher());
    	addSequential(new Delay(RobotMap.SHOOT_CUBE_DELAY));
    	addSequential(new Pneumatics_ExtendPopper());
    }
}
