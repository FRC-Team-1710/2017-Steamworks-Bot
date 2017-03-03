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
	public static boolean yawZeroed;
	
    public void initDefaultCommand() {
        RobotMap.LPower = 0;
        RobotMap.RPower = 0;
    }
    public static void arcadeDrive(double forwardP, double turnP, double multiplier, float currentYaw, boolean onSteg, boolean onTurbo, boolean neutral) {
    	if(onTurbo == true && neutral == false){
    		RobotMap.LPower = (((forwardP*multiplier) * -1) -((RobotMap.navx.getYaw()/666) * -1));
        	RobotMap.RPower = (((forwardP*multiplier) * -1) + ((RobotMap.navx.getYaw()/666)*multiplier * -1));    	
        	RobotMap.axisType = 2;
        	Pneumatics.shiftReverse();
        	
    	}
    	else if (onTurbo == false && neutral == false){ 
    		RobotMap.LPower = (((forwardP*multiplier) * -1) - (turnP*multiplier * -1));    		
    		RobotMap.RPower = (((forwardP*multiplier) * -1) + (turnP*multiplier * -1));    		
        	RobotMap.axisType = 0;
        	Pneumatics.shiftForward();
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
    	if(yawZeroed == true) { 
    		if(forwardP > 0) {
    			if(currentYaw < (-5)) {
    				simpleArcade(forwardP, -0.1, multiplier);
    			} else if(currentYaw > (5)) {
    				simpleArcade(forwardP, 0.1, multiplier);
					} else {
						simpleArcade(forwardP, 0.0, multiplier);
					}	
    		} else {
    			if(currentYaw > (-5)) {
    				simpleArcade(forwardP, 0.1, multiplier);
    			} else if(currentYaw < (5)) {
    				simpleArcade(forwardP, -0.1, multiplier);
    			} else {
    				simpleArcade(forwardP, 0.0, multiplier);
    			}
    		}
    	} else {
    		RobotMap.navx.zeroYaw();
    		Timer.delay(0.2);
    		yawZeroed = true;
    	}
    }
    
    public static void RotateToAngle(float angleToTurn) {
    	float currentYaw = RobotMap.navx.getYaw();

    	if (angleToTurn > 0){
    		if (currentYaw > angleToTurn - 1 && currentYaw < angleToTurn + 1){
    			simpleArcade(0, 0, 1);
    			BetterVision.rotated = true;
    		} else if (angleToTurn > currentYaw){
    			simpleArcade(0, .3, 1);
    		} else if (currentYaw > angleToTurn){
    			simpleArcade(0, -.3, 1);
    		}
    	} else if(angleToTurn < 0) {
    		if (currentYaw > angleToTurn - 1 && currentYaw < angleToTurn + 1){
        		simpleArcade(0, 0, 1);
    			BetterVision.rotated = true;
        	} else if (angleToTurn < currentYaw){
        		simpleArcade(0, -.3, 1);
        	} else if (currentYaw > angleToTurn){
        		simpleArcade(0, .3, 1);
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
    
    public static void straightDrive(double forwardP, double multiplier) {
    	RobotMap.LPower = ((forwardP*multiplier) - (-RobotMap.navx.getYaw()/666));
    	RobotMap.RPower = ((forwardP*multiplier) + (-RobotMap.navx.getYaw()/666));
    }
}