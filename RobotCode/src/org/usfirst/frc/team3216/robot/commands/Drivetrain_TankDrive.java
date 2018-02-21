package org.usfirst.frc.team3216.robot.commands;

/**
 *
 */
public class Drivetrain_TankDrive extends Drivetrain_ArcadeDrive {
	public Drivetrain_TankDrive() {
		super();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		double leftPower = oi.getDriveLeft();
		double rightPower = oi.getDriveRight();

		execute(leftPower, rightPower);
	}

	@Override
	protected void execute(double leftPower, double rightPower) {
		double dt = timer.get();
		timer.reset();
		leftPower = restrictAcceleration(leftPower, leftPowerOld, dt);
		rightPower = restrictAcceleration(rightPower, rightPowerOld, dt);

		drivetrain.setPower(leftPower, rightPower);

		leftPowerOld = leftPower;
		rightPowerOld = rightPower;
	}
}
