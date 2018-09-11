package com.torontocodingcollective.commands;

import com.torontocodingcollective.oi.TOi;
import com.torontocodingcollective.subsystem.TGryoDriveSubsystem;

/**
 * Drive On Heading Command
 * <p>
 * This command is used to drive on a specified heading
 * for a specified amount of time.
 * <p>
 * The command can be extended to provide additional
 * stopping variations (distance, sensor, etc).
 */
public class TDriveOnHeadingCommand extends TSafeCommand {

	private double  heading;
	private double  speed;
	private final boolean brakeWhenFinished;
	private boolean error = false;
	
	private final TGryoDriveSubsystem driveSubsystem;
	
	/**
	 * Construct a new DriveOnHeadingCommand
	 * @param heading in the range 0 <= heading < 360.  If the heading
	 * is not in this range, then the command will end immediately and 
	 * print an error to the DriverStation
	 * @param speed at which to drive in the range 0 <= speed <= 1.0.
	 * if the speed is set to a very small value, the robot will not
	 * drive and the command will end on the timeout.
	 * @param timeout in seconds after which this command will
	 * end
	 * @param oi that extend the TOi operator input class
	 * @param driveSubsystem that extends the TGyroDriveSubsystem
	 */
    public TDriveOnHeadingCommand(double heading, double speed, 
    		double timeout, TOi oi, TGryoDriveSubsystem driveSubsystem) {
    	this(heading, speed, timeout, true, oi, driveSubsystem);
    }
    
	/**
	 * Construct a new DriveOnHeadingCommand
	 * @param heading in the range 0 <= heading < 360.  If the heading
	 * is not in this range, then the command will end immediately and 
	 * print an error to the DriverStation
	 * @param speed at which to drive in the range 0 <= speed <= 1.0.
	 * if the speed is set to a very small value, the robot will not
	 * drive and the command will end on the timeout.
	 * @param timeout in seconds after which this command will
	 * end
     * @param brakeWhenFinished {@code true} to brake when the 
     * command finishes {@code false} to coast into the next
     * command.
	 * @param oi that extend the TOi operator input class
	 * @param driveSubsystem that extends the TGyroDriveSubsystem
     */
    public TDriveOnHeadingCommand(double heading, double speed, 
    		double timeout, boolean brakeWhenFinished,
    		TOi oi, TGryoDriveSubsystem driveSubsystem) {
    	
    	super(timeout, oi);
    	
    	this.driveSubsystem = driveSubsystem;
    	
    	requires(driveSubsystem);

    	if (heading < 0 || heading >= 360) {
    		System.out.println("Heading on DriveOnHeadingCommand must be >= 0 or < 360 degrees. "
    				+ heading + " is invalid.  Command ending immediately");
    		error = true;
    		this.brakeWhenFinished = true;
    		return;
    	}
    	
    	this.speed = speed;
    	this.brakeWhenFinished = brakeWhenFinished;
    }

    @Override
    protected void initialize() {
    	driveSubsystem.driveOnHeading(speed, heading);
    }

    @Override
    protected void execute() {
    	
    	// If there is an error, then do nothing
    	if (error) {
    		return;
    	}

    	// Update the speed and direction each loop.
    	// If the values have not changed, this call will
    	// have no effect.
    	driveSubsystem.driveOnHeading(speed, heading);
    }

    /**
     * Adjust the speed on the driveOnHeading command
     * <p>
     * This routine is used to adjust the speed in the drive on heading
     * command without changing the heading.
     * <p>
     * This routine could be used to support acceleration and deceleration
     * when driving on a heading.
     */
    public void setSpeed(double speed) {
    	this.speed = speed;
    }

    @Override
    protected boolean isFinished() {
    	
    	if (error) {
    		return true;
    	}
    	
    	// Check for a timeout or cancel
    	if (super.isFinished()) {
    		return true;
    	}

    	return false;
    }

    @Override
    protected void end() {
    	if (brakeWhenFinished) {
    		driveSubsystem.setSpeed(0, 0);
    	}
    }
}
