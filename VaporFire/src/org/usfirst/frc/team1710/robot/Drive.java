package org.usfirst.frc.team1710.robot;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drive extends Subsystem {
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
    public void initDefaultCommand() {
        RobotMap.LPower = 0;
        RobotMap.RPower = 0;
    }
    public static void arcadeDrive(double forwardP, double turnP, double multiplier, double currentYaw, boolean onTurbo, boolean onSteg, boolean neutral ) {
    	if (onSteg == true && (currentYaw> 3 || currentYaw< -3)){
    		turnP = ((RobotMap.currentYaw)/10);
    	}
    	else{
    		RobotMap.navx.zeroYaw();
    	}
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
}