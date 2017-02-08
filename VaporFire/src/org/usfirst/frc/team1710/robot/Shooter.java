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
	static public boolean shooteratspeed = false;
	
	
	
    public void initDefaultCommand() {
       power = 0;
    }
    public static void runShooter(){
    	currentVelocity = RobotMap.Shooter1.getEncVelocity();
    	error = ((goalVelocity - currentVelocity) / (goalVelocity + currentVelocity));
    	power -= (inc * error);
    	Timer.delay(.1);
    	RobotMap.Shooter1.set(power);
    	SmartDashboard.putNumber("Motor Power", power);
    	SmartDashboard.putNumber("Velocity", currentVelocity);
    
    }
    public static void runInjector(){
    	RobotMap.Injector.set(1);
    }
    public static void stopInjector(){
    	RobotMap.Injector.set(0);
    }
    
    public static void testMode() {
    	RobotMap.Shooter1.set(-0.3);
    	RobotMap.Shooter2.set(-0.3);
    }
    
    public static void stopShooter(){ 
    	RobotMap.Shooter1.set(0);
    	RobotMap.Shooter2.set(0);
    }
    public static void runSystem(){
    	currentVelocity = RobotMap.Shooter1.getEncVelocity();
    	if(currentVelocity > goalVelocity - 500 && currentVelocity < goalVelocity + 500){
    		runShooter();
    		runInjector();
    	}
    	else{
    		stopInjector();
    		runShooter();
    	}
    }
    public static void noEncoderRun(){
    	if (shooteratspeed == true){
    		RobotMap.Injector.set(1);
    		RobotMap.Shooter1.set(0.8);
    		RobotMap.Shooter2.set(0.8);
    	}
    	else{
    		RobotMap.Shooter1.set(1);
    		RobotMap.Shooter2.set(1);
    		Timer.delay(.5);
    		shooteratspeed = true;
    	}
    }
}
