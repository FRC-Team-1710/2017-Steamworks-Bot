package org.usfirst.frc.team1710.robot.Commands;

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
public class PathFollower extends Command {

    public PathFollower() {

    }

    protected void initialize() {
    	Waypoint[] points = new Waypoint[] {
    		    new Waypoint(-4, -1, Pathfinder.d2r(-45)),      // Waypoint @ x=-4, y=-1, exit angle=-45 degrees
    		    new Waypoint(-2, -2, 0),                        // Waypoint @ x=-2, y=-2, exit angle=0 radians
    		    new Waypoint(0, 0, 0)                           // Waypoint @ x=0, y=0,   exit angle=0 radians
    		};

    	Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 1.7, 2.0, 60.0);
    	Trajectory trajectory = Pathfinder.generate(points, config);
    	//width of robot in meters
    	//loop thru trajectory.segments and see if you can get stuff relevant to srx MP
    	TankModifier modifier = new TankModifier(trajectory).modify(0.7112);
    	EncoderFollower left = new EncoderFollower(modifier.getLeftTrajectory());
    	EncoderFollower right = new EncoderFollower(modifier.getRightTrajectory());
    	//configured with grayhill 63r encoders
    	left.configureEncoder(RobotMap.LM3.getEncPosition(), 1024, .1016);
    	right.configureEncoder(RobotMap.RM2.getEncPosition(), 1024, .1016);
    	//set pid stuff up
    	left.configurePIDVA(1.0, 0.0, 0.0, 1 / 2/*velocity*/, 0);
    	right.configurePIDVA(1.0, 0.0, 0.0, 1 / 2/*velocity*/, 0);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
