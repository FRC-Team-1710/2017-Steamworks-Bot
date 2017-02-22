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
	static final double kP = 0.3;
	static final double kI = 0.4;
	static final double kD = 0.02;
	
	
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
    	double targetSpeed = 25000;
    	
        RobotMap.Shooter1.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
        RobotMap.Shooter1.configNominalOutputVoltage(+0.0f, -0.0f);
        RobotMap.Shooter1.configPeakOutputVoltage(+12.0f, -12.0f);
        RobotMap.Shooter1.reverseSensor(false);
    	RobotMap.Shooter1.setP(kP);
    	RobotMap.Shooter1.setI(kI);
    	//I dont think we will need this
    	RobotMap.Shooter1.setD(0);
    	
    	RobotMap.Shooter1.changeControlMode(TalonControlMode.Speed);
    	RobotMap.Shooter1.set(-targetSpeed);
    	RobotMap.Shooter2.reverseOutput(true);
    	
    	SmartDashboard.putNumber("Velocity", RobotMap.Shooter1.getEncVelocity());
    	if(RobotMap.Shooter1.getEncVelocity() > 24000){
    		Shooter.runIndexer();
    	}else{
    		Shooter.stopIndexer();
    	}
    }
}

