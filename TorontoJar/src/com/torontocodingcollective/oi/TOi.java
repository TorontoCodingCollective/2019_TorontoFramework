package com.torontocodingcollective.oi;

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
public abstract class TOi {

	/** 
	 * Return the state of the cancel command button.
	 * <p>
	 * Typically this is the Back button on the 
	 * Driver controller
	 * @return {@code true} if the cancel button 
	 * is currently pressed {@code false} otherwise
	 */
	public abstract boolean getCancelCommand();
}
