package org.usfirst.frc.team1710.robot.commandGroups;

import org.usfirst.frc.team1710.robot.Commands.EncoderDrive;
import org.usfirst.frc.team1710.robot.Commands.RotatetoAngle;
import org.usfirst.frc.team1710.robot.Commands.RunShooterAuto;
import org.usfirst.frc.team1710.robot.Commands.ZeroYaw;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearPlaceRightShoot extends CommandGroup {

    public GearPlaceRightShoot() {
    	addSequential(new ZeroYaw());
    	addSequential(new EncoderDrive(70, .75, true));
    	addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle(-77));
    	addSequential(new ZeroYaw());
    	addSequential(new EncoderDrive(48, .4, true));
    	addSequential(new Delay(2));
    	addSequential(new ZeroYaw());
    	addSequential(new EncoderDrive(23.5, -.5, false));
    	addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle(12));
    	addSequential(new RunShooterAuto(5000));
    }
}
