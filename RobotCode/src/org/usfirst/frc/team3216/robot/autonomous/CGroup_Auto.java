package org.usfirst.frc.team3216.robot.autonomous;

import org.usfirst.frc.team3216.robot.OI;
import org.usfirst.frc.team3216.robot.Robot;
import org.usfirst.frc.team3216.robot.commands.CGroup_ShootCube;
import org.usfirst.frc.team3216.robot.commands.Delay;
import org.usfirst.frc.team3216.robot.commands.Elevator_Jerk;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CGroup_Auto extends CommandGroup {

	public CGroup_Auto(String path) {
		OI oi = Robot.oi;
		
		char pos = oi.getSide();
		addSequential(new Elevator_Jerk(-1));
		addSequential(new Delay(.15));
		addSequential(new Elevator_Jerk(0));
		addSequential(new Drivetrain_AutoProfileDistanceFollowers(path));		

		if (pos == 'D') {
			addSequential(new CGroup_ShootCube());
		}
	}
}