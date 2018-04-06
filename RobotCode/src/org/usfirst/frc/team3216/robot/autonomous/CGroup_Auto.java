package org.usfirst.frc.team3216.robot.autonomous;

import org.usfirst.frc.team3216.robot.RobotMap;
import org.usfirst.frc.team3216.robot.commands.CGroup_ShootCube;
import org.usfirst.frc.team3216.robot.commands.Elevator_GoTo;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CGroup_Auto extends CommandGroup {

	public CGroup_Auto(String path) {
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		addSequential(new Drivetrain_AutoProfileDistanceFollowers(path));
		if(path.contains("Scale")) {
			addParallel(new Elevator_GoTo(true));
		}
		if (gameData.charAt(0) == 'R') {
			addSequential(new CGroup_ShootCube());
		}
	}
}