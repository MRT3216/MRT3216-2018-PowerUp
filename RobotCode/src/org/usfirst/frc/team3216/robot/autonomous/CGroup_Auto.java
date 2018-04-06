package org.usfirst.frc.team3216.robot.autonomous;

import org.usfirst.frc.team3216.robot.OI;
import org.usfirst.frc.team3216.robot.Robot;
import org.usfirst.frc.team3216.robot.commands.CGroup_ShootCube;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CGroup_Auto extends CommandGroup {

	public CGroup_Auto(String path) {
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		OI oi = Robot.oi;
		
		char pos = oi.getSide();
		char switchPos = gameData.charAt(0);
		
		addSequential(new Drivetrain_AutoProfileDistanceFollowers(path));		

		if (switchPos == 'R' && pos == 'R') {
			addSequential(new CGroup_ShootCube());
		} else if (switchPos == 'L' && pos == 'L') {
			addSequential(new CGroup_ShootCube());
		}
	}
}