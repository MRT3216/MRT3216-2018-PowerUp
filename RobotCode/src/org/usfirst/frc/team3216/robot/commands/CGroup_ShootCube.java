package org.usfirst.frc.team3216.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CGroup_ShootCube extends CommandGroup {

    public CGroup_ShootCube() {
    	addSequential(new Pneumatics_OpenPincher());
    	//addSequential(new Delay(RobotMap.SHOOT_CUBE_DELAY));
    	addParallel(new Pneumatics_ExtendPopper());
    	//addSequential(new Pneumatics_ExtendPopper());
    	addSequential(new Delay(.25));
    	addSequential(new Pneumatics_RetractPopper());
    }
}
