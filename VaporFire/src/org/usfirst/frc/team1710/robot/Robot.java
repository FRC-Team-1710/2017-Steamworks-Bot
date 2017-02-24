
package org.usfirst.frc.team1710.robot;
//other libraries
import org.usfirst.frc.team1710.robot.commandGroups.EncoderTest;

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
	
	double angle, angleIncrease, anglePrevious, angleInitial, continuousAngle;
	
    public void robotInit() {
    	motorMap.practiceBot();
    	//motorMap.competitionBot();
        RobotMap.driveStick = new Joystick(0);
        RobotMap.mechStick = new Joystick(1);
        if(motorMap.runningPracticeBot == true) {
        	RobotMap.Shifter = new DoubleSolenoid(4,2);
        	RobotMap.RPiston = new DoubleSolenoid(3,7);
        	RobotMap.LPiston = new DoubleSolenoid(5,6);
        	RobotMap.Compressor = new Compressor(0);
        	RobotMap.navx = new AHRS(SPI.Port.kMXP);
        } else {
        	RobotMap.Shifter = new DoubleSolenoid(1,2);
        	RobotMap.RPiston = new DoubleSolenoid(3,4);
        	RobotMap.LPiston = new DoubleSolenoid(5,6);
        	RobotMap.Compressor = new Compressor(0);
        	RobotMap.navx = new AHRS(SPI.Port.kMXP);
        }
        //RobotMap.storedPressure = new AnalogInput(4);
        //RobotMap.workingPressure = new AnalogInput(1);
        
        RobotMap.REncoder = new AnalogInput(3);
        
        //Set defaults
        RobotMap.Compressor.setClosedLoopControl(false);
    	
    	//Auto stuff
    	autoChooser = new SendableChooser();
        autoChooser.addDefault("Encoder Test", new EncoderTest());
        SmartDashboard.putData("Autonomous Mode Chooser", autoChooser);
        RobotMap.RPiston.set(DoubleSolenoid.Value.kOff);
        RobotMap.LPiston.set(DoubleSolenoid.Value.kOff);
        RobotMap.Shifter.set(DoubleSolenoid.Value.kOff);
        anglePrevious = 0;
        angle = 0;
        angleIncrease = 0;
        angleInitial = 0;
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
    		RobotMap.RM1.set(RobotMap.RPower);
    		RobotMap.RM2.set(RobotMap.RPower);
    		RobotMap.RM3.set(RobotMap.RPower);
    		RobotMap.LM1.set(RobotMap.LPower*-1);
    		RobotMap.LM2.set(RobotMap.LPower*-1);
    		RobotMap.LM3.set(RobotMap.LPower*-1);
    	}else{
    	 	RobotMap.pRM1.set(RobotMap.RPower);
        	RobotMap.RM2.set(RobotMap.RPower);
        	RobotMap.RM3.set(RobotMap.RPower);
        	RobotMap.pLM1.set(RobotMap.LPower * -1);
        	RobotMap.LM2.set(RobotMap.LPower * -1);
        	RobotMap.LM3.set(RobotMap.LPower * -1);
    	}
    	    	
    	if(RobotMap.onSteg == true) {
    		Drive.StegDrive(RobotMap.ForwardP, RobotMap.navx.getYaw(), RobotMap.Multiplier);
    		System.out.println("running steg");
    	} else {
    		Drive.yawZeroed = false;
        	Drive.arcadeDrive(RobotMap.ForwardP, RobotMap.TurnP, RobotMap.Multiplier, RobotMap.navx.getYaw(), RobotMap.onSteg, RobotMap.onTurbo, RobotMap.neutral);
    	}
    	//Climber
    	if(RobotMap.mechStick.getRawButton(2) == true) {
    		RobotMap.pClimber.set(RobotMap.mechStick.getRawAxis(1));
    	} else {
    		RobotMap.pClimber.set(0);
    	}
    	//Pneumatics
    	//Pneumatics.air();
    	if (RobotMap.onCompress == true){
    		Pneumatics.startCompressor();
    	}
    	else if(RobotMap.trackLift == true) {
    		BetterVision.trackGear((float) SmartDashboard.getNumber("ANGLE_TO_TURN"), SmartDashboard.getBoolean("IS_ALIGNED"));
    	} else if(RobotMap.onTurbo == true) {
    		Pneumatics.shiftForward();
    	} else if(RobotMap.onRPiston == true) {
    		RobotMap.RPiston.set(DoubleSolenoid.Value.kReverse);
    	}
    	else{
    		Pneumatics.stopCompressor();  
    		Pneumatics.shiftReverse();
    		RobotMap.RPiston.set(DoubleSolenoid.Value.kForward);
    	}
    	//Shooter
    	if(RobotMap.onShootSys == true) {
    		Shooter.runSystemNoPID();
    		//Shooter.demonstrationModeLowPower();
    		SmartDashboard.putNumber("Velocity", RobotMap.Shooter1.getEncVelocity());
    	} else {
    		Shooter.stopShooter();
    		Shooter.stopIndexer();
    	}
    	
    	//RobotMap.Injector.set(RobotMap.mechStick.getRawAxis(2));
    	angle = (RobotMap.REncoder.getVoltage() * 360/5);
    	/*angleIncrease = angleInitial - anglePrevious;
    	angle = anglePrevious + angleIncrease;
    	anglePrevious = angle;*/
    	SmartDashboard.putNumber("encoder", angle);
    }

    public void testPeriodic() {
    
    }
    
}
