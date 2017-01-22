
package org.usfirst.frc.team1710.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	double turn, forward;
	public static int axisType, gear;
	boolean turboActivate, shift;
   
    public void robotInit() {
    	RobotMap.leftOne = new Talon(1);
    	RobotMap.leftTwo = new Talon(2);
    	RobotMap.leftThree = new Talon(3);

    	RobotMap.rightOne = new Talon(4);
    	RobotMap.rightTwo = new Talon(5);
    	RobotMap.rightThree = new Talon(6);

        RobotMap.driveStick = new Joystick(0);
        RobotMap.shifterRight = new DoubleSolenoid(0,1);
        RobotMap.shifterLeft = new DoubleSolenoid(2,3);

    }
    
    public void autonomousInit() {

    }


    public void autonomousPeriodic() {

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	turn = RobotMap.driveStick.getRawAxis(2);
    	forward = RobotMap.driveStick.getRawAxis(1);
    	shift = RobotMap.driveStick.getRawButton(0);
    	Drive.arcadeDrive(forward, turn, shift);
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
