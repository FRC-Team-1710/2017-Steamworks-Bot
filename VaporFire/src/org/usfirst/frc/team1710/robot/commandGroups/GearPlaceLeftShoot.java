package org.usfirst.frc.team1710.robot.commandGroups;

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
    	addSequential(new EncoderDrive(80, .4, false));
    	addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle(-30));
    	addSequential(new ZeroYaw());
    	addSequential(new EncoderDrive(30, .4, false));
    	addSequential(new RunShooterAuto(10000));
    }
}
