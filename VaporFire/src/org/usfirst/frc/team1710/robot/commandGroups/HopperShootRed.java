package org.usfirst.frc.team1710.robot.commandGroups;

import org.usfirst.frc.team1710.robot.RobotMap;
import org.usfirst.frc.team1710.robot.Shooter;
import org.usfirst.frc.team1710.robot.Commands.EncoderDrive;
import org.usfirst.frc.team1710.robot.Commands.RotatetoAngle;
import org.usfirst.frc.team1710.robot.Commands.RunShooterAuto;
import org.usfirst.frc.team1710.robot.Commands.ShiftLow;
import org.usfirst.frc.team1710.robot.Commands.ZeroYaw;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class HopperShootRed extends CommandGroup {

    public HopperShootRed() {
    	//start of actual auto from alliance wall
    	/*addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle(-5));*/
    	//was 46

    	
    	//end showcase thing
    	addSequential(new ZeroYaw());
    	addSequential(new EncoderDrive(2.5, .6, false));
    	addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle((float) 19));
    	addSequential(new ZeroYaw());
    	addSequential(new EncoderDrive(110, 0.7, false));
    	addSequential(new ZeroYaw());
    	addSequential(new RotatetoAngle((float) -30));
    	addSequential(new ZeroYaw());
    	addSequential(new EncoderDrive(20, -.69, false));
    	addSequential(new RunShooterAuto(7000));

    }
    
    protected void interrupted() {
    	Scheduler.getInstance().removeAll();
    	RobotMap.navx.zeroYaw();
    	System.out.println("wow :/");
    }
}
