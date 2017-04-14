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
public class GearCenterShootRed extends CommandGroup {

    public GearCenterShootRed() {
    	addSequential(new ZeroYaw());
    	addSequential(new DriveToPosition(96, 0));
    	addSequential(new Delay(2.25));
    	addSequential(new ZeroYaw());
    	addSequential(new DriveToPosition(-30, 0));
    	addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle(-20));
    	addSequential(new RunShooterAuto(3000, 2));
    }
}
