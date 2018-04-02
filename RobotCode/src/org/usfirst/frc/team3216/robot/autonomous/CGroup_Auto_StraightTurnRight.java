package org.usfirst.frc.team3216.robot.autonomous;

import org.usfirst.frc.team3216.robot.RobotMap;
import org.usfirst.frc.team3216.robot.commands.CGroup_ShootCube;
import org.usfirst.frc.team3216.robot.commands.Drivetrain_AutoProfileDistanceFollowers;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CGroup_Auto_StraightTurnRight extends CommandGroup {

	public CGroup_Auto_StraightTurnRight() {
		addSequential(new Drivetrain_AutoProfileDistanceFollowers(RobotMap.FORWARD_TURN_RIGHT));
		addSequential(new CGroup_ShootCube());
	}
}