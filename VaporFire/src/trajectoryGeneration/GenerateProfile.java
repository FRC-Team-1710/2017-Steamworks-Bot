package trajectoryGeneration;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.Trajectory.Segment;
import jaci.pathfinder.modifiers.TankModifier;

public class GenerateProfile {
	static double wheelbase = .7116;
	public static double[][] getLeftProfile(Waypoint[] points) {
        Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 1.7, 2.0, 60.0);
        Trajectory trajectory = Pathfinder.generate(points, config);

        TankModifier modifer = new TankModifier(trajectory);
        //sets robot width
        modifer.modify(wheelbase);
        //splits trajectory into ones for both sides of the bot
        Trajectory left = modifer.getLeftTrajectory();
        double leftProfile[][] = new double[left.length()][3];
        //adds points to the 2d array above and formats them like an SRX MP
        for (int i=0; i<left.segments.length; i++) {
            Segment s = left.segments[i];
            leftProfile[i][0] = s.position;
            leftProfile[i][1] = s.velocity;
            leftProfile[i][2] = s.dt;
        }
        
		return leftProfile;
	}
	
	public static double[][] getRightProfile(Waypoint[] points) {
        Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 1.7, 2.0, 60.0);
        Trajectory trajectory = Pathfinder.generate(points, config);

        TankModifier modifer = new TankModifier(trajectory);
        //sets robot width
        modifer.modify(wheelbase);
        //splits trajectory into ones for both sides of the bot
        Trajectory right = modifer.getRightTrajectory();
        double rightProfile[][] = new double[right.length()][3];
        //adds points to the 2d array above and formats them like an SRX MP
        for (int i=0; i<right.segments.length; i++) {
            Segment s = right.segments[i];
            rightProfile[i][0] = s.position;
            rightProfile[i][1] = s.velocity;
            rightProfile[i][2] = s.dt;
        }
        
		return rightProfile;
	}
}
