
package org.usfirst.frc.team1710.robot;
//other libraries
import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;
//wpi libraries
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	//double compressorCurrent;
	
    public void robotInit() {
    	//Set ports
        RobotMap.driveStick = new Joystick(0);
        RobotMap.mechStick = new Joystick(1);
    	RobotMap.LM1 = new CANTalon(1);
    	RobotMap.LM2 = new CANTalon(2);
    	RobotMap.LM3 = new CANTalon(3);
    	RobotMap.RM1 = new CANTalon(4);
    	RobotMap.RM2 = new CANTalon(5);
    	RobotMap.RM3 = new CANTalon(6);
    	RobotMap.Climber = new CANTalon(7);
    	RobotMap.Shooter1 = new CANTalon(8);
    	RobotMap.Shooter2 = new CANTalon(9);
        RobotMap.Shifter = new DoubleSolenoid(1,2);
        RobotMap.RPiston = new DoubleSolenoid(3,4);
        RobotMap.LPiston = new DoubleSolenoid(5,6);
        RobotMap.Compressor = new Compressor(0);
        RobotMap.navx = new AHRS(SPI.Port.kMXP);
        //Set defaults
        RobotMap.Compressor.setClosedLoopControl(false);
    	RobotMap.LM1.set(0);
    	RobotMap.LM2.set(0);
    	RobotMap.LM3.set(0);
    	RobotMap.RM1.set(0);
    	RobotMap.RM2.set(0);
    	RobotMap.RM3.set(0);
    	RobotMap.Shooter1.set(0);
    	RobotMap.Shooter2.set(0);
    	RobotMap.Climber.set(0);
    	RobotMap.Shifter.set(DoubleSolenoid.Value.kOff);
    	RobotMap.LPiston.set(DoubleSolenoid.Value.kOff);
    	RobotMap.RPiston.set(DoubleSolenoid.Value.kOff);
    	RobotMap.Compressor.setClosedLoopControl(false);
    }
    
    public void autonomousInit() {

    }
    public void autonomousPeriodic() {

    }
    public void teleopPeriodic() {
    	//Control
    	RobotMap.TurnP = RobotMap.driveStick.getRawAxis(RobotMap.axisType);
    	RobotMap.ForwardP = RobotMap.driveStick.getRawAxis(1);
    	RobotMap.Multiplier = RobotMap.driveStick.getRawAxis(3);
    	RobotMap.onTurbo = RobotMap.driveStick.getRawButton(1);
    	RobotMap.onSteg = RobotMap.driveStick.getRawButton(2);
    	RobotMap.onLPiston = RobotMap.driveStick.getRawButton(3);
    	RobotMap.onRPiston = RobotMap.driveStick.getRawButton(4);
    	RobotMap.onCompress = RobotMap.driveStick.getRawButton(5);
    	RobotMap.zeroYaw = RobotMap.driveStick.getRawButton(6);
    	RobotMap.neutral = RobotMap.driveStick.getRawButton(11);
    	//Drive
    	Drive.arcadeDrive(RobotMap.ForwardP, RobotMap.TurnP, RobotMap.Multiplier, RobotMap.navx.getYaw(), RobotMap.onTurbo, RobotMap.onSteg, RobotMap.neutral);
    	RobotMap.RM1.set(RobotMap.RPower);
    	RobotMap.RM2.set(RobotMap.RPower);
    	RobotMap.RM3.set(RobotMap.RPower);
    	RobotMap.LM1.set(RobotMap.LPower);
    	RobotMap.LM2.set(RobotMap.LPower);
    	RobotMap.LM3.set(RobotMap.LPower);
    	//Pneumatics
    	if(RobotMap.onLPiston == true){
    		Pneumatics.LPistonForward();
    	}
    	else{
    		Pneumatics.LPistonReverse();
    	}
    	if(RobotMap.onRPiston == true){
    		Pneumatics.RPistonForward();
    	}
    	else{
    		Pneumatics.RPistonReverse();
    	}
    	if(RobotMap.onCompress == true){
    		Pneumatics.startCompressor();
    	}
    	else{
    		Pneumatics.stopCompressor();
    	}
    	//Shooter
    }

    public void testPeriodic() {
    
    }
    
}
