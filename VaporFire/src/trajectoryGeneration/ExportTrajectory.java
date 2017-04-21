package trajectoryGeneration;

import java.io.File;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;

public class ExportTrajectory {
	public static void main(String[] args) {
		Export("HopperShootRed", Waypoints.hopperShootRedSnek);
	}
	
	public static void Export(String fileName, Waypoint[] points) {
    	Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 1.5, 2, 60.0);
    	Trajectory trajectory = Pathfinder.generate(points, config);
		File trajectoryFile = new File(fileName + ".traj");
		Pathfinder.writeToFile(trajectoryFile, trajectory);
	}
}
