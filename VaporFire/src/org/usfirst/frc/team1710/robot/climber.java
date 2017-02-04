package org.usfirst.frc.team1710.robot;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	RobotMap.climber.set(0);
    	
    }
    
    
    public static void climb(boolean isClimbing, double speed){
    	if(isClimbing == true){
    		RobotMap.climber.set(speed);     		
    	}else{
    		RobotMap.climber.set(0);
    	}
    }
}

