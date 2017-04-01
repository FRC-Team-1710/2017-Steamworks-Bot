package org.usfirst.frc.team1710.robot.Commands;

import org.usfirst.frc.team1710.robot.Pneumatics;
import org.usfirst.frc.team1710.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MotionProfile extends Command {
	double[][] leftProfilePublic, rightProfilePublic;
	int cntPublic;
	private CANTalon.SetValueMotionProfile _setValue;
	boolean pointsFilled;
	public MotionProfile(double[][] leftProfile, double[][] rightProfile, int cnt) {
		leftProfilePublic = leftProfile;
		rightProfilePublic = rightProfile;
		cntPublic = cnt;
    }

	class PeriodicRunnable implements java.lang.Runnable {
		public void run() { 
			RobotMap.RM2.processMotionProfileBuffer();
			RobotMap.LM3.processMotionProfileBuffer();
		}
	}
	
	Notifier _notifier = new Notifier(new PeriodicRunnable());
	
    // Called just before this Command runs the first time
    protected void initialize() {
    	Pneumatics.shiftForward();
    	RobotMap.LM3.clearMotionProfileTrajectories();
    	RobotMap.RM2.clearMotionProfileTrajectories();
    	RobotMap.RM2.changeControlMode(TalonControlMode.MotionProfile);
    	RobotMap.LM3.changeControlMode(TalonControlMode.MotionProfile);
    	RobotMap.RM3.changeControlMode(TalonControlMode.Follower);
    	RobotMap.LM2.changeControlMode(TalonControlMode.Follower);
    	RobotMap.RM3.set(RobotMap.RM2.getDeviceID());
    	RobotMap.LM2.set(RobotMap.LM3.getDeviceID());
    	RobotMap.RM2.changeMotionControlFramePeriod(5);
    	RobotMap.LM3.changeMotionControlFramePeriod(5);
    	RobotMap.LM3.reverseOutput(true);
    	RobotMap.RM2.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogEncoder);
    	RobotMap.LM3.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogEncoder);
    	RobotMap.RM2.setF(0.03);
    	RobotMap.LM3.setF(0.03);
    	
    	_setValue = CANTalon.SetValueMotionProfile.Disable;
    	_notifier.startPeriodic(0.005);
    	
		CANTalon.TrajectoryPoint pointleft = new CANTalon.TrajectoryPoint();
		CANTalon.TrajectoryPoint pointright = new CANTalon.TrajectoryPoint();
		for(int i = 0; i < cntPublic; i++) {
			pointleft.position = leftProfilePublic[i][0];
			pointleft.velocity = leftProfilePublic[i][1];
			pointleft.timeDurMs =(int) leftProfilePublic[i][2];
			pointleft.profileSlotSelect = 0;
			pointleft.velocityOnly = false;
		
			pointleft.zeroPos = false;
			if(i==0)
				pointleft.zeroPos = true;
		
			if((i+1) == cntPublic)
				pointleft.isLastPoint = true;
		
			RobotMap.LM3.pushMotionProfileTrajectory(pointleft);
			System.out.println("yuh");
		}
		//fills right profile
		for(int i = 0; i < cntPublic; i++) {
			pointright.position = rightProfilePublic[i][0];
			pointright.velocity = rightProfilePublic[i][1];
			pointright.timeDurMs =(int) rightProfilePublic[i][2];
			pointright.profileSlotSelect = 0;
			pointright.velocityOnly = false;
		
			pointright.zeroPos = false;
			if(i==0)
				pointright.zeroPos = true;
		
			if((i+1) == cntPublic)
				pointright.isLastPoint = true;
		
			RobotMap.RM2.pushMotionProfileTrajectory(pointright);
			System.out.println("oi");
		}
		
		System.out.println("done");
		_setValue = CANTalon.SetValueMotionProfile.Enable;
	}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	RobotMap.RM2.set(getSetValue().value);
    	RobotMap.LM3.set(getSetValue().value);
    	SmartDashboard.putNumber("Right Velocity", RobotMap.RM2.getEncVelocity());
    	SmartDashboard.putNumber("Left Velocity", RobotMap.LM3.getEncVelocity());
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
	CANTalon.SetValueMotionProfile getSetValue() {
		return _setValue;
	}
}
