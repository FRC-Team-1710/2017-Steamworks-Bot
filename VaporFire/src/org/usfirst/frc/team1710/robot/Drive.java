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
	public static float currentYaw;
	
    public void initDefaultCommand() {
        RobotMap.LPower = 0;
        RobotMap.RPower = 0;
    }
    public static void arcadeDrive(double forwardP, double turnP, double multiplier, float currentYaw, boolean onSteg, boolean onTurbo, boolean neutral) {
    	if(onTurbo == true && neutral == false){
    		RobotMap.LPower = (((forwardP*multiplier) * -1) - (turnP*multiplier*-1));    		
    		RobotMap.RPower = (((forwardP*multiplier) * -1) + (turnP*multiplier*-1)); 
        	RobotMap.axisType = 2;
        	Pneumatics.shiftForward();
    	}
    	else if (onTurbo == false && neutral == false){ 
    		if(RobotMap.flipped = true) {
    			RobotMap.LPower = (((forwardP*multiplier) * -1) - (turnP));    		
    			RobotMap.RPower = (((forwardP*multiplier) * -1) + (turnP));  		
    		} else {
    			RobotMap.LPower = (((forwardP*multiplier) * -1) - (turnP));    		
    			RobotMap.RPower = (((forwardP*multiplier) * -1) + (turnP)); 
    		}
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
    	RobotMap.LPower = ((forwardP*multiplier) - (-(RobotMap.navx.getYaw())/450));
    	RobotMap.RPower = ((forwardP*multiplier) + (-(RobotMap.navx.getYaw())/450));
    }
    
    public static boolean rotateToAngle(float turningDegreePublic) {
    	if (turningDegreePublic > 0){
    		currentYaw = RobotMap.navx.getYaw();
    		// was 5
    		if (currentYaw > turningDegreePublic - .5 && currentYaw < turningDegreePublic + .5  || (currentYaw / turningDegreePublic) > 0.94){
    			Drive.simpleArcade(0, 0, 0);
            	System.out.println(currentYaw+"done");
            	return true;
    		} else if(currentYaw > turningDegreePublic) {
    			Drive.simpleArcade(0, -0.4, 1 - Math.abs((currentYaw / turningDegreePublic)));
    		} else if(currentYaw < turningDegreePublic) {
    			Drive.simpleArcade(0, 0.4, 1 - Math.abs((currentYaw / turningDegreePublic)));
    		}
    	} else {
        	currentYaw = RobotMap.navx.getYaw();
        	System.out.println(currentYaw);
        	//wAS 5
    		if (currentYaw > turningDegreePublic - .5 && currentYaw < turningDegreePublic + .5 || (currentYaw / turningDegreePublic) > 0.94){
    			Drive.simpleArcade(0, 0, 0);
            	System.out.println(currentYaw + "final");
            	return true;
        	} else if(currentYaw > turningDegreePublic) {
    			Drive.simpleArcade(0, -0.4, 1 - Math.abs((currentYaw / turningDegreePublic)));
    		} else if(currentYaw < turningDegreePublic) {
    			Drive.simpleArcade(0, 0.4, 1 - Math.abs((currentYaw / turningDegreePublic)));
    		}
    	}
		RobotMap.LM1.set(RobotMap.LPower);
		RobotMap.LM2.set(RobotMap.LPower);
		RobotMap.LM3.set(RobotMap.LPower);

		RobotMap.RM1.set(RobotMap.RPower * -1);
		RobotMap.RM2.set(RobotMap.RPower * -1);
		RobotMap.RM3.set(RobotMap.RPower * -1);
    	return false;
    }
}