package org.usfirst.frc.team1710.robot.commandGroups;

import org.usfirst.frc.team1710.robot.Commands.EncoderDrive;
import org.usfirst.frc.team1710.robot.Commands.RotatetoAngle;
import org.usfirst.frc.team1710.robot.Commands.ZeroYaw;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearPlaceRight extends CommandGroup {
	//for red alliance, would be left for the blue alliance
    public GearPlaceRight() {
    	addSequential(new ZeroYaw());
    	addSequential(new EncoderDrive(80, 0.5, false));
    	addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle(30));
    	addSequential(new ZeroYaw());
    	addSequential(new EncoderDrive(32, .4, false));
    }
}