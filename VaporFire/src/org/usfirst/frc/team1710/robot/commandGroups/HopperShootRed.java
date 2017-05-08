package org.usfirst.frc.team1710.robot.commandGroups;

import org.usfirst.frc.team1710.robot.RobotMap;
import org.usfirst.frc.team1710.robot.Shooter;
import org.usfirst.frc.team1710.robot.Commands.DriveToPosition;
import org.usfirst.frc.team1710.robot.Commands.EncoderDrive;
import org.usfirst.frc.team1710.robot.Commands.RotateToAngleButWithEncoders;
import org.usfirst.frc.team1710.robot.Commands.RotatetoAngle;
import org.usfirst.frc.team1710.robot.Commands.RunShooterAuto;
import org.usfirst.frc.team1710.robot.Commands.ShiftLow;
import org.usfirst.frc.team1710.robot.Commands.ZeroYaw;
import org.usfirst.frc.team1710.robot.Commands.followcurve;

import trajectoryGeneration.FollowPath;
import trajectoryGeneration.Waypoints;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 
 */
public class HopperShootRed extends CommandGroup {

    public HopperShootRed() {
    	addSequential(new ZeroYaw());
    	addSequential(new followcurve(.003, -40));
    	addSequential(new ZeroYaw());
    	addSequential(new DriveToPosition(-20, 0, 0.4));
    	addSequential(new ZeroYaw());
    	addSequential(new RotateToAngleButWithEncoders(true, 2500, -0.5));
    	addSequential(new RotateToAngleButWithEncoders(true, 2500, 0.5));
    	addSequential(new RunShooterAuto(7500, 4));
    }
    
    protected void interrupted() {
    	Scheduler.getInstance().removeAll();
    	RobotMap.navx.zeroYaw();
    	System.out.println("wow :/");
    }
}
