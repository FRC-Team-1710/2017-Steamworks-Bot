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
    	addSequential(new DriveToPosition(86, 0, 0.45));
    	addSequential(new Delay(2));
    	addSequential(new ZeroYaw());
    	addSequential(new DriveToPosition(-10, 0, 0.75));
    	addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle(-80));
    	addSequential(new ZeroYaw());
    	addSequential(new DriveToPosition(-45, 0, 0.75));
    	addSequential(new RunShooterAuto(4000, 2));
    }
}
