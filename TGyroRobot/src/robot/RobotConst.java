package robot;

public class RobotConst {
	
	// The TorontoCodingCollective framework was developed to run on different
	// robots.
	// Supported robots are 1311 and 1321.
	public static int robot = 1310;

	
	public static final boolean INVERTED = true;
	public static final boolean NOT_INVERTED = false;
	public static final char LEFT = 'L';
	public static final char RIGHT = 'R';

	//*********************************************************
	// Drive Constants
	//*********************************************************
	// Forward for the elevator is counter-clockwise when looking 
	// from the back of the robot towards the front
	public static final boolean ELEVATOR_MOTOR_ORIENTATION;
	public static final boolean ELEVATOR_ENCODER_ORIENTATION;
	public static final double ELEVATOR_LOCK_SPEED_WITH_CUBE = 0.25;
	public static final double ELEVATOR_LOCK_SPEED = 0.15;

	public static final double MAX_LOW_GEAR_SPEED;
	public static final double MAX_HIGH_GEAR_SPEED;
	
	public static final double DRIVE_GYRO_PID_KP;
	public static final double DRIVE_GYRO_PID_KI;
	public static final double DRIVE_MAX_ROTATION_OUTPUT = 0.6;
	
	public static final double DRIVE_SPEED_PID_KP;
	public static final double ENCODER_COUNTS_PER_INCH;
	public static final double ELEVATOR_ENCODER_COUNTS_PER_INCH;
	
	public static final double INTAKE_TILT_COUNTS_PER_DEGREE;


	
	//*********************************************************
	// For Ultrasonic Calibration
	//*********************************************************
	public static final double ULTRASONIC_VOLTAGE_20IN = 0.191;
	public static final double ULTRASONIC_VOLTAGE_40IN = 0.383;
	public static final double ULTRASONIC_VOLTAGE_80IN = 0.764;
	
	public static enum Direction { FORWARD, BACKWARD };
	
	
	static {
		
		switch (robot) {

		case 1311:
		case 1310:
			MAX_LOW_GEAR_SPEED = 580.0;    // Encoder counts/sec
			MAX_HIGH_GEAR_SPEED = 2000.0;
			
			DRIVE_GYRO_PID_KP = .05;
			DRIVE_GYRO_PID_KI = 0;
			
			DRIVE_SPEED_PID_KP = 0.3;
			ENCODER_COUNTS_PER_INCH = 55.6;
			
			ELEVATOR_MOTOR_ORIENTATION = NOT_INVERTED;
			ELEVATOR_ENCODER_ORIENTATION = INVERTED;

			ELEVATOR_ENCODER_COUNTS_PER_INCH = 186.67;
			INTAKE_TILT_COUNTS_PER_DEGREE = 438;
			break;
			
		case 1321:
		default:
			MAX_LOW_GEAR_SPEED = 365.0;    // Encoder counts/sec
			MAX_HIGH_GEAR_SPEED = 830.0;
			
			DRIVE_GYRO_PID_KP = 0.05;
			DRIVE_GYRO_PID_KI = 0.001;
			
			DRIVE_SPEED_PID_KP = 0.75;
			ENCODER_COUNTS_PER_INCH = 51.5;
			
			ELEVATOR_MOTOR_ORIENTATION = NOT_INVERTED;
			ELEVATOR_ENCODER_ORIENTATION = INVERTED;

			ELEVATOR_ENCODER_COUNTS_PER_INCH = 186.67;
			INTAKE_TILT_COUNTS_PER_DEGREE = 461.11;
			break;
		}
	}
}
