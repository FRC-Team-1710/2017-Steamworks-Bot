package org.usfirst.frc.team1710.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class EncoderDrive extends Command {
	double speedPublic, currentVelocity, distance, angle;
	boolean done, hiRotationAdded, loRotationAdded;
	
	
	long startTime, endTime, timeElapsed, currentEncoder, startEncoder, rotationVal;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	speedPublic = speed;
    	rotateToPublic = rotateTo;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println(startEncoder);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	angle = (RobotMap.REncoder.getVoltage() * 360/5);
    	
    	if(rotations < rotateToPublic){
    		Drive.simpleArcade(1, 0, 1);
    		if(angle > 179 && angle < 0 && hiRotationAdded == false) {
        		rotations += 0.5;
        		hiRotationAdded = true;
        		loRotationAdded = false;
        	} else if(angle < 179 && angle > 0 && loRotationAdded == false) {
        		rotations += 0.5;
        		loRotationAdded = true;
        		hiRotationAdded = false;
        	}
        	
    	}else{
    		Drive.simpleArcade(0, 0, 0);
    		done = true;
    	}
    	RobotMap.RM1.set(RobotMap.RPower*-1);
    	RobotMap.RM2.set(RobotMap.RPower*-1);
    	RobotMap.RM3.set(RobotMap.RPower*-1);
    	RobotMap.LM1.set(RobotMap.LPower);
    	RobotMap.LM2.set(RobotMap.LPower);
    	RobotMap.LM3.set(RobotMap.LPower);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println(RobotMap.REncoder.getAccumulatorCount());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
