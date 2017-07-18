package org.usfirst.frc.team1710.robot.commandGroups;

import org.usfirst.frc.team1710.robot.Commands.DriveForTime;
import org.usfirst.frc.team1710.robot.Commands.DriveToPosition;
import org.usfirst.frc.team1710.robot.Commands.EncoderDrive;
import org.usfirst.frc.team1710.robot.Commands.RotatetoAngle;
import org.usfirst.frc.team1710.robot.Commands.ZeroYaw;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearPlaceCenter extends CommandGroup {
	//for red alliance, would be left for the blue alliance
    public GearPlaceCenter() {
    	addSequential(new ZeroYaw());
    	addSequential(new DriveToPosition(78, 0, 0.375));
    }
}