package com.torontocodingcollective.oi;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Handles input from a GameController 
 * <p>
 * This class adds functionality to the standard FRC {@link edu.wpi.first.wpilibj.Joystick} class
 * to access all buttons and axis by name for a standard game controller.
 * <p>
 * This abstract class has 3 known implementations
 * <br> {@link TGameController_Logitech}
 * <br> {@link TGameController_PS}
 * <br> {@link TGameController_Xbox}
 *
 */
public abstract class TGameController extends Joystick {

	/**
	 * Construct an instance of a GameController. 
	 * <p>
	 * The joystick index is the USB port on the drivers station.
	 * @param port The port on the Driver Station that the joystick is plugged into.
	 */
	protected TGameController(int port) {
		super(port);
	}

	/**
	 * Get the axis of the GameController stick
	 * @param stick {@link TStick#LEFT} or {@link TStick#RIGHT}
	 * @param axis {@link TAxis#X} or {@link TAxis#Y}
	 * @return double value in the range 0 to 1.0
	 */
	public abstract double getAxis(TStick stick, TAxis axis);

	/**
	 * Get the button on the GameController
	 * @param button a valid {@link TButton} value for this GameController.
	 * <br> NOTE: if the button is not valid for the controller, then the 
	 * value false
	 * @return boolean {@code true} if pressed, {@code false} otherwise.
	 */
	public abstract boolean getButton(TButton button);

	/**
	 * Get the button on the GameController corresponding to the press of the stick
	 * on the controller
	 * <p>
	 * All game controllers have a button attached to the stick that is 
	 * activated by pushing the stick downward.  This routine returns the 
	 * value of that button press.
	 * @param stick {@link TStick} value on this gamecontroller.
	 * <br> NOTE: if the stick is not valid for the controller, then the 
	 * value returned is {@code false}
	 * @return boolean {@code true} if pressed, {@false otherwise}
	 */
	public abstract boolean getButton(TStick stick);
	
	/**
	 * Get the value of the trigger as a button
	 * <p>
	 * Some game controllers have a button trigger, and some have an analog
	 * trigger.  This routine returns the value of a trigger as a button
	 * value of {@code true} or {@code false}
	 * @param trigger {@link TTrigger#LEFT} or {@link TTrigger#RIGHT}
	 * <br> NOTE: if the trigger is not valid for the game controller then the 
	 * value returned is {@code false}
	 * @return boolean {@code true} if the trigger is pressed {@code false} otherwise.
	 */
	public boolean getButton(TTrigger trigger) {
		return getTrigger(trigger) > 0.3;
	};

	/**
	 * Set the rumble value for this game controller
	 * <p>
	 * The TorontoJar currently only supports a single rumble value which is output
	 * to both left and right channels simultaneously.
	 * <br>NOTE: This routine does not turn off the rumble once set to a non-zero
	 * value.  Users should set the rumble value off (0) when the rumble is finished.
	 * @param volume a double value between 0 (off) and 1.0 (full rumble) for the 
	 * rumble.
	 */
	public void setRumble(double volume) {
		super.setRumble(RumbleType.kLeftRumble, volume);
		super.setRumble(RumbleType.kRightRumble, volume);
	}

	/**
	 * Get the trigger on the GameController
	 * @param trigger {@link TTrigger#LEFT} or {@link TTrigger#RIGHT} for this GameController.
	 * <br> NOTE: if the trigger is not valid for the controller, then the 
	 * value returned is 0.0
	 * @return double value in the range 0 to 1.0
	 */
	public abstract double getTrigger(TTrigger trigger);
}
