package org.usfirst.frc.team1710.robot.commandGroups;

import org.usfirst.frc.team1710.robot.RobotMap;
import org.usfirst.frc.team1710.robot.Shooter;
import org.usfirst.frc.team1710.robot.Commands.DriveToPosition;
import org.usfirst.frc.team1710.robot.Commands.EncoderDrive;
import org.usfirst.frc.team1710.robot.Commands.MotionExperiment;
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
    	/*addSequential(new ZeroYaw());
    	addSequential(new DriveToPosition(20));
    	addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle((float) -45));
    	addSequential(new ZeroYaw());
    	addSequential(new DriveToPosition(25));
    	addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle((float) 43));
    	addSequential(new ZeroYaw());
    	addSequential(new DriveToPosition(38));
    	addSequential(new ZeroYaw());
    	addSequential(new RunShooterAuto(7500, 4));*/
    	addSequential(new ZeroYaw());
    	addSequential(new DriveToPosition(127));
    	addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle((float) 25));
    	addSequential(new DriveToPosition(-60));
    	addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle((float) -35));
    	addSequential(new ZeroYaw());
    	addSequential(new RunShooterAuto(7500, 4));

    	/*double[][] leftProfile = {
    			{12500, 0.6}, {15000, 0.6}, {5000, 0.75}
    	};
    	double[][] rightProfile = {
    			{15000, 0.6}, {12500, 0.6}, {5000, 0.75}
    	};
    	addSequential(new MotionExperiment(leftProfile, rightProfile));*/
    }
    
    protected void interrupted() {
    	Scheduler.getInstance().removeAll();
    	RobotMap.navx.zeroYaw();
    	System.out.println("wow :/");
    }
}
