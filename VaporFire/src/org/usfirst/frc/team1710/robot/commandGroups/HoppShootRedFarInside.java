package org.usfirst.frc.team1710.robot.commandGroups;

import org.usfirst.frc.team1710.robot.Commands.EncoderDrive;
import org.usfirst.frc.team1710.robot.Commands.RotatetoAngle;
import org.usfirst.frc.team1710.robot.Commands.RunShooterAuto;
import org.usfirst.frc.team1710.robot.Commands.ZeroYaw;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class HoppShootRedFarInside extends CommandGroup {

    public HoppShootRedFarInside() {
    	//start of actual auto from alliance wall
    	/*addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle(-5));*/
    	//was 46

    	
    	//end showcase thing
    	addSequential(new ZeroYaw());
    	addSequential(new EncoderDrive(2.5, .3, false));
    	addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle(18));
    	addSequential(new ZeroYaw());
    	addSequential(new EncoderDrive(41, .75, false));
    	addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle(-16));
    	addSequential(new EncoderDrive(6, .75, false));

    	/*addSequential(new EncoderDrive(5, -.5, false));
    	addSequential(new RunShooterAuto(10000));*/
    	//TODO: this is where the piston will activate
    	//addSequential(new AutoHighGoalTrack());
    	//TODO: spin up shooter, then run indexer once shooter is at the right speed
    	//addParallel() allows you to run multiple commands at once
    }
}
