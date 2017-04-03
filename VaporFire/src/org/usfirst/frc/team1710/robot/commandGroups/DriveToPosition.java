package org.usfirst.frc.team1710.robot.commandGroups;

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
	int positionPublic, currentPosition;
	boolean done;
    public DriveToPosition(int position) {
    	positionPublic = position;
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
    	Pneumatics.shiftForward();
    	RobotMap.LM3.setEncPosition(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentPosition = RobotMap.LM3.getEncPosition();
    	System.out.println(1 - (currentPosition/positionPublic));
    	if (currentPosition < positionPublic) {
    		Drive.straightDrive(0.5, 1);
    		System.out.println("gogogo");
    		RobotMap.pRM1.set(RobotMap.RPower);
    		RobotMap.RM2.set(RobotMap.RPower);
    		RobotMap.RM3.set(RobotMap.RPower);
    		RobotMap.pLM1.set(RobotMap.LPower*-1);
    		RobotMap.LM2.set(RobotMap.LPower*-1);
    		RobotMap.LM3.set(RobotMap.LPower*-1);
    	} else {
    		done = true;
    		RobotMap.pRM1.set(0);
    		RobotMap.RM2.set(0);
    		RobotMap.RM3.set(0);
    		RobotMap.pLM1.set(0);
    		RobotMap.LM2.set(0);
    		RobotMap.LM3.set(0);
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
