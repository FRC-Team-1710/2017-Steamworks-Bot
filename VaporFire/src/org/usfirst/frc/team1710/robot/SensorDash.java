package org.usfirst.frc.team1710.robot;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SensorDash extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        //sensor values go here
    }
    public static void dashboard(){
    	//Pressure stuff
    	
    	//Camera stuff
    	
    	//Booleans
    	if (RobotMap.onRPiston == true){
    		SmartDashboard.putBoolean("Right Piston Activated", true);
    	}
    	else{
    		SmartDashboard.putBoolean("Right Piston Activated", false);
    	}
    	if(RobotMap.onLPiston == true){
    		SmartDashboard.putBoolean("Left Piston Activated", true);
    	}
    	else{
    		SmartDashboard.putBoolean("Left Piston Activated", false);
    	}
    	if (RobotMap.onCompress == true){
    		SmartDashboard.putBoolean("Compressor On?", true);
    	}
    	else{
    		SmartDashboard.putBoolean("Compressor On?", false);
    	}
    	if(RobotMap.onTurbo == true){
    		SmartDashboard.putBoolean("High Gear", true);
    	}
    	else{
    		SmartDashboard.putBoolean("High Gear", false);
    	}
    	//Doubles****************
    	//SmartDashboard.putData("Drive Speed", /*put stuff here*/);
    	//SmartDashboard.putData("Shooter Speed", /*put stuff here*/);
    }
}

