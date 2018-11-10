package robot;

public class RobotConst {

    public static final String  TEST_ROBOT                    = "TestRobot";
    public static final String  PROD_ROBOT                    = "ProdRobot";

    // *********************************************************
    // Drive Constants
    // *********************************************************
    // Forward for the elevator is counter-clockwise when looking
    // from the back of the robot towards the front
    public static final double  MAX_LOW_GEAR_SPEED;
    public static final double  MAX_HIGH_GEAR_SPEED;

    public static final double  DRIVE_GYRO_PID_KP;
    public static final double  DRIVE_GYRO_PID_KI;
    public static final double  DRIVE_MAX_ROTATION_OUTPUT     = 0.6;

    public static final double  DRIVE_SPEED_PID_KP;
    public static final double  ENCODER_COUNTS_PER_INCH;

    // *********************************************************
    // For Ultrasonic Calibration
    // *********************************************************
    public static final double  ULTRASONIC_VOLTAGE_20IN       = 0.191;
    public static final double  ULTRASONIC_VOLTAGE_40IN       = 0.383;
    public static final double  ULTRASONIC_VOLTAGE_80IN       = 0.764;

    public static enum Direction {
        FORWARD, BACKWARD
    };

    // The TorontoCodingCollective framework was developed to run on different
    // robots through the use of multiple mappings and constants.
    public static final String robot = TEST_ROBOT;

    static {

        switch (robot) {

        case TEST_ROBOT:
        default:
            MAX_LOW_GEAR_SPEED = 580.0; // Encoder counts/sec
            MAX_HIGH_GEAR_SPEED = 2000.0;

            DRIVE_GYRO_PID_KP = .05;
            DRIVE_GYRO_PID_KI = 0;

            DRIVE_SPEED_PID_KP = 0.3;
            ENCODER_COUNTS_PER_INCH = 55.6;

            break;
        }

    }
}
