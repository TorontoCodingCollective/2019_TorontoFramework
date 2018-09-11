package robot.oi;

import com.torontocodingcollective.oi.TAxis;
import com.torontocodingcollective.oi.TButton;
import com.torontocodingcollective.oi.TGameController;
import com.torontocodingcollective.oi.TGameController_Logitech;
import com.torontocodingcollective.oi.TOi;
import com.torontocodingcollective.oi.TRumbleManager;
import com.torontocodingcollective.oi.TStick;
import com.torontocodingcollective.oi.TToggle;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */


/**Driver Controller
 * 	Sticks:
 * 		Right Stick X-axis 	= Drive Motor Turn 
 * 		Left Stick Y-axis  	= Drive Motor Speed
 * 		Right Stick Press  	= Toggle PIDs
 * 		Left Stick Press 	= Toggle Compressor
 * 	Buttons:
 * 		Start Button 		= Reset Encoders and Gyro 
 * 		Back Button 		= Cancel any Command
 * 		X Button			= Automatic intake
 * 		B Button			= Automatic intake cancel
 * 		A Button			= Climb arm up
 * 		Y Button			= Climb arm down
 * 
 * 	Bumpers/Triggers:
 *  
 *  POV:
 *  	Any Angle			= Rotate to the Pressed Angle
 * 
 */
public class OI extends TOi {

	private TGameController driverController = new TGameController_Logitech(0);

	public AutoSelector autoSelector = new AutoSelector();
	public TRumbleManager driverRumble = new TRumbleManager("Driver", driverController);

	private TToggle compressorToggle = new TToggle(driverController, TStick.LEFT);
	private TToggle speedPidToggle = new TToggle(driverController, TStick.RIGHT);

	public void init() {
		compressorToggle.set(true);
		speedPidToggle.set(false);
	}

	//Driver Controller
	public double getSpeed() {
		return - driverController.getAxis(TStick.LEFT, TAxis.Y);
	}

	public double getTurn() {
		return driverController.getAxis(TStick.RIGHT, TAxis.X);
	}

	public boolean getCancelCommand() {
		return driverController.getButton(TButton.BACK);
	}

	public boolean reset() {
		return driverController.getButton(TButton.START);
	}

	public int getPovAngle() {
		return driverController.getPOV();
	}

	public boolean getTurboOn() {
		return driverController.getButton(TButton.LEFT_BUMPER);
	}

	public boolean getCompressorEnabled() {
		return compressorToggle.get();
	}

	public boolean getSpeedPidEnabled() {
		return speedPidToggle.get();
	}

	public void setSpeedPidToggle(boolean state) {
		speedPidToggle.set(state);
	}

	public void updatePeriodic() {

		// Update all Toggles
		compressorToggle.updatePeriodic();
		speedPidToggle.updatePeriodic();
		driverRumble.updatePeriodic();
		
		// Update all SmartDashboard values
		SmartDashboard.putBoolean("Speed PIDs Enabled", getSpeedPidEnabled());
		SmartDashboard.putBoolean("Compressor Enabled", getCompressorEnabled());
	}
}
