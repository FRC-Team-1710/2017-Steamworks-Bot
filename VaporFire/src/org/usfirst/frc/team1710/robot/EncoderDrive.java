package org.usfirst.frc.team1710.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class EncoderDrive extends Command {
	int encoderAtRest, currentEncoder, rotations, rotateToPublic;
	double speedPublic, currentVelocity, distance;
	boolean done;
	
	long startTime, endTime, timeElapsed;
    public EncoderDrive(int rotateTo, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	speedPublic = speed;
    	rotateToPublic = rotateTo;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	encoderAtRest = RobotMap.REncoder.getValue();
		startTime = System.nanoTime() / 1000000000;
		endTime = 1;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentEncoder = RobotMap.REncoder.getValue();
       	timeElapsed = endTime - startTime;
    	currentVelocity = (rotations * 4 * (Math.PI))/timeElapsed;
    	System.out.println(distance);
    	if(rotations < rotateToPublic){
        	if(currentEncoder > encoderAtRest - 100 && currentEncoder < encoderAtRest + 100){
        		rotations ++;
        		System.out.println("rotated");
        		endTime = System.nanoTime() / 1000000000;
        		Drive.StegDrive(-speedPublic, RobotMap.navx.getYaw(), 1);
        		distance = (rotations * 4 * Math.PI);
        		Timer.delay(0.005);
        	} else {
        		Drive.StegDrive(-speedPublic, RobotMap.navx.getYaw(), 1);
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
