package org.usfirst.frc.team1710.robot.Commands;

import org.usfirst.frc.team1710.robot.BetterVision;
import org.usfirst.frc.team1710.robot.Processing;
import org.usfirst.frc.team1710.robot.RobotMap;
import org.usfirst.frc.team1710.robot.Shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunShooterAuto extends Command {
	int timePublic, count;
	boolean done;
    public RunShooterAuto(int time) {
		timePublic = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
   		BetterVision.trackBoiler();
   		RobotMap.RM1.set(RobotMap.RPower);
    	RobotMap.RM2.set(RobotMap.RPower);
    	RobotMap.RM3.set(RobotMap.RPower);
    	RobotMap.LM1.set(RobotMap.LPower * -1);
    	RobotMap.LM2.set(RobotMap.LPower * -1);
    	RobotMap.LM3.set(RobotMap.LPower * -1);
    	Shooter.BestShooter();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
