package com.torontocodingcollective.commands;

import com.torontocodingcollective.oi.TOi;

import edu.wpi.first.wpilibj.command.Command;

/**
 * TSafeCommand
 * <p>
 * This command is used as a base command for all
 * other commands and supports the features:
 * <br> - end the command after a given timeout
 * <br> - end the command when cancelled by the user
 */
public class TSafeCommand extends Command {

	private final double maxTimeSec;
	private final TOi    oi;
	
	/**
	 * TSafeCommand
	 * <p>
	 * Construct a safe command with unlimited timeout
	 * @param oi the TOi object that defines the 
	 * cancel operation {@link TOi#getCancelCommand()}
	 */
	public TSafeCommand(TOi oi) {
		this(-1, oi);
	}
	
	/**
	 * TSafeCommand
	 * <p>
	 * Construct a safe command with unlimited timeout
	 * @param maxTimeSec the time after which this command
	 * will end automatically a value <= 0 will
	 * be used as an infinite timeout
	 * @param oi the TOi object that defines the 
	 * cancel operation {@link TOi#getCancelCommand()}
	 */
	public TSafeCommand(double maxTimeSec, TOi oi) {
		this.maxTimeSec = maxTimeSec;
		this.oi = oi;
	}
	
	@Override
    protected boolean isFinished() {
    	if (isCancelled() || isTimedOut()) {
    		return true;
    	}
        return false;
    }

	/**
	 * Is Command Cancelled
	 * <p>
	 * Returns {@code true} if the user cancels the 
	 * command using operator controls see {@link TOi#getCancelCommand()}
	 * @return {@code true} if the command is to be cancelled,
	 * {@code false} otherwise.
	 */
    public boolean isCancelled() {
    	if (oi.getCancelCommand()) {
    		return true;
    	}
    	return false;
    }
    
    @Override
    public boolean isTimedOut() {
    	// max time of zero is an infinite timeout
    	if (   maxTimeSec > 0
    		&& timeSinceInitialized() >= maxTimeSec) {
    		return true;
    	}
    	return false;
    }
}
