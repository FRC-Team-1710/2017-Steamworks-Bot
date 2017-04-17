package trajectoryGeneration;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;

public class Waypoints {
	//make 2d arrays of the waypoints for whatever auto
	//these waypoints lists are then utilized in the profile generate class when you run it and output to a csv file
	
    public static Waypoint[] testPoints = new Waypoint[] {
    		new Waypoint(0, 0, 0),
            new Waypoint(1, 0, 0),
            new Waypoint(2, 0, 15),
            new Waypoint(3, 0, 0),
    };
}
