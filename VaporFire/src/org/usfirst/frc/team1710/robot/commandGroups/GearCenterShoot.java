package org.usfirst.frc.team1710.robot.commandGroups;

import org.usfirst.frc.team1710.robot.Commands.DriveToPosition;
import org.usfirst.frc.team1710.robot.Commands.EncoderDrive;
import org.usfirst.frc.team1710.robot.Commands.RotatetoAngle;
import org.usfirst.frc.team1710.robot.Commands.RunShooterAuto;
import org.usfirst.frc.team1710.robot.Commands.TrackHigh;
import org.usfirst.frc.team1710.robot.Commands.ZeroYaw;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearCenterShoot extends CommandGroup {

    public GearCenterShoot() {
    	//blue alliance
    	addSequential(new ZeroYaw());
    	addSequential(new DriveToPosition(98));
    	addSequential(new Delay(2.2));
    	addSequential(new ZeroYaw());
    	addSequential(new DriveToPosition(-20));
    	addSequential(new RunShooterAuto(4000, 3));
     }
}
