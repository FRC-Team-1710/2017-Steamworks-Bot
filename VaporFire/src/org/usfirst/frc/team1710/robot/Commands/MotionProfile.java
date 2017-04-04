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
			//RobotMap.RM2.processMotionProfileBuffer();
			RobotMap.LM3.processMotionProfileBuffer();
		}
	}
	
	Notifier _notifier = new Notifier(new PeriodicRunnable());
	
    // Called just before this Command runs the first time
    protected void initialize() {
    	//shifts into low gear
    	Pneumatics.shiftForward();
    	//resets any profiles on the srx's
    	RobotMap.LM3.clearMotionProfileTrajectories();
    	RobotMap.RM2.clearMotionProfileTrajectories();
    	//makes it so one left motor is in MP mode and every other motor is a slave to it
    	RobotMap.RM2.changeControlMode(TalonControlMode.Follower);
    	RobotMap.LM3.changeControlMode(TalonControlMode.MotionProfile);
    	RobotMap.RM3.changeControlMode(TalonControlMode.Follower);
    	RobotMap.LM2.changeControlMode(TalonControlMode.Follower);
    	RobotMap.RM2.set(RobotMap.LM3.getDeviceID());
    	RobotMap.RM3.set(RobotMap.RM2.getDeviceID());
    	RobotMap.LM2.set(RobotMap.LM3.getDeviceID());
    	//sets resolution of profile (each trajectory is run for 10 ms)
    	RobotMap.RM2.changeMotionControlFramePeriod(10);
    	RobotMap.LM3.changeMotionControlFramePeriod(10);
    	RobotMap.LM3.reverseOutput(false);
    	//output of right side is reversed b/c we want to move straight and it's following the left
    	RobotMap.RM2.reverseOutput(true);
    	//grayhill 63r's are quadrature encoders
    	//RobotMap.RM2.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	RobotMap.LM3.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	
    	_setValue = CANTalon.SetValueMotionProfile.Disable;
		CANTalon.TrajectoryPoint pointleft = new CANTalon.TrajectoryPoint();
		CANTalon.TrajectoryPoint pointright = new CANTalon.TrajectoryPoint();
		for(int i = 0; i < cntPublic; i++) {
			//sets values of each point object to the corresponding point in profiles.java
			pointleft.position = leftProfilePublic[i][0];
			pointleft.velocity = leftProfilePublic[i][1];
			pointleft.timeDurMs =(int) leftProfilePublic[i][2];
			pointleft.profileSlotSelect = 0;
			pointleft.velocityOnly = false;
		
			//first position
			pointleft.zeroPos = false;
			if(i==0)
				pointleft.zeroPos = true;
			//last position
			if((i+1) == cntPublic)
				pointleft.isLastPoint = true;
			//saves point to srx
			RobotMap.LM3.pushMotionProfileTrajectory(pointleft);
			System.out.println("yuh");
		}
		//fills right profile
		/*for(int i = 0; i < cntPublic; i++) {
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
		}*/
		
		System.out.println("done");
		//tell LM3 to begin reading from buffer
		_setValue = CANTalon.SetValueMotionProfile.Enable;
    	RobotMap.LM3.set(_setValue.value);
    	//starts the buffer
    	_notifier.startPeriodic(0.0005);
	}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//puts velocity on dashboard
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
