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
    	addSequential(new EncoderDrive(75, .75, false));
    	addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle(-81));
    	addSequential(new ZeroYaw());
    	addSequential(new EncoderDrive(35, .4, true));
    	addSequential(new Delay(2));
    	addSequential(new ZeroYaw());
    	addSequential(new EncoderDrive(20, -.5, false));
    	addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle(10));
    	addSequential(new RunShooterAuto(10000));
    }
}
