package org.usfirst.frc.team1710.robot.Commands;

import org.usfirst.frc.team1710.robot.Drive;
import org.usfirst.frc.team1710.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class followcurve extends Command {
public static double _forwardP;
public static double _inc;
public static float _goalAngle;
public static boolean done;
    public followcurve(double inc, float goalAngle) {
    	_forwardP = 0.5;
    	_inc = inc;
    	_goalAngle = goalAngle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	done = false;
    	RobotMap.RM2.setEncPosition(0);
    	RobotMap.LM3.setEncPosition(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Math.abs(_goalAngle) > Math.abs(RobotMap.navx.getYaw())){
    		Drive.curve(-_forwardP, _inc, _goalAngle);
    	}
    	else{
    		done = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
		RobotMap.LM1.set(0);
		RobotMap.LM2.set(0);
		RobotMap.LM3.set(0);
		RobotMap.RM1.set(0);
		RobotMap.RM2.set(0);
		RobotMap.RM3.set(0);
    	RobotMap.RM2.setEncPosition(0);
    	done = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
