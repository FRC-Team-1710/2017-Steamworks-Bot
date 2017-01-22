package org.usfirst.frc.team1710.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drive extends Subsystem {

	public static double speedMultiplier = 1;
	static double currentYaw;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	speedMultiplier = 0.5;
    }
    public static void arcadeDrive(double forwardPower, double turningPower, double shiftVal) {
    	RobotMap.leftOne.set((forwardPower * speedMultiplier) + turningPower);
    	RobotMap.leftTwo.set((forwardPower * speedMultiplier) + turningPower);
    	RobotMap.leftThree.set((forwardPower * speedMultiplier) + turningPower);
    	
    	RobotMap.rightOne.set((-forwardPower * speedMultiplier) + turningPower);
    	RobotMap.rightTwo.set((-forwardPower * speedMultiplier) + turningPower);
    	RobotMap.rightThree.set((-forwardPower * speedMultiplier) + turningPower);
    	
    	if(shiftVal > 0.5) {
    		RobotMap.shifterRight.set(DoubleSolenoid.Value.kReverse);
    		RobotMap.shifterLeft.set(DoubleSolenoid.Value.kReverse);
    		SmartDashboard.putBoolean("gear", true);
    	} else if(shiftVal < -0.5){
    		RobotMap.shifterRight.set(DoubleSolenoid.Value.kForward);
    		RobotMap.shifterLeft.set(DoubleSolenoid.Value.kForward);
    		SmartDashboard.putBoolean("gear", false);
    	} else {
    		RobotMap.shifterRight.set(DoubleSolenoid.Value.kOff);
    		RobotMap.shifterLeft.set(DoubleSolenoid.Value.kOff);
    	}
    }
    
    public static void StegDrive(double forwardPower) {
    	currentYaw = RobotMap.navx.getYaw();
    	if(currentYaw < -2.5) {
    		RobotMap.leftOne.set(forwardPower + (currentYaw/100));
    		RobotMap.leftTwo.set(forwardPower + (currentYaw/100));
    		RobotMap.leftThree.set(forwardPower + (currentYaw/100));
    	
    		RobotMap.rightOne.set(-forwardPower + (currentYaw/100));
    		RobotMap.rightTwo.set(-forwardPower + (currentYaw/100));
    		RobotMap.rightThree.set(-forwardPower + (currentYaw/100));
    	} else if(currentYaw > 2.5){
    		RobotMap.leftOne.set(forwardPower - (currentYaw/100));
    		RobotMap.leftTwo.set(forwardPower - (currentYaw/100));
    		RobotMap.leftThree.set(forwardPower - (currentYaw/100));
    	
    		RobotMap.rightOne.set(-forwardPower - (currentYaw/100));
    		RobotMap.rightTwo.set(-forwardPower - (currentYaw/100));
    		RobotMap.rightThree.set(-forwardPower - (currentYaw/100));
    	} else {
    		RobotMap.leftOne.set(forwardPower);
    		RobotMap.leftTwo.set(forwardPower);
    		RobotMap.leftThree.set(forwardPower);
    	
    		RobotMap.rightOne.set(-forwardPower);
    		RobotMap.rightTwo.set(-forwardPower);
    		RobotMap.rightThree.set(-forwardPower);
    	}
    }
    
    public static void zeroYaw() {
    	RobotMap.navx.zeroYaw();
    }
    
    public static void RotateToAngle(int angle) {
    	currentYaw = RobotMap.navx.getYaw();

    	if(angle == 0) {
    		//get straight...er than STEG HAHAHAHa
        	if(currentYaw < -2.5) {
        		setAllMotorPower(currentYaw/100);
        	} else if(currentYaw > 2.5){
        		setAllMotorPower(-currentYaw/100);
        	} else {
        		stopDriving();
        	}
    	} else if(angle == 45) {
        	if(currentYaw < 42.5) {
        		setAllMotorPower(currentYaw/100);
        	} else if(currentYaw > 47.5){
        		setAllMotorPower(-currentYaw/100);
        	} else {
        		stopDriving();
        	}
    	} else if(angle == 90) {
        	if(currentYaw < 87.5) {
        		setAllMotorPower(currentYaw/100);
        	} else if(currentYaw > 92.5){
        		setAllMotorPower(-currentYaw/100);
        	} else {
        		stopDriving();
        	}
    	} else if(angle == 135) {
        	if(currentYaw < 132.5) {
        		setAllMotorPower(currentYaw/100);
        	} else if(currentYaw > 137.5){
        		setAllMotorPower(-currentYaw/100);
        	} else {
        		stopDriving();
        	}
    	} else if(angle == 180) {
        	if(currentYaw < 177.5) {
        		setAllMotorPower(currentYaw/100);
        	} else if(currentYaw < -177.5){
        		setAllMotorPower(-currentYaw/100);
        	} else {
        		stopDriving();
        	}
    	} else if(angle == 225) {
        	if(currentYaw > -132.5) {
        		setAllMotorPower(currentYaw/100);
        	} else if(currentYaw < -137.5){
        		setAllMotorPower(-currentYaw/100);
        	} else {
        		stopDriving();
        	}
    	} else if(angle == 270) {
        	if(currentYaw > -87.5) {
        		setAllMotorPower(currentYaw/100);
        	} else if(currentYaw < -92.5){
        		setAllMotorPower(-currentYaw/100);
        	} else {
        		stopDriving();
        	}
    	} else if(angle == 315) {
        	if(currentYaw > -42.5) {
        		setAllMotorPower(currentYaw/100);
        	} else if(currentYaw < -47.5){
        		setAllMotorPower(-currentYaw/100);
        	} else {
        		stopDriving();
        	}
    	} else {
    		stopDriving();
    	}
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