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
    	//drives 90 inches forward holding a heading of 0 degrees (straight)
    	//at 80% speed, do change the direction just make the distance negative.
    	addSequential(new DriveToPosition(81, 0, .65));
    	addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle(-63));
    	addSequential(new ZeroYaw());
    	addSequential(new DriveToPosition(23, 0, .4));
    	//wait for pilot
    	addSequential(new Delay(1.6));
    	addSequential(new ZeroYaw());
    	addSequential(new DriveToPosition(-42, 10, .4));
    	addSequential(new RunShooterAuto(4000, 2));
    }
}
