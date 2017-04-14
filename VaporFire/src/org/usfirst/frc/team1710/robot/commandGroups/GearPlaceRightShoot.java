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
public class GearPlaceRightShoot extends CommandGroup {

    public GearPlaceRightShoot() {
    	addSequential(new ZeroYaw());
    	addSequential(new DriveToPosition(107, 0));
    	addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle(-45));
    	addSequential(new ZeroYaw());
    	addSequential(new DriveToPosition(48, 0));
    	addSequential(new Delay(2));
    	addSequential(new ZeroYaw());
    	addSequential(new DriveToPosition(-48, 0));
    	addSequential(new ZeroYaw());
    	addSequential(new RunShooterAuto(5000, 2));
    }
}
