package org.usfirst.frc.team1710.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class EncoderDrive extends Command {
	double goalVelocityPublic, currentVelocity, distance, angle, error, power, inc, percentageDone;
	boolean done, hiRotationAdded, loRotationAdded;
	
	double rotations, rotateToPublic;
	
	int count;
	long startTime, endTime, timeElapsed, currentEncoder, startEncoder, rotationVal;
	/*@param the amount of rotations you want the robot to do
	@param the starting velocity of the robot*/
    public EncoderDrive(double rotateTo, double goalVelocity) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	goalVelocityPublic = goalVelocity;
    	rotateToPublic = rotateTo;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println(startEncoder);
    	hiRotationAdded = false;
    	loRotationAdded = false;
    	startTime = System.nanoTime() / 1000000000;
    	inc = 0.3;
    	count = 0;
    	power = 0.8;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println(rotations);
    	percentageDone = (rotations/rotateToPublic);
    	angle = (RobotMap.REncoder.getVoltage() * 360/5);
    	if(rotations < rotateToPublic){
    		if(angle > 179 && hiRotationAdded == false) {
        		rotations += 0.5;
        		hiRotationAdded = true;
        		loRotationAdded = false;
        		endTime = System.nanoTime()/1000000000;
        	} else if(angle <= 179 && angle >= 0 && loRotationAdded == false) {
        		rotations += 0.5;
        		loRotationAdded = true;
        		hiRotationAdded = false;
        		endTime = System.nanoTime()/1000000000;
        	}
    		if (percentageDone >= .8){
    			Drive.simpleArcade(-1.8 + percentageDone, 0, 1);
    		}
    		else {
    			Timer.delay(.5);
    			Drive.simpleArcade(-.75, 0, 1);
    		}
    		
    		timeElapsed = endTime - startTime;
        	distance = rotations * Math.PI * 4;
    		currentVelocity = distance / timeElapsed;
    	}else{
    		Drive.simpleArcade(0, 0, 0);
    		done = true;
    	}
    	System.out.println(currentVelocity);
    	
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
