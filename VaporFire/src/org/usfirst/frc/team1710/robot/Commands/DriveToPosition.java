package org.usfirst.frc.team1710.robot.Commands;

import org.usfirst.frc.team1710.robot.Drive;
import org.usfirst.frc.team1710.robot.Pneumatics;
import org.usfirst.frc.team1710.robot.RobotMap;

import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveToPosition extends Command {
	double positionPublic, currentPosition, _speed;
	boolean done;
	float _heading;
    public DriveToPosition(int inches, float heading, double speed) {
    	positionPublic = inches * 400;
    	_heading = heading;
    	_speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	/*RobotMap.LM3.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	RobotMap.LM3.changeControlMode(TalonControlMode.Position);
    	RobotMap.LM3.configNominalOutputVoltage(+0f, -0f);
    	RobotMap.LM3.configPeakOutputVoltage(+12f, -12f);
    	RobotMap.LM3.setAllowableClosedLoopErr(0);
    	RobotMap.RM2.changeControlMode(TalonControlMode.Follower);
    	RobotMap.RM2.set(RobotMap.LM3.getDeviceID());
    	RobotMap.RM2.reverseOutput(true);
    	RobotMap.RM3.changeControlMode(TalonControlMode.Follower);
    	RobotMap.RM3.set(RobotMap.RM2.getDeviceID());
    	RobotMap.LM2.changeControlMode(TalonControlMode.Follower);
    	RobotMap.LM2.set(RobotMap.LM3.getDeviceID());*/
    	RobotMap.RM2.setEncPosition(0);
		done = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentPosition = RobotMap.RM2.getEncPosition();
    	if((Math.abs(currentPosition)) < (Math.abs(positionPublic))) {
    		if(positionPublic < 0){
    			Drive.straightDrive(-_speed, 1, _heading);
    		}else{
    			Drive.straightDrive(_speed, 1, _heading);
    		}
    		RobotMap.LM1.set(RobotMap.LPower );
    		RobotMap.LM2.set(RobotMap.LPower );
    		RobotMap.LM3.set(RobotMap.LPower );
    		RobotMap.RM1.set(RobotMap.RPower * -1);
    		RobotMap.RM2.set(RobotMap.RPower * -1);
    		RobotMap.RM3.set(RobotMap.RPower * -1);
    	} else {
    		done = true;
    		RobotMap.LM1.set(0);
    		RobotMap.LM2.set(0);
    		RobotMap.LM3.set(0);
    		RobotMap.RM1.set(0);
    		RobotMap.RM2.set(0);
    		RobotMap.RM3.set(0);
    		System.out.println("bye");
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.RM2.setEncPosition(0);
    	done = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
