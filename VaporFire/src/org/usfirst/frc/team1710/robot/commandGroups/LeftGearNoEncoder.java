package org.usfirst.frc.team1710.robot.commandGroups;

import org.usfirst.frc.team1710.robot.Commands.DriveForTime;
import org.usfirst.frc.team1710.robot.Commands.RotatetoAngle;
import org.usfirst.frc.team1710.robot.Commands.ZeroYaw;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftGearNoEncoder extends CommandGroup {

    public LeftGearNoEncoder() {
    	addSequential(new ZeroYaw());
    	addSequential(new DriveForTime(.5, 1500));
    	addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle(35));
    	addSequential(new ZeroYaw());
    	addSequential(new DriveForTime(.5, 2000));
    }
}
