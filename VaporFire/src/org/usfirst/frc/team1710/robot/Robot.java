
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
import edu.wpi.first.wpilibj.Ultrasonic;
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
    	motorMap.practiceBot();
    	//motorMap.competitionBot();
        RobotMap.driveStick = new Joystick(0);
        RobotMap.mechStick = new Joystick(1);

        RobotMap.Shifter = new DoubleSolenoid(1,2);
        RobotMap.RPiston = new DoubleSolenoid(3,4);
        RobotMap.LPiston = new DoubleSolenoid(5,6);
        RobotMap.Compressor = new Compressor(0);
        RobotMap.navx = new AHRS(SPI.Port.kMXP);
        //RobotMap.storedPressure = new AnalogInput(4);
        //RobotMap.workingPressure = new AnalogInput(1);
        
        RobotMap.REncoder = new AnalogInput(1);
        
        //Set defaults
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
    	if(motorMap.runningCompetitionBot == true){
    		RobotMap.RM1.set(RobotMap.RPower*-1);
    		RobotMap.RM2.set(RobotMap.RPower*-1);
    		RobotMap.RM3.set(RobotMap.RPower*-1);
    		RobotMap.LM1.set(RobotMap.LPower);
    		RobotMap.LM2.set(RobotMap.LPower);
    		RobotMap.LM3.set(RobotMap.LPower);
    	}else{
    	 	RobotMap.pRM1.set(RobotMap.RPower*-1);
        	RobotMap.RM2.set(RobotMap.RPower*-1);
        	RobotMap.RM3.set(RobotMap.RPower*-1);
        	RobotMap.pLM1.set(RobotMap.LPower);
        	RobotMap.LM2.set(RobotMap.LPower);
        	RobotMap.LM3.set(RobotMap.LPower);
    	}
    	angle = (RobotMap.REncoder.getVoltage() * 360)/5;
    	    	
    	if(RobotMap.onSteg == true) {
    		Drive.StegDrive(RobotMap.ForwardP, RobotMap.navx.getYaw(), RobotMap.Multiplier);
    		System.out.println("running steg");
    	} else {
    		Drive.yawZeroed = false;
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
    	else if(RobotMap.trackLift == true) {
    		BetterVision.trackGear((float) SmartDashboard.getNumber("ANGLE_TO_TURN"), SmartDashboard.getBoolean("IS_ALIGNED"));
    	}
    	else{
    		Pneumatics.stopCompressor();   		
    	}
    	//Shooter
    	if(RobotMap.onShootSys == true) {
    		Shooter.runSystemNoPID();
    	} else {
    		Shooter.stopShooter();
    	}
    }

    public void testPeriodic() {
    
    }
    
}
