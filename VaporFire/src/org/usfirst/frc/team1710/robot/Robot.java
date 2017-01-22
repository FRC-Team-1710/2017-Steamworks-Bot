
package org.usfirst.frc.team1710.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	double turn, forward, shifter;
	public static int axisType, gear, compressorToggleCount;
	boolean activateSteg, photosynthesis;
   
    public void robotInit() {
    	RobotMap.leftOne = new Talon(1);
    	RobotMap.leftTwo = new Talon(2);
    	RobotMap.leftThree = new Talon(3);

    	RobotMap.rightOne = new Talon(4);
    	RobotMap.rightTwo = new Talon(5);
    	RobotMap.rightThree = new Talon(6);

        RobotMap.driveStick = new Joystick(0);
        RobotMap.shifterRight = new DoubleSolenoid(1,2);
        RobotMap.shifterLeft = new DoubleSolenoid(3,4);
        RobotMap.moreAir = new Compressor(0);
        
        compressorToggleCount = 1;

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
    	shifter = RobotMap.driveStick.getRawAxis(3);
    	photosynthesis = RobotMap.driveStick.getRawButton(2);
    	activateSteg = RobotMap.driveStick.getRawButton(0);
    	
    	if(activateSteg) {
    		Drive.StegDrive(forward);
    	} else {
    		Drive.arcadeDrive(forward, turn, shifter);
    	}
    	PneumaticsCrap.CheckPressure();
    	//turns on compressor
    	if(photosynthesis) {
    		PneumaticsCrap.ToggleCompressor(compressorToggleCount += 1);
    		Timer.delay(1);
    	}
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
