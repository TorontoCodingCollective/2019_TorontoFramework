package com.torontocodingcollective.commands;

import com.torontocodingcollective.oi.TOi;
import com.torontocodingcollective.subsystem.TGryoDriveSubsystem;

/**
 * Rotate To Heading Command
 * <p>
 * This command is used to pivot the robot on the spot to align
 * with the given heading.
 * <p>
 * This command will end when the angle is reached and the rotational
 * speed is below the threshold
 */
public class TRotateToHeadingCommand extends TSafeCommand {

	public static final double DEFAULT_TIMEOUT = 15.0;

	private final double heading;
	private final double maxRotationOutput;

	private final TGryoDriveSubsystem driveSubsystem;
	
	private boolean error = false;
	
	/**
	 * Rotate to the specified heading
	 * <p>
	 * This command will use the maxRotation speed specified in the drive
	 * subsystem
	 * @param heading 0 <= heading < 360
	 * @param oi that extend the TOi operator input class
	 * @param driveSubsystem that extends the TGyroDriveSubsystem
	 */
	public TRotateToHeadingCommand(double heading, 
			TOi oi, TGryoDriveSubsystem driveSubsystem) {

		this(heading, -1, DEFAULT_TIMEOUT, oi, driveSubsystem);
	}

	/**
	 * Rotate to the specified heading
	 * <p>
	 * This command will use the maxRotation speed specified as long
	 * as it does not exceed the speed set in the subsystem.
	 * @param heading 0 <= heading < 360
	 * @param maxRotationOutput a speed of -1 will indicate to use the default
	 * set in the subsystem.  The subsystem maxRotationOutput will be used
	 * to override this value if it is set higher than the value in the subsystem.
	 * See {@link TGyroDriveSubsystem#setMaxRotationOutput()}
	 * @param timeout command timeout in seconds
	 * @param oi that extend the TOi operator input class
	 * @param driveSubsystem that extends the TGyroDriveSubsystem
	 */
	public TRotateToHeadingCommand(double heading, double maxRotationOutput, 
			double timeout,	TOi oi, TGryoDriveSubsystem driveSubsystem) {
		
		super(timeout, oi);
		
		this.driveSubsystem = driveSubsystem;
		
		requires(driveSubsystem);
		
    	if (heading < 0 || heading >= 360) {
    		System.out.println("Heading on DriveOnHeadingCommand must be >= 0 or < 360 degrees. "
    				+ heading + " is invalid.  Command ending immediately");
    		this.heading = 0;
    		this.maxRotationOutput = -1;
    		return;
    	}
    	
    	this.heading = heading;
		this.maxRotationOutput = maxRotationOutput;
	}
	
	@Override
	protected void initialize() {
		
		if (error) {
			return;
		}
		
		if (maxRotationOutput <= 0) {
			driveSubsystem.rotateToHeading(heading);
		} else {
			driveSubsystem.rotateToHeading(heading, maxRotationOutput);
		}
	}

	@Override
	protected void execute() {
	}
	
	protected void end(){
		driveSubsystem.setSpeed(0,0);
	}
	
	protected boolean isFinished() {

		if (error) {
			return true;
		}
		
		if (super.isFinished()) {
			return true;
		}

		// If the angle is close to the required heading and the 
		// rotational speed is low (not an overshoot), then end
		double rotationRate = driveSubsystem.getGyroRate();
		double headingError = driveSubsystem.getGyroHeadingError();

		if (Math.abs(headingError) <= 4 && Math.abs(rotationRate) < 4) {
			return true;
		}
		
		return false;
	}

}
