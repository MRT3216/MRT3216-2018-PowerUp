package org.usfirst.frc.team3216.robot;

import org.usfirst.frc.team3216.robot.RobotMap.AutonomousModes;
import org.usfirst.frc.team3216.robot.RobotMap.StartingPositions;
import org.usfirst.frc.team3216.robot.commands.CGroup_Auto_StraightSwitch;
import org.usfirst.frc.team3216.robot.commands.Drivetrain_AutoDriveForward;

import edu.wpi.first.wpilibj.command.Command;

public class AutonomousChooser {
	StartingPositions startingPosition = RobotMap.STARTING_POSITION;
	AutonomousModes autonomousMode = RobotMap.AUTONOMOUS_MODE;
	Command autonomousCommand;

	public Command Cross_Line(String gameData) {
		if(startingPosition == StartingPositions.CENTER) {
			autonomousCommand = new Drivetrain_AutoDriveForward(25); //For now it drives straight, but when we have 
   																	 //motion profiling working, we'll want to have it 
																	 //turn and go around the center stack of cubes.
		}
		else {
			autonomousCommand = new Drivetrain_AutoDriveForward(25);
		}
		return autonomousCommand;
	}

	public Command Switch(String gameData) {
		if((startingPosition == StartingPositions.LEFT && gameData.charAt(0) == 'L') || (startingPosition == StartingPositions.RIGHT && gameData.charAt(0) == 'R')) {
			autonomousCommand = new CGroup_Auto_StraightSwitch();
		}
		else if(startingPosition == StartingPositions.CENTER) {
			//TODO: commands for doing switch for center position.
			autonomousCommand = new Drivetrain_AutoDriveForward(25);
		}
		return autonomousCommand;
	}

	public Command Scale(String gameData) {
		if((startingPosition == StartingPositions.LEFT)) {
			if(gameData.charAt(1) == 'L') {
				//autonomousCommand = (command for left scale starting on left side)
			}
			else {
				//autonomousCommand = (command for left scale starting on right side)
			}
		}
		else if((startingPosition == StartingPositions.RIGHT)) {
			if(gameData.charAt(1) == 'R') {
				//autonomousCommand = (command for right scale starting on right side)
			}
			else {
				//autonomousCommand = (command for right scale starting on left side)
			}
		}
		else {
			autonomousCommand = new Drivetrain_AutoDriveForward(25);
		}
		return autonomousCommand;
	}
}
