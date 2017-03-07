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
	public static double[] centerX;
	public static double targetX;
	static double error;
	static boolean errorFound;
	
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
    			Drive.RotateToAngle(angleToTurn);
    		} else {
    			currentYaw = RobotMap.navx.getYaw();
    			Drive.StegDrive(-0.5, currentYaw, 1);
    		}
    	}
    }
    
    public static void trackBoiler() {
    	table = NetworkTable.getTable("Root/GRIP/BoilerReport");
    	centerX = table.getNumberArray("centerX");
    	if(centerX.length > 0) {
    		System.out.println("I see it " + error );
    		if(targetX > 340) {
    			Drive.simpleArcade(0, -0.1, 1);
    		} else if(targetX < 290) {
    			Drive.simpleArcade(0, 0.1 , 1);
    		} else {
    			Drive.simpleArcade(0, 0, 0);
    		}
    		targetX = centerX[0];
    	} else {
    		Drive.simpleArcade(0, 0.25, 1);
    		System.out.println("I don't see anything");
    	}
    } 
    
}

