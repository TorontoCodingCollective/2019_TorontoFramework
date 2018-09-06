package com.torontocodingcollective.speedcontroller;

import edu.wpi.first.wpilibj.DMC60;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SD540;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;

/**
 * Enum of all PWM speed controller types supported by the wpilib.
 */
public enum TPwmSpeedControllerType {
	/** DMC60 see {@link DMC60} */
	DMC60, 
	/** SD540 see {@link SD540} */
	SD540, 
	/** Spark see {@link Spark} */
	SPARK,
	/** Jaguar see {@link Jaguar} */
	JAGUAR,
	/** Talon see {@link Talon} */
	TALON,
	/** TalonSR see {@link Talon} */
	TALON_SR,
	/** TalonSRX see {@link PWMTalonSRX} 
	 * <p>
	 * NOTE: If using the TalonSRX attached to a CAN bus, 
	 * see {@link TCanSpeedController} */
	TALON_SRX,
	/** Victor 888 or 884 see {@link Victor} */
	VICTOR,
	/** VictorSP see {@link VictorSP} */
	VICTOR_SP,
	/** VictorSPX see {@link PWMVictorSPX}
	 * <p>
	 * NOTE: If using the VictorSPX attached to a CAN bus, 
	 * see {@link TCanSpeedController} */
	VICTOR_SPX
}
