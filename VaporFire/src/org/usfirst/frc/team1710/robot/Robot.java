
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
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    SendableChooser chooser;
	double turn, forward, multiplier, climbPower;
	int axisType, gear;
	boolean turboActivate;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
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
    	//Make a "drive" subsystem and put this code in it VVV
    	if(turboActivate == true){
    		//engage shift
    		//RobotMap.shifter.set(on);
        	RobotMap.move.arcadeDrive(forward*multiplier, turn*.3);
    		axisType = 2;
    		gear = 2;
    	}
    	else{
    		//disengage shift
        	RobotMap.move.arcadeDrive(forward*multiplier, turn*multiplier);
    		axisType = 1;
    		gear = 1;
    		
    	}
    	//***************************
    	climbPower = RobotMap.mechStick.getRawAxis(1);
        SmartDashboard.putNumber("Gear", gear);
        SmartDashboard.putNumber("Multiplier", multiplier);
        
    	
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
