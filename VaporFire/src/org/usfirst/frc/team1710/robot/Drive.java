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
	public static double angleCorrection;
	public static double rotateSpeed;
	
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
    		//StegDrive(forwardP, currentYaw, multiplier);
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
    
    public static void straightDrive(double forwardP, double multiplier, float goalAngle) {
    	angleCorrection = (goalAngle - RobotMap.navx.getYaw()) * -0.02;
    	RobotMap.LPower = ((forwardP*multiplier) + (angleCorrection));
    	RobotMap.RPower = ((forwardP*multiplier) - (angleCorrection));
    }
    
    public static void curve(double forwardP, double inc, float goalAngle) {
    	angleCorrection = (goalAngle - RobotMap.navx.getYaw()) * inc;
    	RobotMap.LPower = ((forwardP*1) + (angleCorrection));
    	RobotMap.RPower = ((forwardP*1) - (angleCorrection));
		RobotMap.LM1.set(RobotMap.LPower * -1);
		RobotMap.LM2.set(RobotMap.LPower * -1);
		RobotMap.LM3.set(RobotMap.LPower * -1);
		RobotMap.RM1.set(RobotMap.RPower * 1);
		RobotMap.RM2.set(RobotMap.RPower * 1);
		RobotMap.RM3.set(RobotMap.RPower * 1);
    }
    
    public static void leftDrive(double power) {
    	RobotMap.LM1.set(power);
    	RobotMap.LM2.set(power);
    	RobotMap.LM3.set(power);
    }
    
    public static void rightDrive(double power) {
    	RobotMap.RM1.set(-power);
    	RobotMap.RM2.set(-power);
    	RobotMap.RM3.set(-power);
    }
    
    public static void pidDrive() {
    	
    }
    
}