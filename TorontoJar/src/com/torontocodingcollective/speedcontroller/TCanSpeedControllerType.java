package com.torontocodingcollective.speedcontroller;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public enum TCanSpeedControllerType {

	/** Talon SRX see {@link TalonSRX} */
	TALON_SRX,
	/** Victor SPX see {@link VictorSPX} */
	VICTOR_SPX
}
