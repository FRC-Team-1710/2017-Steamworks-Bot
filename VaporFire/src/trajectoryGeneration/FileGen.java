package trajectoryGeneration;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;

import java.io.File;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FileGen extends Command {
	Waypoint[] _points;
	boolean done;
    public FileGen(Waypoint[] points) {
    	_points = points;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	done = false;
    	Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 1.5, 2, 60.0);
    	Trajectory trajectory = Pathfinder.generate(_points, config);
		File trajectoryFile = new File("HopperShootRed.traj");
		Pathfinder.writeToFile(trajectoryFile, trajectory);
		done = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    	done = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
