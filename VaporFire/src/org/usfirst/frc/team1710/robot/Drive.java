package org.usfirst.frc.team1710.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drive extends Subsystem {
	static double currentYaw;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public static void arcadeDrive(double forwardPower, double turningPower, boolean shiftVal, double speedMultiplier) {
       RobotMap.leftOne.set((forwardPower * speedMultiplier) - turningPower* speedMultiplier);
       RobotMap.leftTwo.set((forwardPower * speedMultiplier) - turningPower* speedMultiplier);
       RobotMap.leftThree.set((forwardPower * speedMultiplier) - turningPower* speedMultiplier);
       
       RobotMap.rightOne.set((-forwardPower * speedMultiplier) - turningPower* speedMultiplier);
       RobotMap.rightTwo.set((-forwardPower * speedMultiplier) - turningPower* speedMultiplier);
       RobotMap.rightThree.set((-forwardPower * speedMultiplier) - turningPower* speedMultiplier);    	
    	
       if(shiftVal == true) {
    	   RobotMap.shifter.set(DoubleSolenoid.Value.kReverse);
    	   SmartDashboard.putBoolean("gear", true);
       } else if(shiftVal == false){
    	   RobotMap.shifter.set(DoubleSolenoid.Value.kForward);
    	   SmartDashboard.putBoolean("gear", false);
       } else {
    	   RobotMap.shifter.set(DoubleSolenoid.Value.kOff);
       }
    }
    
    public static void StegDrive(double forwardPower, double speedMultiplier) {
    	currentYaw = RobotMap.navx.getYaw();
    	if(currentYaw < -2.5) {
    		arcadeDrive(forwardPower, currentYaw/100, false, speedMultiplier);
    	} else if(currentYaw > 2.5){
    		arcadeDrive(forwardPower, -currentYaw/100, false, speedMultiplier);
    	} else {
    		arcadeDrive(forwardPower, 0, false, 1);
    	}
    }
    
    public static void zeroYaw() {
    	RobotMap.navx.zeroYaw();
    }
    
    public static void stopDriving() {
		RobotMap.leftOne.set(0);
		RobotMap.leftTwo.set(0);
		RobotMap.leftThree.set(0);
	
		RobotMap.rightOne.set(0);
		RobotMap.rightTwo.set(0);
		RobotMap.rightThree.set(0);
    }
    
    public static void setAllMotorPower(double power) {
		RobotMap.leftOne.set(power);
		RobotMap.leftTwo.set(power);
		RobotMap.leftThree.set(power);
	
		RobotMap.rightOne.set(power);
		RobotMap.rightTwo.set(power);
		RobotMap.rightThree.set(power);
    }
}