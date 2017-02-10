package org.usfirst.frc.team1710.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drive extends Subsystem {
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	static double flipMultiplier = 1;
	
    public void initDefaultCommand() {
        RobotMap.LPower = 0;
        RobotMap.RPower = 0;
    }
    public static void arcadeDrive(double forwardP, double turnP, double multiplier, float currentYaw, boolean onSteg, boolean onTurbo, boolean neutral) {
    	if(onTurbo == true && neutral == false){
    		RobotMap.LPower = ((forwardP*multiplier) - (turnP*multiplier*.3));
        	RobotMap.RPower = ((forwardP*multiplier) + (turnP*multiplier*.3));    	
        	RobotMap.axisType = 2;
        	Pneumatics.shiftForward();
    	}
    	else if (onTurbo == false && neutral == false){ 
    		RobotMap.LPower = ((forwardP*multiplier) - (turnP*multiplier));    		
    		RobotMap.RPower = ((forwardP*multiplier) + (turnP*multiplier));    		
        	RobotMap.axisType = 0;
        	Pneumatics.shiftReverse();
    	}
    	else if(onTurbo == true && onSteg == true) {
    		StegDrive(forwardP, currentYaw, multiplier);
        	Pneumatics.shiftForward();
    	}
    	else {
    		RobotMap.LPower = ((forwardP*multiplier*.1) - (turnP*multiplier*.1));    		
    		RobotMap.RPower = ((forwardP*multiplier*.1) + (turnP*multiplier*.1));	
        	RobotMap.axisType = 0;
        	Pneumatics.shiftNeutral();
    	}
    }
    
    public static void zeroYaw() {
    	RobotMap.navx.zeroYaw();
    }
    
    public static void StegDrive(double forwardP, float currentYaw, double multiplier) {
    	if(forwardP > 0) {
    		if(currentYaw < (-2.5)) {
				simpleArcade(forwardP, -0.6, multiplier);
			} else if(currentYaw > (2.5)) {
				simpleArcade(forwardP, 0.6, multiplier);
			} else {
				simpleArcade(forwardP, 0.0, multiplier);
			}	
    	} else {
    		if(currentYaw > (-2.5)) {
				simpleArcade(forwardP, 0.6, multiplier);
			} else if(currentYaw < (2.5)) {
				simpleArcade(forwardP, -0.6, multiplier);
			} else {
				simpleArcade(forwardP, 0.0, multiplier);
			}
    	}
    }
    
    public static void stopDriving() {
		RobotMap.LM1.set(0);
		RobotMap.LM2.set(0);
		RobotMap.LM3.set(0);
		RobotMap.RM1.set(0);
		RobotMap.RM2.set(0);
		RobotMap.RM3.set(0);
    }
    
    public static void simpleArcade(double forwardP, double turnP, double multiplier) {
		RobotMap.LPower = ((forwardP*multiplier) - (turnP*multiplier));    		
		RobotMap.RPower = ((forwardP*multiplier) + (turnP*multiplier));
    }
}