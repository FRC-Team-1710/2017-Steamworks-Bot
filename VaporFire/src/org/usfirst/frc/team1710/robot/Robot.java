
package org.usfirst.frc.team1710.robot;
//other libraries
import org.usfirst.frc.team1710.robot.commandGroups.CrossBaseline;
import org.usfirst.frc.team1710.robot.commandGroups.EncoderTest;
import org.usfirst.frc.team1710.robot.commandGroups.GearPlaceCenter;
import org.usfirst.frc.team1710.robot.commandGroups.GearPlaceLeft;
import org.usfirst.frc.team1710.robot.commandGroups.GearPlaceLeftShoot;
import org.usfirst.frc.team1710.robot.commandGroups.GearPlaceRightShoot;
import org.usfirst.frc.team1710.robot.commandGroups.GearPlaceRight;
import org.usfirst.frc.team1710.robot.commandGroups.HopperShoot;
import org.usfirst.frc.team1710.robot.commandGroups.RotateToAngleTest;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
//wpi libraries
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;
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
	static final double kP = 0.005;
	static final double kI = 0.0025;
	static final double kD = 0.01;
	boolean PIDReady;
	
    public void robotInit() {
    	RobotMap.gearSensor = new DigitalInput(6);
   
    	RobotMap.directionMultiplier = 1;
    	motorMap.practiceBot();
    	//motorMap.competitionBot();
        RobotMap.driveStick = new Joystick(0);
        RobotMap.mechStick = new Joystick(1);
        if(motorMap.runningPracticeBot == true) {
        	RobotMap.Shifter = new DoubleSolenoid(4,2);
        	RobotMap.RPiston = new DoubleSolenoid(1,3);
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
        
        RobotMap.REncoder = new AnalogInput(0);
        
        //Set defaults
        RobotMap.Compressor.setClosedLoopControl(false);

    	//Auto stuff
    	autoChooser = new SendableChooser();
        autoChooser.addObject("Gear Left", new GearPlaceRight());
        autoChooser.addObject("Gear Right", new GearPlaceLeft());
        autoChooser.addDefault("Gear Center", new GearPlaceCenter());
        autoChooser.addObject("Gear Right Shoot", new GearPlaceLeftShoot());
        autoChooser.addObject("Gear Left Shoot", new GearPlaceRightShoot());
        autoChooser.addObject("HopperShoot", new HopperShoot());
        autoChooser.addObject("Cross Baseline", new CrossBaseline());
        SmartDashboard.putData("Meme Chooser", autoChooser);
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
     	SmartDashboard.putData("Gear Sensor", RobotMap.gearSensor);
    	//Drive Controls
    	//ControllerMap.runIsaacMode();
    	ControllerMap.runPennMode();
    	if(RobotMap.directionToggle == true){
    		RobotMap.directionToggleCount++;
    		Timer.delay(0.25);
    	}
    	if(RobotMap.directionToggleCount % 2 > 0){
    		RobotMap.directionMultiplier = 1;
    	}else{
        	RobotMap.directionMultiplier = -1;

    	}
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
    	
    	if(RobotMap.mechStick.getRawButton(5) == true) {
    		BetterVision.trackBoiler();
    	} else {
    		BetterVision.upAndDown = false;
    		BetterVision.sideToSide = false;
    	}
    	
    	//Pneumatics
    	Pneumatics.air();
    	//Shooter
    	if(RobotMap.onShootSys == true) {
    		BetterShooter.run();
    		if(PIDReady == false) {
    	    	RobotMap.Shooter2.changeControlMode(TalonControlMode.Follower);
    	    	RobotMap.Shooter2.set(RobotMap.Shooter1.getDeviceID());
    	        RobotMap.Shooter1.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
    	        RobotMap.Shooter1.configNominalOutputVoltage(+0.0f, -0.0f);
    	        RobotMap.Shooter1.configPeakOutputVoltage(+12.0f, -12.0f);
    	        RobotMap.Shooter1.reverseSensor(false);
    	    	RobotMap.Shooter1.setP(kP);
    	    	RobotMap.Shooter1.setI(kI);
    	    	RobotMap.Shooter1.setD(kD);
    	    	RobotMap.Shooter1.changeControlMode(TalonControlMode.Speed);
    	        PIDReady = true;
    		}
    		SmartDashboard.putNumber("Velocity", RobotMap.Shooter1.getEncVelocity());
    	} else {
    		PIDReady = false;
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
