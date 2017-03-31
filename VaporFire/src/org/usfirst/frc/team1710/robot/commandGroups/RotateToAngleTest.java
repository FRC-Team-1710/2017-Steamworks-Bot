package org.usfirst.frc.team1710.robot.commandGroups;

import org.usfirst.frc.team1710.robot.Commands.RotatetoAngle;
import org.usfirst.frc.team1710.robot.Commands.ZeroYaw;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RotateToAngleTest extends CommandGroup {

    public RotateToAngleTest() {
    	addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle(45));
    	
    	addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle(45));
    }
}
