package org.usfirst.frc.team1710.robot;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class climber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	RobotMap.Climber.set(0);
    	
    }
    public static void climbthatshit(boolean onClimbPos, boolean onClimbNeg, double ClimbP){
    	if(onClimbPos == true && onClimbNeg == false){
    		RobotMap.Climber.set(ClimbP*-1);     		
    	}
    	else if(onClimbPos == false && onClimbNeg == true){
    		RobotMap.Climber.set(ClimbP);
    	}
    	else{
    		RobotMap.Climber.set(0);
    	}
    }
}

