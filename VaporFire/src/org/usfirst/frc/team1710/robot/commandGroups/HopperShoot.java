package org.usfirst.frc.team1710.robot.commandGroups;

import org.usfirst.frc.team1710.robot.RobotMap;
import org.usfirst.frc.team1710.robot.Shooter;
import org.usfirst.frc.team1710.robot.Commands.DriveToPosition;
import org.usfirst.frc.team1710.robot.Commands.EncoderDrive;
import org.usfirst.frc.team1710.robot.Commands.RotateToAngleButWithEncoders;
import org.usfirst.frc.team1710.robot.Commands.RotatetoAngle;
import org.usfirst.frc.team1710.robot.Commands.RunShooterAuto;
import org.usfirst.frc.team1710.robot.Commands.ZeroYaw;
import org.usfirst.frc.team1710.robot.Commands.followcurve;

import trajectoryGeneration.FollowPath;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class HopperShoot extends CommandGroup {

    public HopperShoot() {
    	addSequential(new ZeroYaw());
    	addSequential(new followcurve(-.0015, 40));
    	addSequential(new ZeroYaw());
    	addSequential(new DriveToPosition(20, 0, 0.45));
    	addSequential(new ZeroYaw());
    	addSequential(new RotateToAngleButWithEncoders(true, 2500, 0.5));
    	addSequential(new RotateToAngleButWithEncoders(true, 2500, -0.5));
    	addSequential(new RunShooterAuto(7500, 4));
    }
    
    protected void interrupted() {
    	Scheduler.getInstance().removeAll();
    	RobotMap.navx.zeroYaw();
    	System.out.println("wow :/");
    }
}
