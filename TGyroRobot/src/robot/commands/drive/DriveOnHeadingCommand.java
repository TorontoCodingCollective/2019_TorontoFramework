package robot.commands.drive;

import robot.Robot;

/**
 *
 */
public class DriveOnHeadingCommand extends TSafeCommand {

	private double  heading;
	private double  speed;
	private final boolean brakeWhenFinished;
	private boolean error = false;
	
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
	 */
    public DriveOnHeadingCommand(double heading, double speed, 
    		double timeout) {
    	this(heading, speed, timeout, true);
    }
    	
    public DriveOnHeadingCommand(double heading, double speed, 
    		double timeout, boolean brakeWhenFinished) {
    	
    	super(timeout);
    	
    	if (heading < 0 || heading >= 360) {
    		System.out.println("Heading on DriveOnHeadingCommand must be >= 0 or < 360 degrees. "
    				+ heading + " is invalid.  Command ending immediately");
    		error = true;
    		this.brakeWhenFinished = true;
    		return;
    	}
    	
    	this.speed = speed;
    	this.brakeWhenFinished = brakeWhenFinished;
    	
    	requires(Robot.chassisSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.chassisSubsystem.driveOnHeading(speed, heading);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	// If there is an error, then do nothing
    	if (error) {
    		return;
    	}

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	if (error) {
    		return true;
    	}
    	
    	if (super.isFinished()) {
    		return true;
    	}

    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	if (brakeWhenFinished) {
    		Robot.chassisSubsystem.setSpeed(0, 0);
    	}
    }

}
