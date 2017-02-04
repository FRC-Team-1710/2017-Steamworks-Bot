
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
	boolean activateSteg, startCompressor, zeroYaw, turboMode, shifter, piston1Start, piston2Start;
   
    public void robotInit() {
    	RobotMap.leftOne = new Talon(1);
    	RobotMap.leftTwo = new Talon(2);
    	RobotMap.leftThree = new Talon(3);

    	RobotMap.rightOne = new Talon(4);
    	RobotMap.rightTwo = new Talon(5);
    	RobotMap.rightThree = new Talon(6);

        RobotMap.driveStick = new Joystick(0);
        RobotMap.shifter = new DoubleSolenoid(1,2);
        RobotMap.piston1 = new DoubleSolenoid(3,4);
        RobotMap.piston2 = new DoubleSolenoid(5,6);
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
    	startCompressor = RobotMap.driveStick.getRawButton(3);
    	//activateSteg = RobotMap.driveStick.getRawButton(4);
    	//zeroYaw = RobotMap.driveStick.getRawButton(6);
    	turboMode = RobotMap.driveStick.getRawButton(1);
    	piston1Start = RobotMap.driveStick.getRawButton(5);
    	piston2Start = RobotMap.driveStick.getRawButton(6);
    
    	//if(activateSteg) {
    		//Drive.StegDrive(forward);
    	//} else {
    	Drive.arcadeDrive(forward, turn, turboMode);
    	//}
    	//checks pressure switch to see if it's low and prints result to dashboard
    	//PneumaticsCrap.CheckPressure();
    	//turns on compressor
    	if (piston1Start == true){
    		RobotMap.piston1.set(DoubleSolenoid.Value.kForward);
    		
    	}
    	else{
    		RobotMap.piston1.set(DoubleSolenoid.Value.kReverse);
    	}
    	if (piston2Start == true){
    		RobotMap.piston2.set(DoubleSolenoid.Value.kForward);
    	}
    	else{
    		RobotMap.piston2.set(DoubleSolenoid.Value.kReverse);
    	}
    	if (startCompressor == true){
    		RobotMap.moreAir.setClosedLoopControl(true);
    		SmartDashboard.putBoolean("Compressor On?", true);
    	}
    	else{
    		RobotMap.moreAir.setClosedLoopControl(false);
    		SmartDashboard.putBoolean("Compressor On?", false);
    	}
    	if(zeroYaw) {
    		Drive.zeroYaw();
    	}
    }

    public void testPeriodic() {
    
    }
    
}
