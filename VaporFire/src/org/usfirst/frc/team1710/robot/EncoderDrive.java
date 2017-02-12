package org.usfirst.frc.team1710.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class EncoderDrive extends Command {
	double goalVelocityPublic, currentVelocity, distance, angle, error, power, inc;
	boolean done, hiRotationAdded, loRotationAdded;
	
	double rotations, rotateToPublic;
	
	double[] motionProfile = {90,90,90,90,90,90,90,90,90,90,90,60,60,60,60,30,30,30,30,30,30,30,30};
	int count;
	long startTime, endTime, timeElapsed, currentEncoder, startEncoder, rotationVal;
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
    	angle = (RobotMap.REncoder.getVoltage() * 360/5);
    	if(rotations < rotateToPublic){
    		Drive.simpleArcade(power, 0, 1);
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
    		timeElapsed = endTime - startTime;
        	distance = rotations * Math.PI * 4;
    		currentVelocity = distance / timeElapsed;
    		System.out.println(currentVelocity);
    		goalVelocityPublic = motionProfile[count];
    		if(count < motionProfile.length - 1) {
    			count ++;
    		}
        	error = ((goalVelocityPublic - currentVelocity) / (goalVelocityPublic + currentVelocity));
        	power -= (inc * error);
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
