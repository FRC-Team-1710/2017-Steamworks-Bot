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
    	table = NetworkTable.getTable("GRIP/BoilerReport");
    	centerX = table.getNumberArray("centerX");
    	centerY = table.getNumberArray("centerY");
    	if(centerX.length > 0) {
    		targetX = centerX[0];
    		targetY = centerY[0];
    		if(Math.abs((targetX-320))  > 40 && sideToSide == false) {
    			if((targetX-320) <= 25) {
    				upAndDown = false;
    				sideToSide = true;
    			} else {
        			Drive.simpleArcade(0, (targetX-320)/500, -.5);
        			System.out.println("turn speed " + (targetX-320)/500);
    			}
    		} else if(Math.abs((targetY-140)) > 40 && upAndDown == false) {
        		if((targetY-140) <= 25) {
        			upAndDown = true;
        			sideToSide = false;
        		} else {
        			Drive.simpleArcade((targetY-140)/225, 0, 0.75);
            		System.out.println("forward speed" + (targetY-140)/225);
        		}
    		}
    	} else {
    		Drive.simpleArcade(0, 0.25, 1);
    	}
    } 
    
}

