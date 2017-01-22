package org.usfirst.frc.team1710.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drive extends Subsystem {

	public static double speedMultiplier = 1;
	
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
    	
    	if(shiftVal > 0) {
    		RobotMap.shifterRight.set(DoubleSolenoid.Value.kReverse);
    		RobotMap.shifterLeft.set(DoubleSolenoid.Value.kReverse);
    		SmartDashboard.putBoolean("gear", true);
    	} else {
    		RobotMap.shifterRight.set(DoubleSolenoid.Value.kForward);
    		RobotMap.shifterLeft.set(DoubleSolenoid.Value.kForward);
    		SmartDashboard.putBoolean("gear", false);
    	}
    }
}