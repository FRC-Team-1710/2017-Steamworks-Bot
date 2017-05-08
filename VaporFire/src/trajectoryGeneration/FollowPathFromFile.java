package trajectoryGeneration;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.usfirst.frc.team1710.robot.RobotMap;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class FollowPathFromFile extends Command {
	double wheelBase, wheelDiameter, turnVal, currentHeading, goalHeading, angleDiff, leftOutput, rightOutput;
	int rightEncPos, leftEncPos, count;
	EncoderFollower right, left;
	String _fileName;
	
	static Trajectory _trajectory;
    public FollowPathFromFile(String fileName) {
    	_fileName = fileName;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	count = 0;
    	File trajFile = new File("HopperShootRed.traj");
    	_trajectory = Pathfinder.readFromFile(trajFile);
    	TankModifier modifier = new TankModifier(_trajectory).modify(wheelBase);
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
    	
    	RobotMap.RM1.set((rightOutput + turnVal) * 1);
    	RobotMap.RM2.set((rightOutput + turnVal) * 1);
    	RobotMap.RM3.set((rightOutput + turnVal) * 1);

    	RobotMap.LM1.set((leftOutput - turnVal) * -1);
    	RobotMap.LM2.set((leftOutput - turnVal) * -1);
    	RobotMap.LM3.set((leftOutput - turnVal) * -1);
    	
    	SmartDashboard.putNumber("RightOutput", rightOutput);
    	SmartDashboard.putNumber("LeftOutput", leftOutput);
    	count++;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return count > 50 && rightOutput == 0 && leftOutput == 0;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
