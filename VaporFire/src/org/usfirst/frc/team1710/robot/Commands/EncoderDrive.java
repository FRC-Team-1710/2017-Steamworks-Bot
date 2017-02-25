package org.usfirst.frc.team1710.robot.Commands;

import org.usfirst.frc.team1710.robot.Drive;
import org.usfirst.frc.team1710.robot.Pneumatics;
import org.usfirst.frc.team1710.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class EncoderDrive extends Command {
	double goalVelocityPublic, currentVelocity, distance, angle, anglePrevious, angleIncrease, error, power, inc, percentageDone;
	boolean done, hiRotationAdded, loRotationAdded, slowDownPublic;
	
	double rotations, rotateToPublic;
	
	int count;
	long startTime, endTime, timeElapsed, currentEncoder, startEncoder, rotationVal;
	/*@param the amount of rotations you want the robot to do
	@param the starting velocity of the robot*/
    public EncoderDrive(double rotateTo, double goalVelocity, boolean slowDown) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	goalVelocityPublic = goalVelocity;
    	rotateToPublic = rotateTo;
    	slowDownPublic = slowDown;
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
    	anglePrevious = 0;
    	Pneumatics.shiftForward();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//4pi * r = d 
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
    		if(slowDownPublic == true) {
    			Drive.simpleArcade(goalVelocityPublic, RobotMap.navx.getYaw()/50000, (1 - percentageDone) + 0.25);
    		} else {
    			Drive.simpleArcade(goalVelocityPublic, RobotMap.navx.getYaw()/50000, 1);
    		}
    	}else{
    		Drive.simpleArcade(0, 0, 0);
    		done = true;
    	}    	
    	RobotMap.pRM1.set(RobotMap.RPower*-1);
    	RobotMap.RM2.set(RobotMap.RPower*-1);
    	RobotMap.RM3.set(RobotMap.RPower*-1);
    	RobotMap.pLM1.set(RobotMap.LPower);
    	RobotMap.LM2.set(RobotMap.LPower);
    	RobotMap.LM3.set(RobotMap.LPower);
    	System.out.println(rotations);
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
