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
    public static void climbthatrope(double ClimbP){
    	RobotMap.Climber.set(ClimbP);
    }
}

