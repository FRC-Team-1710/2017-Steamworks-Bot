package trajectoryGeneration;

import java.io.File;

import org.usfirst.frc.team1710.robot.Drive;
import org.usfirst.frc.team1710.robot.Pneumatics;
import org.usfirst.frc.team1710.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

/**
 *
 */
public class FollowPath extends Command {
	Waypoint[] _points;
	//dist b/t wheels in meters
	double wheelBase = 0.7116;
	//wheel diameter in meters
	double wheelDiameter = 0.1016;
	double rightOutput, leftOutput, turnVal, currentHeading, goalHeading, angleDiff;
	int rightEncPos, leftEncPos, count;
	boolean _reverse;
	EncoderFollower right, left;
    public FollowPath(Waypoint[] points, boolean reverse) {
    	_points = points;
    	_reverse = reverse;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	count = 0;
    	Pneumatics.shiftForward();
    	Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 1.5, 2, 60.0);
		File trajectoryFile = new File("GearRightRed" + ".csv");
    	Trajectory trajectory = Pathfinder.generate(_points, config);
		Pathfinder.writeToCSV(trajectoryFile, trajectory);
    	RobotMap.RM2.setEncPosition(0);
    	RobotMap.LM3.setEncPosition(0);
    	TankModifier modifier = new TankModifier(trajectory).modify(wheelBase);
    	right = new EncoderFollower(modifier.getRightTrajectory());
    	right.configureEncoder(rightEncPos, 2000, wheelDiameter);
    	//pid stuff
    	right.configurePIDVA(0.8, 0, 0, 1 / 1.5, 0);
    	
    	left = new EncoderFollower(modifier.getLeftTrajectory());
    	left.configureEncoder(leftEncPos, 2000, wheelDiameter);
    	//pid stuff
    	left.configurePIDVA(0.8, 0, 0, 1 / 1.5, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	rightEncPos = RobotMap.RM2.getEncPosition();
    	leftEncPos = RobotMap.LM3.getEncPosition();
    	leftOutput = left.calculate(leftEncPos);
    	rightOutput = right.calculate(rightEncPos);
    	currentHeading = RobotMap.navx.getYaw();
    	goalHeading = right.getHeading();
    	angleDiff = Pathfinder.boundHalfDegrees(goalHeading - currentHeading);
    	turnVal = 0.8 * (-1.0/80.0) * angleDiff;
    	if(_reverse == false) {
    		RobotMap.pRM1.set((rightOutput + turnVal) * 1);
    		RobotMap.RM2.set((rightOutput + turnVal) * 1);
    		RobotMap.RM3.set((rightOutput + turnVal) * 1);

    		RobotMap.pLM1.set((leftOutput - turnVal) * -1);
    		RobotMap.LM2.set((leftOutput - turnVal) * -1);
    		RobotMap.LM3.set((leftOutput - turnVal) * -1);
    	} else {
    		RobotMap.pRM1.set((rightOutput + turnVal) * -1);
    		RobotMap.RM2.set((rightOutput + turnVal) * -1);
    		RobotMap.RM3.set((rightOutput + turnVal) * -1);

    		RobotMap.pLM1.set((leftOutput - turnVal) * 1);
    		RobotMap.LM2.set((leftOutput - turnVal) * 1);
    		RobotMap.LM3.set((leftOutput - turnVal) * 1);
    	}
    	
    	SmartDashboard.putNumber("RightOutput", rightOutput);
    	SmartDashboard.putNumber("LeftOutput", leftOutput);
    	count ++;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return rightOutput == 0 && leftOutput == 0 && count > 5;
    }

    // Called once after isFinished returns true
    protected void end() {
    	count = 0;
    	Drive.stopDriving();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
