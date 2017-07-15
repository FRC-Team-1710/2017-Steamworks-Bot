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
    	addSequential(new DriveToPosition(80, 0, .75));
    	addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle(-63));
    	addSequential(new ZeroYaw());
    	addSequential(new DriveToPosition(22, 0, .35));
    }
}