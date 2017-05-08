package org.usfirst.frc.team1710.robot.Commands;

import org.usfirst.frc.team1710.robot.Drive;
import org.usfirst.frc.team1710.robot.Pneumatics;
import org.usfirst.frc.team1710.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RotateToAngleButWithEncoders extends Command {
	double anglePublic;
	double rightPosition, leftPosition, _speed;
	int _count;
	boolean done, _dir;
    public RotateToAngleButWithEncoders(boolean dir, int count, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	_dir = dir;
    	_count = count;
    	_speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Pneumatics.shiftForward();
    	RobotMap.RM2.setEncPosition(0);
    	done = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	rightPosition =(double) RobotMap.RM2.getEncPosition();
    	SmartDashboard.putNumber("encoder2", rightPosition);
    	if(_dir == true && _count > Math.abs(rightPosition)) {
    		Drive.simpleArcade(_speed, 0, 1);
    		RobotMap.LM1.set(RobotMap.LPower);
    		RobotMap.LM2.set(RobotMap.LPower);
    		RobotMap.LM3.set(RobotMap.LPower);
    		RobotMap.RM1.set(RobotMap.LPower);
    		RobotMap.RM2.set(RobotMap.LPower);
    		RobotMap.RM3.set(RobotMap.LPower);
    	} else if(_dir == false && _count > Math.abs(rightPosition)) {
    		Drive.simpleArcade(_speed, 0, -1);
    		RobotMap.LM1.set(RobotMap.LPower);
    		RobotMap.LM2.set(RobotMap.LPower);
    		RobotMap.LM3.set(RobotMap.LPower);
    		RobotMap.RM1.set(RobotMap.LPower);
    		RobotMap.RM2.set(RobotMap.LPower);
    		RobotMap.RM3.set(RobotMap.LPower);
    	} else {
    		Drive.stopDriving();
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
