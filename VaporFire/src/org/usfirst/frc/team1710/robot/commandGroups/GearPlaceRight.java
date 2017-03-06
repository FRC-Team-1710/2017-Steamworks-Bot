package org.usfirst.frc.team1710.robot.commandGroups;

import org.usfirst.frc.team1710.robot.Commands.Delay;
import org.usfirst.frc.team1710.robot.Commands.DriveForTime;
import org.usfirst.frc.team1710.robot.Commands.RotatetoAngle;
import org.usfirst.frc.team1710.robot.Commands.ZeroYaw;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearPlaceRight extends CommandGroup {
	//for red alliance, would be left for the blue alliance
    public GearPlaceRight() {
    	addSequential(new DriveForTime(1700, .4));
    	addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle(-75));
    	addSequential(new DriveForTime(3000, .4));
    	addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle(90));
    	addSequential(new DriveForTime(1500, .4));
    	addSequential(new Delay(2));
    	addSequential(new DriveForTime(1500, -.4));
    	addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle(90));
    	addSequential(new DriveForTime(1500, .4));
    }
}