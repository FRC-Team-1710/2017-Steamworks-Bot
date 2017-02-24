package org.usfirst.frc.team1710.robot.commandGroups;

import org.usfirst.frc.team1710.robot.RobotMap;
import org.usfirst.frc.team1710.robot.Shooter;
import org.usfirst.frc.team1710.robot.Commands.EncoderDrive;
import org.usfirst.frc.team1710.robot.Commands.RotatetoAngle;
import org.usfirst.frc.team1710.robot.Commands.RunShooterAuto;
import org.usfirst.frc.team1710.robot.Commands.ZeroYaw;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class HopperShoot extends CommandGroup {

    public HopperShoot() {		
    	addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle(-39));
    	//was 46
    	addSequential(new EncoderDrive(47, .8));
    	addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle(34));
    	addSequential(new EncoderDrive(7, -.4));
    	addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle(7));
    	addSequential(new RunShooterAuto(10000));
    	//TODO: this is where the piston will activate
    	//addSequential(new AutoHighGoalTrack());
    	//TODO: spin up shooter, then run indexer once shooter is at the right speed
    	//addParallel() allows you to run multiple commands at once
    }
    
    protected void interrupted() {
    	Scheduler.getInstance().removeAll();
    	RobotMap.navx.zeroYaw();
    	System.out.println("wow :/");
    }
}
