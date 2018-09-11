package com.torontocodingcollective.commands;

import com.torontocodingcollective.oi.TOi;

import edu.wpi.first.wpilibj.command.Command;

public class TSafeCommand extends Command {

	private final double maxTimeSec;
	private final TOi    oi;
	
	public TSafeCommand(TOi oi) {
		this.maxTimeSec = 0;
		this.oi = oi;
	}
	
	public TSafeCommand(double maxTimeSec, TOi oi) {
		this.maxTimeSec = maxTimeSec;
		this.oi = oi;
	}
	
    protected boolean isFinished() {
    	if (isCancelled() || isTimedOut()) {
    		return true;
    	}
        return false;
    }

    public boolean isCancelled() {
    	if (oi.getCancelCommand()) {
    		return true;
    	}
    	return false;
    }
    
    public boolean isTimedOut() {
    	// max time of zero is an infinite timeout
    	if (   maxTimeSec > 0
    		&& timeSinceInitialized() >= maxTimeSec) {
    		return true;
    	}
    	return false;
    }
}
