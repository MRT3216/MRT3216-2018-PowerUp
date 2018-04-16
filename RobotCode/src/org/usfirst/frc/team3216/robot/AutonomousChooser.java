package org.usfirst.frc.team3216.robot;

import org.usfirst.frc.team3216.lib.Logger;

public class AutonomousChooser {

	public static String getPath(String gameData) {
		String path = RobotMap.DRIVE_STRAIGHT;
		OI oi = Robot.oi;
		char pos = oi.getSide();
	
		char switchPos = gameData.charAt(0);

		Logger log = new Logger(RobotMap.LOG_OI, "AutonomousChooser");
		
		log.add("Position: " + pos , RobotMap.LOG_ROBOT);
		
		if(pos == 'D') {
			if(switchPos == 'L') {
				path = RobotMap.CENTER_SWITCH_LEFT;
			} else {
				path = RobotMap.CENTER_SWITCH_RIGHT;
			}
		} else {
			path = RobotMap.DRIVE_STRAIGHT;
		}
		return path;
	}
}
