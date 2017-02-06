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
	static double inc = 0.08;
	static double power = 0;
	static double error = 0;
	static int goalVelocity = -28000;
	
    public void initDefaultCommand() {
       power = 0;
    }
    public static void run(){
    	currentVelocity = RobotMap.Shooter1.getEncVelocity();
    	error = ((goalVelocity - currentVelocity) / (goalVelocity + currentVelocity));
    	power -= (inc * error);
    	Timer.delay(.25);
    	RobotMap.Shooter1.set(power);
    	RobotMap.Shooter2.set(power);
    	SmartDashboard.putNumber("Motor Power", power);
    	SmartDashboard.putNumber("Velocity", currentVelocity);
    }
    public static void testMode() {
    	RobotMap.Shooter1.set(-0.3);
    	RobotMap.Shooter2.set(-0.3);
    }
    public static void stopShooter(){ 
    	RobotMap.Shooter1.set(0);
    	RobotMap.Shooter2.set(0);
    }
}

