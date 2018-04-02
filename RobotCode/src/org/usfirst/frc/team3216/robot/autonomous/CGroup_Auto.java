package org.usfirst.frc.team3216.robot.autonomous;

import org.usfirst.frc.team3216.robot.RobotMap;
import org.usfirst.frc.team3216.robot.commands.CGroup_ShootCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CGroup_Auto extends CommandGroup {

	public CGroup_Auto() {
		addSequential(new Drivetrain_AutoProfileDistanceFollowers(RobotMap.DRIVE_STRAIGHT));
		addSequential(new CGroup_ShootCube());
	}
}