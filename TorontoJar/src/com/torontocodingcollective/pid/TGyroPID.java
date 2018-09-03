package com.torontocodingcollective.pid;

import edu.wpi.first.wpilibj.PIDController;

/**
 * Proportional (PID) Control Loop for heading (gyro angle) control.
 * <p>
 * The PID controller calculate routine must be called at a consistent rate
 * (every control loop is good enough) when the PID is enabled.
 * <p>
 * This PID controller uses the Smartdashboard communications features of the 
 * wpiLib PID controller, but does not use its control loop functionality.
 */
public class TGyroPID extends PIDController {

	private double output;
	private double totalError;
	
	/**
	 * Construct a TGyroPid using the supplied proportional gain 
	 */
	public TGyroPID(double kP) {
		// The super class is used in order to 
		// support a SmartDashboard pid object
		super(kP, 0.0d, 0.0d, 0.0d, 
				new NullPIDSource(),
				new NullPIDOutput()	);
		this.totalError     = 0;
	}

	/**
	 * Construct a TGyroPid using the supplied proportional and integral gain 
	 */
	public TGyroPID(double kP, double kI) {
		super(kP, kI, 0.0d, 0.0d, 
				new NullPIDSource(),
				new NullPIDOutput()	);
		this.totalError     = 0;
	}

	/**
	 * Calculate the PID output.
	 * <p>
	 * In order to generate proper PID behaviour, this routine
	 * must be called at a consistent periodic rate.  Calling
	 * this routine anywhere in the main robot periodic loops is 
	 * sufficient.
	 * <p>
	 * NOTE: If the PID is disabled, this routine returns 0.
	 * @param rawRate the raw encoder rate used as feedback for this PID
	 * @return the calculated result. This result can also be 
	 * retrieved with subsequent calls to {@link #get()}.
	 */
	public double calculate(double currentGyroAngle) {
		
		// If the PID is not enabled, this routine does nothing.
		if (!this.isEnabled()) {
			return 0;
		}

		// Normalize the gyro angle.
		// Current gyro angle is -infinity to +infinity
		double normalizedGyroAngle = currentGyroAngle % 360.0d;
		
		if (normalizedGyroAngle < 0) {
			normalizedGyroAngle = normalizedGyroAngle + 360.0;
		}
		
		// Calculate the error
		// Normalize the error for the shortest path.  The error
		// can be between -360 and +360.
		double error = super.getSetpoint() - normalizedGyroAngle;
		
		if (error > 180) {
			error = error - 360.0;
		}
		
		if (error < -180) {
			error = error + 360.0;
		}
		
		// Add the proportional output
		double proportionalOutput = super.getP() * error;

		// The output cannot steer more than 1.0
		if (proportionalOutput > 1.0) {
			proportionalOutput = 1.0;
		}
		
		// The output cannot steer less than -1.0
		if (proportionalOutput < -1.0) {
			proportionalOutput = -1.0;
		}
		
		double totalOutput = proportionalOutput;
		
		// Calculate the integral contribution. 
		// Integral controllers are very prone to saturation
		// and "wind-up".  In order to avoid "wind-up", 
		// do not allow the integral error total to exceed
		// the total required to saturate the output (-1.0 or 1.0).
		double kI = super.getI();
		
		if (kI != 0) {
			
			totalError += error;  // sum of all errors
			
			double integralOutput = totalError * kI;
			
			// Clamp the total error to prevent "windup"
			if (integralOutput + proportionalOutput > 1.0) {
				totalError = (1.0 - proportionalOutput) / kI;
			}
			
			if (integralOutput + proportionalOutput < -1.0) {
				totalError = (-1.0 - proportionalOutput) / kI;
			}
			
			// Recalculate the integral output after clamping
			integralOutput = totalError * kI;
			
			totalOutput = proportionalOutput + integralOutput;
		}
		
		output = totalOutput;
		
		return output;
	}
	
	@Override
	public void disable() {
		super.disable();
		totalError = 0;
		output = 0;
	}
	
	@Override
	public double get() { 
		return output;
	}
}
