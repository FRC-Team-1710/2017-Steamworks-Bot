package org.usfirst.frc.team1710.robot.Commands;

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
	public static double[] centerX;
	public static double targetX;
	
    public TrackBoiler(int time) {
    	timePublic = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(timePublic/20 > count) {
        	table = NetworkTable.getTable("GRIP/BoilerReport");
        	centerX = table.getNumberArray("centerX");
        	System.out.println(centerX.length);
        	if(centerX.length > 0) {
        		System.out.println("I see it");
        		targetX = centerX[0];
        		if(targetX > 320) {
        			Drive.simpleArcade(0, -0.2, 1);
            		System.out.println("turning right");
        		} else if(targetX < 290){
        			Drive.simpleArcade(0, 0.2, 1);
            		System.out.println("turning left");
        		} else {
        			Drive.simpleArcade(0, 0.0, 1);
            		System.out.println("we square");
        		}
        	} else {
        		Drive.simpleArcade(0, 0.2, 1);
        		System.out.println("I don't see anything");
        	}
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
