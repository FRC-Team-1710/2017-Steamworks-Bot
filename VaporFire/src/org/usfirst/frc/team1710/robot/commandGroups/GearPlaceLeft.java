package org.usfirst.frc.team1710.robot.commandGroups;

import org.usfirst.frc.team1710.robot.Commands.EncoderDrive;
import org.usfirst.frc.team1710.robot.Commands.RotatetoAngle;
import org.usfirst.frc.team1710.robot.Commands.ZeroYaw;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearPlaceLeft extends CommandGroup {
	//for red alliance, would be right for the blue alliance
    public GearPlaceLeft() {
     	addSequential(new EncoderDrive(80, .4, false));
    	addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle(-30));
    	addSequential(new EncoderDrive(30, .4, false));
    }
}