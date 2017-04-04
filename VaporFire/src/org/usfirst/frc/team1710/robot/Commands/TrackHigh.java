package org.usfirst.frc.team1710.robot.Commands;

import org.usfirst.frc.team1710.robot.BetterVision;
import org.usfirst.frc.team1710.robot.Drive;
import org.usfirst.frc.team1710.robot.RobotMap;
import org.usfirst.frc.team1710.robot.Shooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class TrackHigh extends Command {
	NetworkTable table;
	double[] centerX, centerY;
	double targetX, targetY;
	boolean done;
    public TrackHigh() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	table = NetworkTable.getTable("GRIP/BoilerReport");
    	centerX = table.getNumberArray("centerX");
    	centerY = table.getNumberArray("centerY");
    	System.out.println(centerX.length);
    	if(centerX.length > 0) {
    		targetX = centerX[0];
    		targetY = centerY[0];
    		if((targetY-230)/500 > .1 && (targetX-320)/650 > .11){
    			Drive.simpleArcade((targetY-230)/500, -(targetX-320)/650, 1);
    		}else{
    			done = true;
    		}
    	} else {
    		Drive.simpleArcade(0, 0.25, 1);
    	}
   		RobotMap.RM1.set(RobotMap.RPower);
    	RobotMap.RM2.set(RobotMap.RPower);
    	RobotMap.RM3.set(RobotMap.RPower);
    	RobotMap.LM1.set(RobotMap.LPower * -1);
    	RobotMap.LM2.set(RobotMap.LPower * -1);
    	RobotMap.LM3.set(RobotMap.LPower * -1);
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
