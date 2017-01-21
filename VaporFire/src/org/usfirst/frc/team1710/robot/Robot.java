
package org.usfirst.frc.team1710.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    SendableChooser chooser;
	double turn, forward, multiplier, climbPower;
	public static int axisType, gear;
	boolean turboActivate;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	RobotMap.navx = new AHRS(SPI.Port.kMXP);
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
        RobotMap.driveStick = new Joystick(0);
        RobotMap.mechStick = new Joystick(1);
        RobotMap.move = new RobotDrive(0,1,2,3);
        RobotMap.climber = new Talon(4);
        RobotMap.shooter = new Talon(5);
        CameraServer camera = CameraServer.getInstance();
        //camera.setQuality(50);
        //camera.startAutomaticCapture("Front Camera");
        turboActivate = false;
        axisType = 1;
        RobotMap.shifter = new DoubleSolenoid(1,2);
        
    }
    
    public void autonomousInit() {
    	autoSelected = (String) chooser.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
    }


    public void autonomousPeriodic() {
    	switch(autoSelected) {
    	case customAuto:
        //Put custom auto code here   
            break;
    	case defaultAuto:
    	default:
    	//Put default auto code here
            break;
    	}
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	turn = RobotMap.driveStick.getRawAxis(axisType);
    	forward = RobotMap.driveStick.getRawAxis(0);
    	multiplier = RobotMap.driveStick.getRawAxis(3)*.5+.5;
    	turboActivate = RobotMap.driveStick.getRawButton(1);
    	Drive.arcadeDrive(forward, turn, turboActivate);
    	climbPower = RobotMap.mechStick.getRawAxis(1);
        SmartDashboard.putNumber("Gear", gear);
        SmartDashboard.putNumber("Multiplier", multiplier);
        SmartDashboard.putNumber("Robot Speed", RobotMap.navx.getVelocityY());
    	
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
