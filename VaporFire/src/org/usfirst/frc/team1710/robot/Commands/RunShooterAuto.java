package org.usfirst.frc.team1710.robot.Commands;

import org.usfirst.frc.team1710.robot.BetterShooter;
import org.usfirst.frc.team1710.robot.BetterVision;
import org.usfirst.frc.team1710.robot.RobotMap;
import org.usfirst.frc.team1710.robot.Shooter;

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
    	if(count < timePublic/20) {
    		BetterShooter.run();
    		BetterVision.trackBoiler();
    		
        	RobotMap.pRM1.set(RobotMap.RPower*-1);
        	RobotMap.RM2.set(RobotMap.RPower*-1);
        	RobotMap.RM3.set(RobotMap.RPower*-1);
        	RobotMap.pLM1.set(RobotMap.LPower);
        	RobotMap.LM2.set(RobotMap.LPower);
        	RobotMap.LM3.set(RobotMap.LPower);
    		
    	} else {
    		done = true;
    		
        	RobotMap.pRM1.set(RobotMap.RPower*0);
        	RobotMap.RM2.set(RobotMap.RPower*0);
        	RobotMap.RM3.set(RobotMap.RPower*0);
        	RobotMap.pLM1.set(RobotMap.LPower * 0);
        	RobotMap.LM2.set(RobotMap.LPower * 0);
        	RobotMap.LM3.set(RobotMap.LPower * 0);
    	}
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
