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
public class GearPlaceLeftShoot extends CommandGroup {

    public GearPlaceLeftShoot() {
    	addSequential(new ZeroYaw());
    	//drives 90 inches forward holding a heading of 0 degrees (straight)
    	//at 80% speed, do change the direction just make the distance negative.
    	addSequential(new DriveToPosition(78, 0, .6));
    	addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle(55));
    	addSequential(new ZeroYaw());
    	addSequential(new DriveToPosition(18, 0, .35));
    	//wait for pilot
    	addSequential(new Delay(1.6));
    	addSequential(new ZeroYaw());
    	addSequential(new DriveToPosition(-42, -10, .4));
    	addSequential(new RunShooterAuto(4000, 2));
    }
}
