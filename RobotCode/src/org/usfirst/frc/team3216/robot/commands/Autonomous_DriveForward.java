package org.usfirst.frc.team3216.robot.commands;

import org.usfirst.frc.team3216.lib.Logger;
import org.usfirst.frc.team3216.robot.OI;
import org.usfirst.frc.team3216.robot.Robot;
import org.usfirst.frc.team3216.robot.RobotMap;
import org.usfirst.frc.team3216.robot.subsystems.ADIS16448_IMU;
import org.usfirst.frc.team3216.robot.subsystems.Drivetrain;
import org.usfirst.frc.team3216.robot.subsystems.RangeFinder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
//import jaci.pathfinder.Pathfinder;
//import jaci.pathfinder.Trajectory;
//import jaci.pathfinder.Waypoint;

/**
 *
 */
public class Autonomous_DriveForward extends Command {
	private static final Logger.Level LOG_LEVEL = RobotMap.LOG_DRIVEFORWARD;
	
	Logger log = new Logger(LOG_LEVEL, getName());
	Drivetrain drivetrain = Robot.drivetrain;
	OI oi = Robot.oi;
	Timer timer = new Timer();
	RangeFinder rangeFinder = Robot.rangeFinder;
	ADIS16448_IMU imu = Robot.imu;
	
	//Trajectory trajectory;
	
    public Autonomous_DriveForward() {    	
    	requires(drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	log.add("Initialize", LOG_LEVEL);
    	drivetrain.stop();
    	
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
    	//Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 1.7, 2.0, 60.0);

    	// Generate the trajectory
    	//trajectory = Pathfinder.generate(points, config);
    	
    	timer.reset();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	log.add(Double.toString(rangeFinder.getAverageDistanceInInches()), LOG_LEVEL);
    	if(rangeFinder.getAverageDistanceInInches() > 25) {
    		drivetrain.setPower(0.1, .1);
    	}
    	else {
    		drivetrain.setPower(0,0);
    	}
    }
 
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	log.add("End", LOG_LEVEL);
    	terminate();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	log.add("Interrupted", LOG_LEVEL);
    	terminate();
    }
    
	/** Graceful End **********************************************************/
    private void terminate() {
		drivetrain.stop();
	} 
}
