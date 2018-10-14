package robot.commands.drive;

import com.torontocodingcollective.commands.TDefaultDriveCommand;

import robot.Robot;
import robot.oi.OI;
import robot.subsystems.DriveSubsystem;

/**
 *
 */
public class DefaultDriveCommand extends TDefaultDriveCommand {

	OI oi = Robot.oi;
	DriveSubsystem driveSubsystem = Robot.driveSubsystem;
	
	public DefaultDriveCommand() {
		// The drive logic will be handled by the TDefaultDriveCommand
		// which also contains the requires(driveSubsystem) statement
		super(Robot.oi, Robot.driveSubsystem);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {

		// Enable turbo mode
		if (oi.getTurboOn()) {
			driveSubsystem.enableTurbo();
		}
		else {
			driveSubsystem.disableTurbo();
		}

		// Drive using the TDefaultDriveCommand
		// buttons and drive mode
		super.execute();
	}

	@Override
	protected boolean isFinished() {
		// The default command does not end
		return false;
	}
}
