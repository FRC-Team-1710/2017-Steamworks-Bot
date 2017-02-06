package org.usfirst.frc.team1710.robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shooter extends Subsystem {

	static double currentVelocity;
	static double inc = 0.1;
	static double power = 0;
	static double error = 0;
	static int goalVelocity = -28000;
	
	
	
    public void initDefaultCommand() {
       power = 0;
    }
    public static void runShooter(){
    	currentVelocity = RobotMap.shooter.getEncVelocity();
    	error = ((goalVelocity - currentVelocity) / (goalVelocity + currentVelocity));
    	power -= (inc * error);
    	Timer.delay(.1);
    	RobotMap.shooter.set(power);
    	SmartDashboard.putNumber("Motor Power", power);
    	SmartDashboard.putNumber("Velocity", currentVelocity);
    
    }
    public static void runIndexer(){
    	RobotMap.indexer.set(1);
    }
    public static void stopIndexer(){
    	RobotMap.indexer.set(0);
    }
    
    public static void testMode() {
    	RobotMap.shooter.set(-0.3);
    }
    
    public static void stopShooter(){ 
    	RobotMap.shooter.set(0);
    }
    public static void runSystem(){
    	currentVelocity = RobotMap.shooter.getEncVelocity();
    	if(currentVelocity > goalVelocity - 500 && currentVelocity < goalVelocity + 500){
    		runShooter();
    		runIndexer();
    	}else{
    		stopIndexer();
    		runShooter();
    	}
    }
}
