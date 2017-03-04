package org.usfirst.frc.team1710.robot.commandGroups;

import org.usfirst.frc.team1710.robot.RobotMap;
import org.usfirst.frc.team1710.robot.Shooter;
import org.usfirst.frc.team1710.robot.Commands.EncoderDrive;
import org.usfirst.frc.team1710.robot.Commands.RotatetoAngle;
import org.usfirst.frc.team1710.robot.Commands.RunShooterAuto;
import org.usfirst.frc.team1710.robot.Commands.TrackBoiler;
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
    	/*addSequential(new ZeroYaw());
    	addSequential(new EncoderDrive(2.5, .4, false));
    	addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle((float) -9.25));
    	addSequential(new ZeroYaw());
    	addSequential(new EncoderDrive(29.5, 1, true));
    	addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle(24));
    	addSequential(new EncoderDrive(5.5, -.5, false));*/
    	addSequential(new RunShooterAuto(10000));

    }
    
    protected void interrupted() {
    	Scheduler.getInstance().removeAll();
    	RobotMap.navx.zeroYaw();
    	System.out.println("wow :/");
    }
}
