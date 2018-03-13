package org.usfirst.frc.team3216.robot.autonomous;

import org.usfirst.frc.team3216.robot.RobotMap;
import org.usfirst.frc.team3216.robot.commands.CGroup_ShootCube;
import org.usfirst.frc.team3216.robot.commands.Drivetrain_AutoProfileDistanceFollowers;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CGroup_Auto_StraightSwitch extends CommandGroup {

	public CGroup_Auto_StraightSwitch() {
		addParallel(new Drivetrain_AutoProfileDistanceFollowers(RobotMap.DRIVE_STRAIGHT));
		addSequential(new CGroup_ShootCube());
	}
}