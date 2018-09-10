package robot.subsystems;

import com.torontocodingcollective.sensors.encoder.TEncoder;
import com.torontocodingcollective.sensors.gyro.TNavXGyro;
import com.torontocodingcollective.speedcontroller.TCanSpeedController;
import com.torontocodingcollective.subsystem.TGryoDriveSubsystem;

import robot.RobotConst;
import robot.RobotMap;
import robot.commands.drive.DefaultChassisCommand;

/**
 *
 */
public class ChassisSubsystem extends TGryoDriveSubsystem {

	public ChassisSubsystem() {

		// Uncomment this constructor to use PWM based Speed controllers
		super(	
				// Gyro used for this subsystem
				new TNavXGyro(),
				// Left Speed Controller
				new TCanSpeedController(
						RobotMap.LEFT_DRIVE_SPEED_CONTROLLER_TYPE, 
						RobotMap.LEFT_DRIVE_SPEED_CONTROLLER_CAN_ADDRESS,
						RobotMap.LEFT_DRIVE_FOLLOWER_SPEED_CONTROLLER_TYPE,
						RobotMap.LEFT_DRIVE_FOLLOWER_SPEED_CONTROLLER_CAN_ADDRESS,
						RobotMap.LEFT_DRIVE_MOTOR_ISINVERTED),
				// Right Speed Controller
				new TCanSpeedController(
						RobotMap.RIGHT_DRIVE_SPEED_CONTROLLER_TYPE, 
						RobotMap.RIGHT_DRIVE_SPEED_CONTROLLER_CAN_ADDRESS, 
						RobotMap.RIGHT_DRIVE_FOLLOWER_SPEED_CONTROLLER_TYPE,
						RobotMap.RIGHT_DRIVE_FOLLOWER_SPEED_CONTROLLER_CAN_ADDRESS,
						RobotMap.RIGHT_DRIVE_MOTOR_ISINVERTED),
				// PID Constants
				RobotConst.DRIVE_GYRO_PID_KP,
				RobotConst.DRIVE_GYRO_PID_KI,
				RobotConst.DRIVE_MAX_ROTATION_OUTPUT);

				// Get the encoders attached to the CAN bus speed controller.
				TEncoder leftEncoder  = ((TCanSpeedController) super.leftMotor) .getEncoder();
				TEncoder rightEncoder = ((TCanSpeedController) super.rightMotor).getEncoder();

				super.setEncoders(
						leftEncoder,
						rightEncoder,
						RobotConst.DRIVE_SPEED_PID_KP,
						RobotConst.MAX_LOW_GEAR_SPEED);

	}

	@Override
	public void init() {
	};

	// Initialize the default command for the Chassis subsystem.
	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new DefaultChassisCommand());
	}

	@Override
	public void updatePeriodic() {

		super.updatePeriodic();

	}

}
