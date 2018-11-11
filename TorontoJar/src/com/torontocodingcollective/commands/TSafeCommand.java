package com.torontocodingcollective.commands;

import com.torontocodingcollective.TConst;
import com.torontocodingcollective.TUtil;
import com.torontocodingcollective.oi.TOi;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 * TSafeCommand
 * <p>
 * This command is used as a base command for all other commands and supports
 * the features: <ls>
 * <li>end the command after a given timeout
 * <li>end the command when cancelled by the user </ls>
 */
public abstract class TSafeCommand extends Command {

    private final TOi oi;
    private final double timeout;

    /**
     * TSafeCommand
     * <p>
     * Construct a safe command with the default timeout
     * {@link TConst#DEFAULT_COMMAND_TIMEOUT }
     * 
     * @param oi
     *            the TOi object that defines the cancel operation
     *            {@link TOi#getCancelCommand()}
     */
    public TSafeCommand(TOi oi) {
        this(TConst.DEFAULT_COMMAND_TIMEOUT, oi);
    }

    /**
     * TSafeCommand
     * <p>
     * Construct a safe command with unlimited timeout
     * 
     * @param timeout
     *            the time after which this command will end automatically a value
     *            of {@link TConst#NO_COMMAND_TIMEOUT} will be used as an infinite
     *            timeout.
     * @param oi
     *            the TOi object that defines the cancel operation
     *            {@link TOi#getCancelCommand()}
     */
    public TSafeCommand(double timeout, TOi oi) {
        if (timeout >= 0) {
            super.setTimeout(timeout);
        }
        this.oi = oi;
        this.timeout = timeout;
    }

    /**
     * Get the command name associated with this command
     * @return
     */
    protected abstract String getCommandName();

    protected String getParmDesc() {
        if (timeout >= 0) {
            return "Timeout " + timeout;
        }
        else {
            return "No timeout";
        }
    }

    /**
     * Log a message generated by any command
     * <p>
     * The message will be marked with the current period
     * and the estimated time remaining in the period
     * @param message to log
     */
    protected void logMessage(String message) {

        DriverStation driverStation = DriverStation.getInstance();

        // Mark the message with the time and command name
        StringBuilder sb = new StringBuilder();
        if (driverStation.isAutonomous()) {
            sb.append("Auto: ");
        }
        else {
            sb.append("Teleop: ");
        }

        // Round the match time to one decimal
        double matchTime = TUtil.round(driverStation.getMatchTime(), 2);
        sb.append(matchTime).append(' ');

        sb.append(getCommandName()).append(" : ");

        sb.append(message);

        System.out.println(sb.toString());
    }

    @Override
    protected boolean isFinished() {

        if (isCancelled()) {
            logMessage("command cancelled by user after "
                    + TUtil.round(timeSinceInitialized(), 2) + "s");
            return true;
        }

        if (super.isTimedOut()) {
            logMessage("command timed out after "
                    + TUtil.round(timeSinceInitialized(), 2) + "s");
            return true;
        }

        return false;
    }

    @Override
    protected void interrupted() {
        logMessage("interrupted");
        super.interrupted();
    }

    /**
     * Is Command Cancelled
     * <p>
     * Returns {@code true} if the user cancels the command using operator controls
     * see {@link TOi#getCancelCommand()}
     * 
     * @return {@code true} if the command is to be cancelled, {@code false}
     *         otherwise.
     */
    public boolean isCancelled() {
        if (oi.getCancelCommand()) {
            return true;
        }
        return false;
    }
}
