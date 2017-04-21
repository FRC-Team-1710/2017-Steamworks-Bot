package trajectoryGeneration;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;

public class Waypoints {
	//make 2d arrays of the waypoints for whatever auto
	//these waypoints lists are then utilized in the profile generate class when you run it and output to a csv file
	
    public static Waypoint[] testCurve = new Waypoint[] {
        new Waypoint(1, 0, 0),
        new Waypoint(2, .5, 0),
        new Waypoint(3, 2, 0),
        new Waypoint(4, 2, 0),
    };
    
    //TODO: adjust distances
    //this path snakes right up to the button and does a swift, clean turn back and forth to release the balls
    public static Waypoint[] hopperShootBlueSnek = new Waypoint[] {
        new Waypoint(1, 0, 0),
        new Waypoint(2, .4, 0),
        new Waypoint(3, 1.2, 0),
        new Waypoint(4.35, 1.3, -10),
    };
    public static Waypoint[] hopperShootRedSnek = new Waypoint[] {
        new Waypoint(1, 0, 0),
        new Waypoint(2, -.4, 0),
        new Waypoint(3, -1.65, 0),
        new Waypoint(4.35, -1.7, 10),
    };
    //TODO:adjust distances
    //this path starts parallel to the boiler then curves right into the hopper button
    public static Waypoint[] hopperShootRedCurve = new Waypoint[] {
        new Waypoint(0, 0, 0),
        new Waypoint(.5, 0, 0),
        new Waypoint(1.3, .6, -90),
    };
    
    public static Waypoint[] testPoints = new Waypoint[] {
    	new Waypoint(1,0,0),
    	new Waypoint(2,0,0),
    	new Waypoint(2.2,0,90),
    };
    public static Waypoint[] gearRightRed = new Waypoint[] {
        new Waypoint(10, 0, 0),
      /*  new Waypoint(6, .8, 0),
        new Waypoint(7, 1.6, 0),
        new Waypoint(6, 1.6, 0),*/
    };

}
