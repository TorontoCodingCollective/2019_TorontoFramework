package com.torontocodingcollective.sensors.gyro;

import edu.wpi.first.wpilibj.AnalogGyro;

public class TAnalogGyro extends TGyro {

	private final AnalogGyro analogGyro;
	private final int analogPort;
	
	public TAnalogGyro(int analogPort) {
		this(analogPort, false);
	}
	
	public TAnalogGyro(int analogPort, boolean isInverted) {
		super(isInverted);
		this.analogGyro = new AnalogGyro(analogPort);
		this.analogPort = analogPort;
		// Set the default sensitivity for a VEX yaw rate gyro
		// Vex documentation states .0011 V/deg/s
		this.analogGyro.setSensitivity(.00172);
	}
	
	@Override
	public void calibrate() {
		super.setGyroAngle(0);
		analogGyro.calibrate();
	}

	@Override
	public double getAngle() {
		return super.getAngle(analogGyro.getAngle());
	}

	@Override
	public double getRate() {
		return super.getRate(analogGyro.getRate());
	}

	/**
	 * Set the sensitivity of the analog gyro
	 * @param voltsPerDegreePerSecond sensitivity of the gyro
	 */
	public void setSensitivity(double voltsPerDegreePerSecond) {
		analogGyro.setSensitivity(voltsPerDegreePerSecond);
	}
	
	/**
	 * Get the current calibration values for the underlying
	 * analog gyro.
	 * @return String containing the channel, offset and center
	 * calibration values for the underlying analog gyro.
	 */
	public String getCalibrationValuesString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("Analog Port: ").append(analogPort);
		sb.append(", Offset: ").append(analogGyro.getOffset());
		sb.append(", Center: ").append(analogGyro.getCenter());
		
		return sb.toString();
	}

}
