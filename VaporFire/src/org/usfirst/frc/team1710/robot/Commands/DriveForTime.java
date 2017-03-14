package org.usfirst.frc.team1710.robot.Commands;

import org.usfirst.frc.team1710.robot.Drive;

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
    		Drive.simpleArcade(speedPublic, 0, 1);
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
