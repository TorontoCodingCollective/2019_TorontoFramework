package com.torontocodingcollective.oi;

/**
 * This class is used to name all of the buttons on a controller.
 * <p>
 * Depending on the controller type, different buttons are used.
 */
public enum TButton {

	// Joystick Controller
	/** Button 1 */
	ONE, 
	/** Button 2 */
	TWO, 
	/** Button 3 */
	THREE, 
	/** Button 4 */
	FOUR, 
	/** Button 5 */
	FIVE, 
	/** Button 6 */
	SIX,
	/** Button 7 */
	SEVEN, 
	/** Button 8 */
	EIGHT,
	/** Button 9 */
	NINE,
	/** Button 10 */
	TEN, 	
	/** Button 11 */
	ELEVEN, 	
	/** Button 12 */
	TWELVE,
	
	// GameController - Logitech, XBox
	/** XBox or Logitech Button A, equivalent to PS controllers {@link TButton#X_SYMBOL} */
	A, 
	/** XBox or Logitech Button B, equivalent to PS controllers {@link TButton#CIRCLE} */
	B, 
	/** XBox or Logitech Button X, equivalent to PS controllers {@link TButton#SQUARE} */
	X, 
	/** XBox or Logitech Button Y, equivalent to PS controllers {@link TButton#TRIANGLE} */
	Y, 
	
    // Playstation
	// Mapping of A, B, X, Y
	/** Playstation Button X symbol, equivalent to XBox controllers {@link TButton#A} */
	X_SYMBOL, 
	/** Playstation Button Circle, equivalent to XBox controllers {@link TButton#B} */
	CIRCLE, 
	/** Playstation Button Square, equivalent to XBox controllers {@link TButton#X} */
	SQUARE, 
	/** Playstation Button Triangle, equivalent to XBox controllers {@link TButton#Y} */
	TRIANGLE,

	/** Right Bumper */
	RIGHT_BUMPER, 
	/** Left Bumper */
	LEFT_BUMPER, 
	/** Back Button */
	BACK, 
	/** Start Button */
	START,
	
	
	// PS3 mapping of BACK button
	/** Playstation 3 Select button, equivalent to XBox controllers {@link TButton#BACK} */
	SELECT,
	
	// PS4 
	// Mapping of BACK and START buttons
	/** Playstation 4 Share button, equivalent to XBox controllers {@link TButton#BACK} */
	SHARE, 
	/** Playstation 4 Share button, equivalent to XBox controllers {@link TButton#START} */
	OPTIONS,
	
	// Additional PS4 buttons
	/** Playstation 4 Touch pad button */
	TOUCHPAD, 
	/** Playstation 4 PlayStation button */
	PS;
	

}
