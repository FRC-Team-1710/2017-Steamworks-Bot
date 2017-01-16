package org.usfirst.frc.team1710.robot;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drive extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public static void arcadeDrive(double forwardPower, double turningPower, boolean speedBoost){
    	if(speedBoost == true){
    		RobotMap.move.arcadeDrive(forwardPower * -1, turningPower * -1);
    		Robot.axisType = 2;
    		}
    	else{
    		RobotMap.move.arcadeDrive(forwardPower * -.6, turningPower * -.6);
    		Robot.axisType = 0;
    	}
    }
}