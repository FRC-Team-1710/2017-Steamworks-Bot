package org.usfirst.frc.team1710.robot.commandGroups;

import org.usfirst.frc.team1710.robot.Commands.EncoderDrive;
import org.usfirst.frc.team1710.robot.Commands.RotatetoAngle;
import org.usfirst.frc.team1710.robot.Commands.RunShooterAuto;
import org.usfirst.frc.team1710.robot.Commands.ZeroYaw;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearCenterShoot extends CommandGroup {

    public GearCenterShoot() {
    	//blue alliance
    	addSequential(new ZeroYaw());
    	addSequential(new EncoderDrive(86, .66, false));
    	addSequential(new Delay(2.25));
    	addSequential(new ZeroYaw());
    	addSequential(new EncoderDrive(60, -.75, false));
    	addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle(100));
    	addSequential(new ZeroYaw());
    	addSequential(new EncoderDrive(58, -0.69, false));
    	addSequential(new RunShooterAuto(2500));
    }
}
