package org.usfirst.frc.team1710.robot.Commands;

import org.usfirst.frc.team1710.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MotionExperiment extends Command {

	static double[][] _left;
	static double[][] _right;
	static int leftCount, rightCount, rightPosition, leftPosition;
	static double targetPositionRight, targetPositionLeft, leftPower, rightPower;
	boolean rightGood, leftGood, done;
	//@param left
	//@param right
    public MotionExperiment(double[][] leftProfile, double[][] rightProfile) {
    	_left = leftProfile;
    	_right = rightProfile;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.RM2.setEncPosition(0);
    	RobotMap.LM3.setEncPosition(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	rightPosition = Math.abs(RobotMap.RM2.getEncPosition());
    	leftPosition = Math.abs(RobotMap.LM3.getEncPosition());
    		//gets encoder position profiles
    		targetPositionRight = _right[rightCount][0];
    		targetPositionLeft = _left[leftCount][0];
    		//gets left & right power from profile
    		leftPower = _left[leftCount][1];
    		rightPower = _right[rightCount][1];
    		//moves right side until it reaches correct position
        	if(rightPosition < targetPositionRight) {
    			RobotMap.RM1.set(-rightPower);
    			RobotMap.RM2.set(-rightPower);
    			RobotMap.RM3.set(-rightPower);
        	} else {
            	RobotMap.RM2.setEncPosition(0);
        		RobotMap.RM1.set(0);
        		RobotMap.RM2.set(0);
        		RobotMap.RM3.set(0);
        		rightGood = true;
        	}
        	
        	//moves left side
        	if(leftPosition < targetPositionLeft) {
    			RobotMap.LM1.set(leftPower);
    			RobotMap.LM2.set(leftPower);
    			RobotMap.LM3.set(leftPower);
        	} else {
            	RobotMap.LM3.setEncPosition(0);
        		RobotMap.LM1.set(0);
        		RobotMap.LM2.set(0);
        		RobotMap.LM3.set(0);
        		//advances to next left position
        	    leftGood = true;	
        	}
        	
        	if(rightGood == true && leftGood == true) {
        		leftCount ++;
        		rightCount ++;
        		rightGood = false;
        		leftGood = false;
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
