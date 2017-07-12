package org.usfirst.frc.team1710.robot.commandGroups;

import org.usfirst.frc.team1710.robot.Commands.DriveToPosition;
import org.usfirst.frc.team1710.robot.Commands.EncoderDrive;
import org.usfirst.frc.team1710.robot.Commands.RotatetoAngle;
import org.usfirst.frc.team1710.robot.Commands.ZeroYaw;
import org.usfirst.frc.team1710.robot.Commands.followcurve;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearPlaceRight extends CommandGroup {
	//for red alliance, would be left for the blue alliance
    public GearPlaceRight() {
    	addSequential(new ZeroYaw());
    	//drives 90 inches forward holding a heading of 0 degrees (straight)
    	//at 80% speed, do change the direction just make the distance negative.
    	addSequential(new DriveToPosition(74, 0, .65));
    	addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle(-50));
    	addSequential(new ZeroYaw());
    	addSequential(new DriveToPosition(27, 0, .4));
    }
}