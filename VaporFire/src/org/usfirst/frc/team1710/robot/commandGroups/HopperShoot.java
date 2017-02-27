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
    	//start of actual auto from alliance wall
    	/*addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle(-5));*/
    	//was 46

    	
    	//end showcase thing
    	addSequential(new ZeroYaw());
    	addSequential(new EncoderDrive(3, .5, false));
    	addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle(-6));
    	addSequential(new EncoderDrive(35, .5, false));
    	addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle(20));
    	//addSequential(new EncoderDrive(15, -.5, false));
    	//addSequential(new RunShooterAuto(10000));
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
