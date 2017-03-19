package org.usfirst.frc.team1710.robot.commandGroups;

import org.usfirst.frc.team1710.robot.Commands.EncoderDrive;
import org.usfirst.frc.team1710.robot.Commands.RotatetoAngle;
import org.usfirst.frc.team1710.robot.Commands.RunShooterAuto;
import org.usfirst.frc.team1710.robot.Commands.ZeroYaw;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearCenterShootRed extends CommandGroup {

    public GearCenterShootRed() {
    	addSequential(new ZeroYaw());
    	addSequential(new EncoderDrive(86, .68, false));
    	addSequential(new Delay(2.25));
    	addSequential(new ZeroYaw());
    	addSequential(new EncoderDrive(60, -.75, false));
    	addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle((float) -104));
    	addSequential(new ZeroYaw());
    	addSequential(new EncoderDrive(58, -0.69, false));
    	addSequential(new RunShooterAuto(2500));
    }
}
