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
    			Drive.arcadeDrive(0, 0.4, false);
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
    			Drive.arcadeDrive(-0.6, 0, false);
    		}
    	}
    }
    
}

