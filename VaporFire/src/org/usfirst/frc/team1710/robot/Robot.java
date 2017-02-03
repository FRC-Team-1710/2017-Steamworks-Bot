
package org.usfirst.frc.team1710.robot;

import com.ctre.CANTalon;
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
	public static double speedMultiplier;
	double compressorCurrent;
   
    public void robotInit() {
    	RobotMap.leftOne = new CANTalon(1);
    	RobotMap.leftTwo = new CANTalon(2);
    	RobotMap.leftThree = new CANTalon(3);

    	RobotMap.rightOne = new CANTalon(8);
    	RobotMap.rightTwo = new CANTalon(9);
    	RobotMap.rightThree = new CANTalon(10);

        RobotMap.driveStick = new Joystick(0);
        RobotMap.shifter = new DoubleSolenoid(0,1);
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
    	turn = RobotMap.driveStick.getRawAxis(2);
    	forward = RobotMap.driveStick.getRawAxis(1);
    	shifter = RobotMap.driveStick.getRawButton(1);
    	photosynthesis = RobotMap.driveStick.getRawButton(3);
    	activateSteg = RobotMap.driveStick.getRawButton(4);
    	zeroYaw = RobotMap.driveStick.getRawButton(6);
    	turboMode = RobotMap.driveStick.getRawButton(2);
    	speedMultiplier = Math.abs(RobotMap.driveStick.getRawAxis(3));
    	compressorCurrent = RobotMap.moreAir.getCompressorCurrent();
    	if(turboMode || activateSteg) {
    		Drive.StegDrive(forward, speedMultiplier);
    	} else {
    		Drive.arcadeDrive(forward, turn, shifter, speedMultiplier);
    	}
    	//checks pressure switch to see if it's low and prints result to dashboard
    	PneumaticsCrap.CheckPressure();
    	SmartDashboard.putNumber("compressor current", compressorCurrent);
    	//turns on compressor
    	if(photosynthesis) {
    		PneumaticsCrap.ToggleCompressor((compressorToggleCount += 1));
    		Timer.delay(0.5);
    	} else if(zeroYaw) {
    		Drive.zeroYaw();
    	}
    }

    public void testPeriodic() {
    
    }
    
}
