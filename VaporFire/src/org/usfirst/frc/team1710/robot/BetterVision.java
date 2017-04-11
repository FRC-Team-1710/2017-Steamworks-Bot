package org.usfirst.frc.team1710.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class BetterVision extends Subsystem {

	public static boolean angleFound, yawSet;
	public static boolean rotated = false;
	public static float angleToTarget;
	public static float currentYaw;
	static NetworkTable table;
	public static double[] centerX, centerY;
	public static double targetX, targetY;
	static double error;
	public static boolean errorFound, sideToSide, upAndDown;
	public static int count;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public static void trackGear(float angleToTurn, boolean aligned) {
    	if(yawSet == false) {
    		if(angleToTurn == 0) {
    			//spins slowly until target is found
    			Drive.simpleArcade(0, 0.3, 1);
    		} else {
    			angleToTarget = angleToTurn;
    			Timer.delay(0.2);
    			yawSet = true;
    			System.out.println("yaw set");
    		}
    	} else {
    		if(rotated == false) {
    			Drive.rotateToAngle(angleToTurn);
    		} else {
    			currentYaw = RobotMap.navx.getYaw();
    		}
    	}
    }
    
    public static void trackBoiler(int config) {
    	table = NetworkTable.getTable("GRIP/BoilerReport");
    	centerX = table.getNumberArray("centerX");
    	centerY = table.getNumberArray("centerY");
    	System.out.println(centerX.length);
    	if(config == 1) {
    		if(centerX.length > 0) {
    			targetX = centerX[0];
    			targetY = centerY[0];
				Drive.simpleArcade(0, -(targetX-340), .008);
    		} else {
    			//red side
    			Drive.simpleArcade(0, 0.25, 1);
    		}
    	} else if(config == 2) {
    		if(centerX.length > 0) {
    			targetX = centerX[0];
    			targetY = centerY[0];
				Drive.simpleArcade((targetY-300), -(targetX-340), .008);
    		} else {
    			//red side
    			Drive.simpleArcade(0, 0.25, 1);
    		}
    	} else if(config == 3) {
    		if(centerX.length > 0) {
    			targetX = centerX[0];
    			targetY = centerY[0];
				Drive.simpleArcade((targetY-300), -(targetX-340), .008);
    		} else {
    			//blue side
    			Drive.simpleArcade(0, -0.25, 1);
    		}
    	} else {
    		if(centerX.length > 0) {
    			targetX = centerX[0];
    			targetY = centerY[0];
				Drive.simpleArcade(0, -(targetX-340), .008);
    		} else {
    			//blue side
    			Drive.simpleArcade(0, -0.25, 1);
    		}
    	}
    }
    
}

