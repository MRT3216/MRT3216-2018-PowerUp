package org.usfirst.frc.team3216.robot;

import org.usfirst.frc.team3216.robot.RobotMap.AutonomousModes;
import org.usfirst.frc.team3216.robot.RobotMap.StartingPositions;

public class AutonomousChooser {

	public static String getPath(String gameData) {
		String path = RobotMap.DRIVE_STRAIGHT;
		StartingPositions startingPosition = RobotMap.STARTING_POSITION;
		AutonomousModes autonomousMode = RobotMap.AUTONOMOUS_MODE;

		char switchPos = gameData.charAt(0);
		char scalePos = gameData.charAt(1);

		switch (autonomousMode) {
		case CROSS_LINE:
			path = RobotMap.DRIVE_STRAIGHT;
			break;
		case SWITCH:
			if (startingPosition == StartingPositions.LEFT && switchPos == 'L') {
				path = RobotMap.LEFT_SWITCH_LEFT;
			} else if (startingPosition == StartingPositions.LEFT && switchPos == 'R') {
				path = RobotMap.LEFT_SWITCH_RIGHT;
			} else if (startingPosition == StartingPositions.RIGHT && switchPos == 'L') {
				path = RobotMap.RIGHT_SWITCH_LEFT;
			} else if (startingPosition == StartingPositions.RIGHT && switchPos == 'R') {
				path = RobotMap.RIGHT_SWITCH_RIGHT;
			}
			break;
		case SCALE:
			if (startingPosition == StartingPositions.LEFT && scalePos == 'L') {
				path = RobotMap.LEFT_SCALE_LEFT;
			} else if (startingPosition == StartingPositions.LEFT && scalePos == 'R') {
				path = RobotMap.LEFT_SCALE_RIGHT;
			} else if (startingPosition == StartingPositions.RIGHT && scalePos == 'L') {
				path = RobotMap.RIGHT_SCALE_LEFT;
			} else if (startingPosition == StartingPositions.RIGHT && scalePos == 'R') {
				path = RobotMap.RIGHT_SCALE_RIGHT;
			}
			break;
		case EASIEST:
			if (startingPosition == StartingPositions.LEFT && switchPos == 'L') {
				path = RobotMap.LEFT_SWITCH_LEFT;
			} else if (startingPosition == StartingPositions.RIGHT && switchPos == 'R') {
				path = RobotMap.RIGHT_SWITCH_RIGHT;
			} else if (startingPosition == StartingPositions.LEFT && scalePos == 'L') {
				path = RobotMap.LEFT_SCALE_LEFT;
			} else if (startingPosition == StartingPositions.RIGHT && scalePos == 'R') {
				path = RobotMap.RIGHT_SCALE_RIGHT;
			} else if (startingPosition == StartingPositions.RIGHT && scalePos == 'L') {
				path = RobotMap.RIGHT_SCALE_RIGHT;
			}
			/*
			if (startingPosition == StartingPositions.LEFT && switchPos == 'L') {
				path = RobotMap.LEFT_SWITCH_LEFT;
			} else if (startingPosition == StartingPositions.RIGHT && switchPos == 'R') {
				path = RobotMap.RIGHT_SWITCH_RIGHT;
			} else if (startingPosition == StartingPositions.LEFT && scalePos == 'L') {
				path = RobotMap.LEFT_SCALE_LEFT;
			} else if (startingPosition == StartingPositions.RIGHT && scalePos == 'R') {
				path = RobotMap.RIGHT_SCALE_RIGHT;
			} else if (startingPosition == StartingPositions.LEFT && switchPos == 'R') {
				path = RobotMap.LEFT_SWITCH_LEFT;
			} else if (startingPosition == StartingPositions.RIGHT && switchPos == 'L') {
				path = RobotMap.RIGHT_SWITCH_RIGHT;
			} else if (startingPosition == StartingPositions.LEFT && scalePos == 'R') {
				path = RobotMap.LEFT_SCALE_LEFT;
			} else if (startingPosition == StartingPositions.RIGHT && scalePos == 'L') {
				path = RobotMap.RIGHT_SCALE_RIGHT;
			}*/
			break;
		default:
			break;
		}
		return path;
	}
}
