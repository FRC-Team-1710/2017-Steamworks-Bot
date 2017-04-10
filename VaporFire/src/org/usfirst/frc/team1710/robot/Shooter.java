package org.usfirst.frc.team1710.robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *This is not Penn, not my CTO
 *
 */
public class Shooter extends Subsystem {

	static double currentVelocity;
	static double inc = 0.4;
	public static boolean shooterAtSpeed, firstInterval, secondInterval, runIndexer;
	static double power = 0;
	static double error = 0;
	static double goalVelocity = 29000;
	static boolean moveAway, moveCloser, added, subtracted;
	static double motorPower = 0.6;
	
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
    
    public static void ShootLow() {
    	RobotMap.Shooter1.set(0.25);
    	RobotMap.Shooter2.set(-0.25);
    	Shooter.runIndexer();
    }
    
    public static void runIndexer(){
    	if(motorMap.runningCompetitionBot == true) {
    		//was 0.84
    		RobotMap.Injector.set(0.80);
    	} else {
    		RobotMap.pInjector.set(0.65);
    	}
    }
    public static void stopIndexer(){
    	if(motorMap.runningCompetitionBot == true) {
    		RobotMap.Injector.stopMotor();
    		RobotMap.Injector.set(0);
    	} else {
    		RobotMap.pInjector.set(0);
    	}
    }
    
    public static void testMode() {
    	RobotMap.Shooter1.set(0);
    	RobotMap.Shooter2.set(-0.5);
    }
    
    public static void stopShooter(){ 
    	RobotMap.Shooter1.set(0);
    	RobotMap.Shooter2.set(0);
    	RobotMap.Shooter1.stopMotor();
    	RobotMap.Shooter2.stopMotor();
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
    
    public static void BestShooter() {
    	double shooterVelocity = RobotMap.Shooter1.getEncVelocity();
    	
    	
    	if(shooterVelocity > 25500) {
    		//lower motor power
    		subtracted = false;
    		if(subtracted == false) {
    			motorPower -= 0.015;
    			System.out.println("subtracted");
    			subtracted = true;
    		}
    	} else if(shooterVelocity < 23000) {
    		//increase motor power
    		added = false;
    		if(added == false) {
    			motorPower += 0.009;
    			System.out.println("added");
    			added = true;
    		}
    	} else {
    		runIndexer = true;
    	}
    	
    	if(runIndexer == true && Math.abs((BetterVision.targetX-300)/500) < .4) {
    		runIndexer();
    	} else {
    		stopIndexer();
    	}
    	RobotMap.Shooter1.set(-motorPower);
    	RobotMap.Shooter2.set(motorPower);
    	Timer.delay(0.25);
    }
}
