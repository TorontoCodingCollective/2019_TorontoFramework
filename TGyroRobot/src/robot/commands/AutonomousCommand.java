package robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import robot.oi.AutoSelector;

/**
 *
 */
public class AutonomousCommand extends CommandGroup {

	public static final char LEFT 				= 'L';
	public static final char RIGHT 				= 'R';
	public static final char CENTER 			= 'C';

	public AutonomousCommand() {
		//getting info
		String robotStartPosition 	= AutoSelector.getRobotStartPosition();


		//		 Print out the user selection and Game config for debug later
		System.out.println("Auto Command Configuration");
		System.out.println("--------------------------");
		System.out.println("Robot Position : " + robotStartPosition);

	}

}
