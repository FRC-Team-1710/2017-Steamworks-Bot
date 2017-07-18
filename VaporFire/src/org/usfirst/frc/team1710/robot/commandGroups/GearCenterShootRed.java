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
    	addSequential(new DriveToPosition(72, 0, 0.6));
    	addSequential(new Delay(2));
    	addSequential(new ZeroYaw());
    	addSequential(new DriveToPosition(-5, 0, 0.75));
    	addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle(-75));
    	addSequential(new ZeroYaw());
    	addSequential(new DriveToPosition(-35, 0, 0.75));
    	addSequential(new RunShooterAuto(5500, 2));
    }
}
