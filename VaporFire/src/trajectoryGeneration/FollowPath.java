package trajectoryGeneration;

import org.usfirst.frc.team1710.robot.Drive;
import org.usfirst.frc.team1710.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
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
	int rightEncPos, leftEncPos;
	EncoderFollower right, left;
    public FollowPath(Waypoint[] points) {
    	_points = points;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 1.0, 2.0, 60.0);
    	Trajectory trajectory = Pathfinder.generate(_points, config);
    	RobotMap.RM2.setEncPosition(0);
    	RobotMap.LM3.setEncPosition(0);
    	TankModifier modifier = new TankModifier(trajectory).modify(wheelBase);
    	right = new EncoderFollower(modifier.getRightTrajectory());
    	right.configureEncoder(rightEncPos, 1000, wheelDiameter);
    	//pid stuff
    	right.configurePIDVA(.5, 0, 0, 1 / 1.0, 0);
    	
    	left = new EncoderFollower(modifier.getRightTrajectory());
    	left.configureEncoder(leftEncPos, 1000, wheelDiameter);
    	//pid stuff
    	left.configurePIDVA(.5, 0, 0, 1 / 1.0, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	rightEncPos = RobotMap.RM2.getEncPosition();
    	leftEncPos = RobotMap.LM3.getEncPosition();
    	leftOutput = left.calculate(leftEncPos);
    	rightOutput = right.calculate(rightEncPos);
    	currentHeading = RobotMap.navx.getYaw();
    	goalHeading = Pathfinder.r2d(right.getHeading());
    	angleDiff = Pathfinder.boundHalfDegrees(goalHeading - currentHeading);
    	turnVal = 0.8 * (-1.0/80.0) * angleDiff;
    	
    	RobotMap.pRM1.set(rightOutput + turnVal);
    	RobotMap.RM2.set(rightOutput + turnVal);
    	RobotMap.RM3.set(rightOutput + turnVal);

    	RobotMap.pLM1.set((leftOutput + turnVal) * -1);
    	RobotMap.LM2.set((leftOutput + turnVal) * -1);
    	RobotMap.LM3.set((leftOutput + turnVal) * -1);
    	
    	System.out.println(rightOutput)
;    }

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
}
