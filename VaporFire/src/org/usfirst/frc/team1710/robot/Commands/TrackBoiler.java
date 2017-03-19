package org.usfirst.frc.team1710.robot.Commands;

import org.usfirst.frc.team1710.robot.BetterVision;
import org.usfirst.frc.team1710.robot.Drive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class TrackBoiler extends Command {

	int timePublic, count;
	boolean done;
	
	static NetworkTable table;
	
    public TrackBoiler(int time) {
    	timePublic = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(timePublic < count/20) {
        	BetterVision.trackBoiler();
        	count ++;
        } else {
        	done = true;
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
