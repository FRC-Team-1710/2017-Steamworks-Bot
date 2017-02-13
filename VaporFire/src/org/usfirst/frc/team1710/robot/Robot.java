
package org.usfirst.frc.team1710.robot;
//other libraries
import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
//wpi libraries
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	//double compressorCurrent;
	Command autonomousCommand;
	SendableChooser autoChooser;
	
	double angle, continuousAngle;
	
    public void robotInit() {
    	//Set ports
    	/*
    	 - indexer = 4
    	 - Drive = same
    	 - Shooters = 6 and 7
    	 */
        RobotMap.driveStick = new Joystick(0);
        RobotMap.mechStick = new Joystick(1);
    	RobotMap.LM1 = new CANTalon(8);
    	RobotMap.LM2 = new CANTalon(9);
    	RobotMap.LM3 = new CANTalon(10);
    	RobotMap.RM1 = new CANTalon(1);
    	RobotMap.RM2 = new CANTalon(2);
    	RobotMap.RM3 = new CANTalon(3);
    	RobotMap.Injector = new CANTalon(4);
    	RobotMap.Climber = new CANTalon(5);
    	RobotMap.Shooter1 = new CANTalon(6);
    	RobotMap.Shooter2 = new CANTalon(7);
    	//RobotMap.RShifter = new DoubleSolenoid(1,2);
        //RobotMap.LShifteer = new DoubleSolenoid(3,4);
        RobotMap.Shifter = new DoubleSolenoid(1,2);
        RobotMap.RPiston = new DoubleSolenoid(3,4);
        RobotMap.LPiston = new DoubleSolenoid(5,6);
        RobotMap.Compressor = new Compressor(0);
        RobotMap.navx = new AHRS(SPI.Port.kMXP);
        //RobotMap.storedPressure = new AnalogInput(4);
        //RobotMap.workingPressure = new AnalogInput(1);
        
        RobotMap.REncoder = new AnalogInput(1);
        
        //this encoder doesnt work, we dont need it anyways
        RobotMap.LEncoder = new AnalogInput(2);
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
    	//Auto stuff
    	autoChooser = new SendableChooser();
        autoChooser.addDefault("Encoder Test", new EncoderTest());
        SmartDashboard.putData("Autonomous Mode Chooser", autoChooser);
    }
    
    public void autonomousInit() {
    	autonomousCommand = (Command) autoChooser.getSelected();	
    	autonomousCommand.start();
    }
    public void autonomousPeriodic() {
    	Scheduler.getInstance().run();
    }
    public void teleopPeriodic() {
    	//Drive Controls
    	//ControllerMap.runIsaacMode();
    	ControllerMap.runPennMode();
    	//Drive
    	RobotMap.RM1.set(RobotMap.RPower*-1);
    	RobotMap.RM2.set(RobotMap.RPower*-1);
    	RobotMap.RM3.set(RobotMap.RPower*-1);
    	RobotMap.LM1.set(RobotMap.LPower);
    	RobotMap.LM2.set(RobotMap.LPower);
    	RobotMap.LM3.set(RobotMap.LPower);
    	
    	
    	angle = (RobotMap.REncoder.getVoltage() * 360)/5;
    	    	
    	if(RobotMap.onSteg == true) {
    		Drive.StegDrive(RobotMap.ForwardP, RobotMap.navx.getYaw(), RobotMap.Multiplier);
    		System.out.println("running steg");
    	} else {
        	Drive.arcadeDrive(RobotMap.ForwardP, RobotMap.TurnP, RobotMap.Multiplier, RobotMap.navx.getYaw(), RobotMap.onSteg, RobotMap.onTurbo, RobotMap.neutral);
    	}
    	//Climber
    	if(RobotMap.climb) {
    		climber.climbthatrope(RobotMap.ClimbP, RobotMap.ClimbDown);
    	} else {
    		climber.climbthatrope(0, 0);
    	}
    	//Pneumatics
    	//Pneumatics.air();
    	if (RobotMap.onCompress == true){
    		Pneumatics.startCompressor();
    	}
    	else{
    		Pneumatics.stopCompressor();   		
    	}
    	//Shooter
    	if(RobotMap.onShootSys == true){                                                                                                                                                                                                                                                                                                                                                                                             
    		Shooter.noEncoderRun();
    	}
    	else{
    		Shooter.shooteratspeed = false;
    		Shooter.stopShooter();
    		Shooter.stopInjector();
    	}
    }

    public void testPeriodic() {
    
    }
    
}
