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
	static double inc = 0.4;
	public static boolean shooterAtSpeed, firstInterval, secondInterval;
	static double power = 0;
	static double error = 0;
	static double goalVelocity = 29000;
	static boolean moveAway, moveCloser;
	
    public void initDefaultCommand() {
       power = 0;
       moveAway = false;
       moveCloser = false;
    }
    
    public static void runShooter(){
    	currentVelocity = RobotMap.Shooter1.getEncVelocity();
    	error = ((goalVelocity - currentVelocity) / (goalVelocity + currentVelocity));
    	power += (inc * error);
    	RobotMap.Shooter1.set(power);
    	RobotMap.Shooter2.set(power);
    	Timer.delay(0.25);
    	SmartDashboard.putNumber("Motor Power", power);
    	SmartDashboard.putNumber("Velocity", currentVelocity);
    }

    public static void runIndexer(){
    	if(motorMap.runningCompetitionBot == true) {
    		RobotMap.Injector.set(0.6);
    	} else {
    		RobotMap.pInjector.set(0.6);
    	}
    }
    public static void stopIndexer(){
    	if(motorMap.runningCompetitionBot == true) {
    		RobotMap.Injector.set(0);
    		RobotMap.Injector.stopMotor();
    	} else {
    		RobotMap.pInjector.set(0);
    		RobotMap.pInjector.stopMotor();
    	}
    }
    
    public static void testMode() {
    	RobotMap.Shooter1.set(0);
    	RobotMap.Shooter2.set(-0.5);
    }
    
    public static void stopShooter(){ 
    	RobotMap.Shooter1.stopMotor();
    	RobotMap.Shooter2.stopMotor();
    	RobotMap.Shooter1.set(0);
    	RobotMap.Shooter2.set(0);
    }
    public static void runSystem(){
    	currentVelocity = RobotMap.Shooter1.getEncVelocity();
    	if(currentVelocity > goalVelocity -1000 && currentVelocity < goalVelocity + 5000){
    		runShooter();
    		runIndexer();
    		System.out.println("pew Pew");
    	}else{
    		stopIndexer();
    		runShooter();
    	}
    }
    
    public static void runSystemNoEnc() {
    	if(shooterAtSpeed == true) {
        	RobotMap.Shooter1.set(-1);
        	RobotMap.Shooter2.set(1);		
        	runIndexer();
    		System.out.println("pew pew");
    	} else {
        	RobotMap.Shooter1.set(-1);
        	RobotMap.Shooter2.set(1);
        	Timer.delay(2);
    		shooterAtSpeed = true;
    	}
    }
    
    public static void runSystemNoPID() {
    	if(shooterAtSpeed == true) {
        	RobotMap.Shooter1.set(-0.75);
        	RobotMap.Shooter2.set(0.75);
        	runIndexer();
    		System.out.println("pew pew");
    	} else {
    		stopIndexer();
    		if(firstInterval == false) {
    			RobotMap.Shooter1.set(-0.5);
    			RobotMap.Shooter2.set(0.5);
    			Timer.delay(1);
    			firstInterval = true;
    		} else {
    			RobotMap.Shooter1.set(-0.75);
    			RobotMap.Shooter2.set(0.75);
    			Timer.delay(1);
    			shooterAtSpeed = true;
    		}
    	}
    }
}
