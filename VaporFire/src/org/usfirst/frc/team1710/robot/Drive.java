package org.usfirst.frc.team1710.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drive extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public static void arcadeDrive(double forwardPower, double turningPower, boolean shift){
    	RobotMap.leftOne.set(forwardPower + turningPower);
    	RobotMap.leftTwo.set(forwardPower + turningPower);
    	RobotMap.leftThree.set(forwardPower + turningPower);
    	SmartDashboard.putNumber("left Power", (forwardPower + turningPower));
    	RobotMap.rightOne.set(forwardPower - turningPower);
    	RobotMap.rightTwo.set(forwardPower - turningPower);
    	RobotMap.rightThree.set(forwardPower - turningPower);
    	SmartDashboard.putNumber("right Power", (-forwardPower - turningPower));
    	
    	if(shift) {
    		RobotMap.shifterRight.set(DoubleSolenoid.Value.kForward);
    		RobotMap.shifterLeft.set(DoubleSolenoid.Value.kForward);
    	} else {
    		RobotMap.shifterRight.set(DoubleSolenoid.Value.kReverse);
    		RobotMap.shifterLeft.set(DoubleSolenoid.Value.kReverse);
    	}
    }
}