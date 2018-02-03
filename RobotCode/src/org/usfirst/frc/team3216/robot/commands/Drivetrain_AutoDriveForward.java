package org.usfirst.frc.team3216.robot.commands;

import org.usfirst.frc.team3216.robot.Robot;
import org.usfirst.frc.team3216.robot.RobotMap;
import org.usfirst.frc.team3216.robot.subsystems.RangeFinder;
//import jaci.pathfinder.Pathfinder;
//import jaci.pathfinder.Trajectory;
//import jaci.pathfinder.Waypoint;

/**
 *
 */
public class Drivetrain_AutoDriveForward extends Drivetrain_Drive {
	protected RangeFinder rangeFinder = Robot.rangeFinder;
	protected double initialHeading = 0;
	
    public Drivetrain_AutoDriveForward() {    	
    	super();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	super.initialize();
    	
    	initialHeading = imu.getAngleZ();
    	
    	// 3 Waypoints
    	/*
    	Waypoint[] points = new Waypoint[] {
    	    new Waypoint(-4, -1, Pathfinder.d2r(-45)),      // Waypoint @ x=-4, y=-1, exit angle=-45 degrees
    	    new Waypoint(-2, -2, 0),                        // Waypoint @ x=-2, y=-2, exit angle=0 radians
    	    new Waypoint(0, 0, 0)                           // Waypoint @ x=0, y=0,   exit angle=0 radians
    	};
    	*/
    	
    	// Create the Trajectory Configuration
    	//
    	// Arguments:
    	// Fit Method:          HERMITE_CUBIC or HERMITE_QUINTIC
    	// Sample Count:        SAMPLES_HIGH (100 000)
    	//    	                SAMPLES_LOW  (10 000)
    	//    	               SAMPLES_FAST (1 000)
    	// Time Step:           0.05 Seconds
    	// Max Velocity:        1.7 m/s
    	// Max Acceleration:    2.0 m/s/s
    	// Max Jerk:            60.0 m/s/s/s
    	//Trajectory.Config config = new oTrajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 1.7, 2.0, 60.0);

    	// Generate the trajectory
    	//trajectory = Pathfinder.generate(points, config);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	log.add(Double.toString(rangeFinder.getSmoothedDistancedInInches()), LOG_LEVEL);
    	
    	if(rangeFinder.getSmoothedDistancedInInches() > RobotMap.AUTONOMOUS_RANGEFINDER_DISTANCE) {
    		driveStraight(-.5, initialHeading);
    	}
    	else {
    		drivetrain.setPower(0,0);
    	}
    }
}
