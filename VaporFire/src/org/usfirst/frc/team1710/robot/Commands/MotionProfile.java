package org.usfirst.frc.team1710.robot.Commands;

import java.util.Arrays;

import org.usfirst.frc.team1710.robot.Pneumatics;
import org.usfirst.frc.team1710.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Waypoint;
import trajectoryGeneration.GenerateProfile;

/**
 *
 */
public class MotionProfile extends Command {
	double[][] leftProfile, rightProfile;
	Waypoint[] _points;
	private CANTalon.SetValueMotionProfile _setValue;
	boolean pointsFilled, done;
	int cntPublic;
	CANTalon.MotionProfileStatus _statusRight = new CANTalon.MotionProfileStatus();
	CANTalon.MotionProfileStatus _statusLeft = new CANTalon.MotionProfileStatus();
	
	public MotionProfile(Waypoint[] points) {
		_points = points;
    }

	class PeriodicRunnable implements java.lang.Runnable {
		public void run() { 
			if(_statusRight.activePoint.isLastPoint == true && _statusLeft.activePoint.isLastPoint == true) {
				//done = true;
				//System.out.println("See Ya!");
			} else {
				RobotMap.RM2.getMotionProfileStatus(_statusRight);
				RobotMap.LM3.getMotionProfileStatus(_statusLeft);
				RobotMap.RM2.processMotionProfileBuffer();
				RobotMap.LM3.processMotionProfileBuffer();
				done = false;
			}
		}
	}
	
	public Notifier _notifier = new Notifier(new PeriodicRunnable());
	
    // Called just before this Command runs the first time
    protected void initialize() {
    	//shifts into high gear
    	//resets any profiles on the srx's
    	RobotMap.RM2.clearMotionProfileTrajectories();
    	RobotMap.LM3.clearMotionProfileTrajectories();
    	//translates a trajectory from Jaci's pathfinder into a motion profile that's readable by the srx's
    	leftProfile = GenerateProfile.getLeftProfile(_points);
    	rightProfile = GenerateProfile.getRightProfile(_points);
    	//configure right side talons
    	RobotMap.RM2.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	RobotMap.RM2.configEncoderCodesPerRev(3094);
    	RobotMap.RM2.changeControlMode(TalonControlMode.MotionProfile);
    	//pid stuff
    	RobotMap.RM2.setP(1);
    	RobotMap.RM2.setI(0);
    	RobotMap.RM2.setD(1);
    	RobotMap.RM2.setF(1);
    	//configures left side talons- using grayhill 63r's
    	RobotMap.LM3.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	RobotMap.LM3.configEncoderCodesPerRev(3094);
    	RobotMap.LM3.changeControlMode(TalonControlMode.MotionProfile);
    	//more PID stuff
    	RobotMap.LM3.setP(1);
    	RobotMap.LM3.setI(0);
    	RobotMap.LM3.setD(1);
    	RobotMap.LM3.setF(1);
    	//makes other motors on right/left sides slaves to the ones in MP mode
    	RobotMap.RM3.changeControlMode(TalonControlMode.Follower);
    	RobotMap.LM2.changeControlMode(TalonControlMode.Follower);
    	//sets the specific talon ID to follow
    	RobotMap.RM3.set(RobotMap.RM2.getDeviceID());
    	RobotMap.LM2.set(RobotMap.LM3.getDeviceID());
    	//sets resolution of profile (each trajectory point is run for 20 ms)
    	RobotMap.LM3.changeMotionControlFramePeriod(20);
    	RobotMap.RM2.changeMotionControlFramePeriod(20);
    	//not sure if we need to reverse output... let's see
    	RobotMap.LM3.reverseOutput(true);
    	RobotMap.RM2.reverseOutput(false);
    	
		CANTalon.TrajectoryPoint pointright = new CANTalon.TrajectoryPoint();
		CANTalon.TrajectoryPoint pointleft = new CANTalon.TrajectoryPoint();
		for(int i = 0; i < leftProfile.length; ++i) {
			//sets values of each point object to the corresponding point in profiles.java
			pointleft.position = leftProfile[i][0];
			pointleft.velocity = leftProfile[i][1];
			pointleft.timeDurMs =(int) leftProfile[i][2];
			pointleft.profileSlotSelect = 0;
			pointleft.velocityOnly = false;
			//first position
			pointleft.zeroPos = false;
			if(i==0)
				pointleft.zeroPos = true;
			//last position
			if((i+1) == leftProfile.length - 1)
				pointleft.isLastPoint = true;
			//saves point to srx
			RobotMap.LM3.pushMotionProfileTrajectory(pointleft);
			System.out.println("oi");
		}
		//fills right profile
		for(int i = 0; i < rightProfile.length; ++i) {
			pointright.position = rightProfile[i][0];
			pointright.velocity = rightProfile[i][1];
			pointright.timeDurMs =(int) rightProfile[i][2];
			pointright.profileSlotSelect = 0;
			pointright.velocityOnly = false;
		
			pointright.zeroPos = false;
			if(i==0)
				pointright.zeroPos = true;
		
			if((i+1) == rightProfile.length - 1)
				pointright.isLastPoint = true;
		
			RobotMap.RM2.pushMotionProfileTrajectory(pointright);
			System.out.println("oi");
		}
		
		System.out.println("done");
		//tell motor to begin reading from buffer
    	RobotMap.RM2.set(1);
    	RobotMap.LM3.set(1);
    	_notifier.startPeriodic(0.005);
    	//starts the buffer
	}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//puts velocity on dashboard
    	SmartDashboard.putNumber("Right Velocity", RobotMap.RM2.getEncVelocity());
    	SmartDashboard.putNumber("Left Velocity", RobotMap.LM3.getEncVelocity());
    }  
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.RM2.set(0);
    	RobotMap.LM3.set(0);
    	_notifier.stop();
    	RobotMap.LM3.changeControlMode(TalonControlMode.PercentVbus);
    	RobotMap.RM3.changeControlMode(TalonControlMode.PercentVbus);
    	RobotMap.LM2.changeControlMode(TalonControlMode.PercentVbus);
    	RobotMap.RM2.changeControlMode(TalonControlMode.PercentVbus);
    	done = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	_notifier.stop();
    }
    
	CANTalon.SetValueMotionProfile getSetValue() {
		return _setValue;
	}
}
