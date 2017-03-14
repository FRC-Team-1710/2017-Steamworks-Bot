package org.usfirst.frc.team1710.robot;

import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class BetterShooter extends Subsystem {

     // Put methods for controlling this subsystem
    // here. Call these from Commands.
	static double speed;

	
	
	public BetterShooter() {
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());

    }
    public static void run(){
    	//p = 0.6kU
    	//i = 1.2kU/tU
    	//d = (3*kU*tU)/40
    	double targetSpeed = 2100;

    	RobotMap.Shooter1.set(targetSpeed);
    	
    	SmartDashboard.putNumber("Velocity", RobotMap.Shooter1.getEncVelocity());
    	if(RobotMap.Shooter1.getEncVelocity() > 24000 && RobotMap.Shooter1.getEncVelocity() < 26000){
    		Shooter.runIndexer();
    	}else{
    		Shooter.stopIndexer();
    	}
    }
}

