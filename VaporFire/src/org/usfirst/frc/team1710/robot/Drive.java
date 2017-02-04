package org.usfirst.frc.team1710.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drive extends Subsystem {

	public static double speedMultiplier = (RobotMap.driveStick.getRawAxis(3)*-1*.5+.5);
	public static double currentYaw;
	public static boolean neutral = RobotMap.driveStick.getRawButton(11);
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public static void arcadeDrive(double forwardPower, double turningPower, boolean turboMode) {
    	if(turboMode = true){
    		RobotMap.leftOne.set((forwardPower * speedMultiplier) - turningPower*speedMultiplier*.3);
        	RobotMap.leftTwo.set((forwardPower * speedMultiplier) - turningPower*speedMultiplier*.3);
        	RobotMap.leftThree.set((forwardPower * speedMultiplier) - turningPower*speedMultiplier*.3);
        	
        	RobotMap.rightOne.set((forwardPower * speedMultiplier) - turningPower*speedMultiplier*.3);
        	RobotMap.rightTwo.set((forwardPower * speedMultiplier) - turningPower*speedMultiplier*.3);
        	RobotMap.rightThree.set((forwardPower * speedMultiplier) - turningPower*speedMultiplier*.3);
    	
        	RobotMap.axisType = 2;
    	}
    	
    	else{ 
        	RobotMap.leftOne.set((forwardPower * speedMultiplier) - turningPower* speedMultiplier);
        	RobotMap.leftTwo.set((forwardPower * speedMultiplier) - turningPower* speedMultiplier);
        	RobotMap.leftThree.set((forwardPower * speedMultiplier) - turningPower* speedMultiplier);
        	
        	RobotMap.rightOne.set((forwardPower * speedMultiplier) - turningPower* speedMultiplier);
        	RobotMap.rightTwo.set((forwardPower * speedMultiplier) - turningPower* speedMultiplier);
        	RobotMap.rightThree.set((forwardPower * speedMultiplier) - turningPower* speedMultiplier);
    		
        	RobotMap.axisType = 0;
    	}
    	
    	
    	if(turboMode == true && neutral == false) {
    		RobotMap.shifter.set(DoubleSolenoid.Value.kForward);
    		SmartDashboard.putBoolean("gear", true);
    	} 
    	else if (neutral == true){
    		RobotMap.shifter.set(DoubleSolenoid.Value.kOff);
    	}
    	else{
    		RobotMap.shifter.set(DoubleSolenoid.Value.kReverse);
    		SmartDashboard.putBoolean("gear", false);
    	} 

    }
    
    public static void StegDrive(double forwardPower) {
    	currentYaw = RobotMap.navx.getYaw();
    	if(currentYaw < -2.5) {
    		//arcadeDrive(forwardPower, currentYaw/100, 0, false);
    	} else if(currentYaw > 2.5){
    		//arcadeDrive(forwardPower, -currentYaw/100, 0, false);
    	} else {
    		//arcadeDrive(forwardPower, 0, 0, false);
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