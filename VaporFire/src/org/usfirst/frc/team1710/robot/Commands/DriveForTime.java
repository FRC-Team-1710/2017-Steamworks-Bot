package org.usfirst.frc.team1710.robot.Commands;

import org.usfirst.frc.team1710.robot.Drive;
import org.usfirst.frc.team1710.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForTime extends Command {
	int timePublic, count;
	double speedPublic;
	boolean done;
    public DriveForTime(double speed, int time) {
    	timePublic = time;
    	speedPublic = speed;
    }

    protected void initialize() {
    }

    protected void execute() {
    	if(timePublic/20 > count) {
    		Drive.straightDrive(speedPublic, 1, 0);
        	RobotMap.RM1.set(RobotMap.RPower*-1);
        	RobotMap.RM2.set(RobotMap.RPower*-1);
        	RobotMap.RM3.set(RobotMap.RPower*-1);
        	RobotMap.LM1.set(RobotMap.LPower);
        	RobotMap.LM2.set(RobotMap.LPower);
        	RobotMap.LM3.set(RobotMap.LPower);
    		count++;
    	} else {
    		done = true;
    	}
    }

    protected boolean isFinished() {
        return done;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
