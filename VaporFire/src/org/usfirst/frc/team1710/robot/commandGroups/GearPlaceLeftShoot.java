package org.usfirst.frc.team1710.robot.commandGroups;

import org.usfirst.frc.team1710.robot.Commands.DriveToPosition;
import org.usfirst.frc.team1710.robot.Commands.EncoderDrive;
import org.usfirst.frc.team1710.robot.Commands.RotatetoAngle;
import org.usfirst.frc.team1710.robot.Commands.RunShooterAuto;
import org.usfirst.frc.team1710.robot.Commands.ZeroYaw;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearPlaceLeftShoot extends CommandGroup {

    public GearPlaceLeftShoot() {
    	addSequential(new ZeroYaw());
    	addSequential(new DriveToPosition(96, 0));
    	addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle(70));
    	addSequential(new ZeroYaw());
    	addSequential(new EncoderDrive(36, .4, true));
    	addSequential(new Delay(2));
    	addSequential(new ZeroYaw());
    	addSequential(new EncoderDrive(35, -.5, false));
    	addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle(-10));
    	addSequential(new RunShooterAuto(10000, 3));
    }
}
