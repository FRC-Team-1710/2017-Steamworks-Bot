
package org.usfirst.frc.team1710.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
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
	double turn, forward;
	public static int gear, compressorToggleCount;
	boolean activateSteg, photosynthesis, zeroYaw, turboMode, shifter;
   
    public void robotInit() {
    	RobotMap.leftOne = new Talon(1);
    	RobotMap.leftTwo = new Talon(2);
    	RobotMap.leftThree = new Talon(3);

    	RobotMap.rightOne = new Talon(4);
    	RobotMap.rightTwo = new Talon(5);
    	RobotMap.rightThree = new Talon(6);

        RobotMap.driveStick = new Joystick(0);
        RobotMap.shifterRight = new DoubleSolenoid(2,1);
        RobotMap.shifterLeft = new DoubleSolenoid(3,4);
        RobotMap.moreAir = new Compressor(0);
        
        compressorToggleCount = 1;
        
        RobotMap.navx = new AHRS(SPI.Port.kMXP);
        
        RobotMap.moreAir.setClosedLoopControl(false);
    }
    
    public void autonomousInit() {

    }


    public void autonomousPeriodic() {

    }

    public void teleopPeriodic() {
    	turn = RobotMap.driveStick.getRawAxis(RobotMap.axisType);
    	forward = RobotMap.driveStick.getRawAxis(1);
    	shifter = RobotMap.driveStick.getRawButton(3);
    	photosynthesis = RobotMap.driveStick.getRawButton(2);
    	activateSteg = RobotMap.driveStick.getRawButton(3);
    	zeroYaw = RobotMap.driveStick.getRawButton(6);
    	turboMode = RobotMap.driveStick.getRawButton(1);
    	
    	if(activateSteg) {
    		Drive.StegDrive(forward);
    	} else {
    		Drive.arcadeDrive(forward, turn, shifter, turboMode);
    	}
    	//checks pressure switch to see if it's low and prints result to dashboard
    	PneumaticsCrap.CheckPressure();
    	//turns on compressor
    	if(photosynthesis) {
    		PneumaticsCrap.ToggleCompressor((compressorToggleCount += 1));
    		Timer.delay(1);
    	} else if(zeroYaw) {
    		Drive.zeroYaw();
    	}
    }

    public void testPeriodic() {
    
    }
    
}
