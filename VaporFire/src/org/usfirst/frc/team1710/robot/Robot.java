
package org.usfirst.frc.team1710.robot;
//other libraries
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;

import org.usfirst.frc.team1710.robot.Commands.MotionProfile;
import org.usfirst.frc.team1710.robot.commandGroups.CrossBaseline;
import org.usfirst.frc.team1710.robot.commandGroups.EncoderTest;
import org.usfirst.frc.team1710.robot.commandGroups.GearCenterShoot;
import org.usfirst.frc.team1710.robot.commandGroups.GearCenterShootRed;
import org.usfirst.frc.team1710.robot.commandGroups.GearPlaceCenter;
import org.usfirst.frc.team1710.robot.commandGroups.GearPlaceLeft;
import org.usfirst.frc.team1710.robot.commandGroups.GearPlaceLeftShoot;
import org.usfirst.frc.team1710.robot.commandGroups.GearPlaceRightShoot;
import org.usfirst.frc.team1710.robot.commandGroups.GearPlaceRight;
import org.usfirst.frc.team1710.robot.commandGroups.HopperShoot;
import org.usfirst.frc.team1710.robot.commandGroups.HopperShootRed;
import org.usfirst.frc.team1710.robot.commandGroups.JustShoot;
import org.usfirst.frc.team1710.robot.commandGroups.RightGearNoEncoder;
import org.usfirst.frc.team1710.robot.commandGroups.RotateToAngleTest;

import trajectoryGeneration.Waypoints;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.cscore.UsbCamera;
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
	static final double kD = 0.0;
	boolean PIDReady;
	public static Trajectory traj;
    public void robotInit() {
    	/*UsbCamera camera;
    	camera = CameraServer.getInstance().startAutomaticCapture();
    	camera.setResolution(320, 240);
    	camera.setFPS(20);
    	camera.setBrightness(25);
    	camera.setExposureManual(0);*/
    	Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 1.5, 2, 60.0);
    	traj = Pathfinder.generate(Waypoints.hopperShootRedSnek, config);
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
        autoChooser.addObject("Gear Left", new GearPlaceLeft());
        autoChooser.addObject("Gear Right", new GearPlaceRight());
        autoChooser.addDefault("Center Gear", new EncoderTest());
        autoChooser.addObject("Gear Left Shoot", new GearPlaceLeftShoot());
        autoChooser.addObject("Gear Right Shoot Red", new GearPlaceRightShoot());
        autoChooser.addObject("Gear Center Shoot Red", new GearCenterShootRed());
        autoChooser.addObject("Gear Center Shoot Blue", new GearCenterShoot());
        autoChooser.addObject("HopperShoot Blue", new HopperShoot());
        autoChooser.addObject("HopperShoot Red", new HopperShootRed());
        autoChooser.addObject("Cross Baseline", new CrossBaseline());
        autoChooser.addObject("Gear Right, time based", new RightGearNoEncoder());
        autoChooser.addObject("Just Shoot", new JustShoot());
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
    	autonomousCommand = (Command)  autoChooser.getSelected();	
    	autonomousCommand.start();
    }
    public void autonomousPeriodic() {
    	Scheduler.getInstance().run();
    }
    
    public void teleopInit() {
    }
    
    public void teleopPeriodic() {
     	//SmartDashboard.putData("Gear Sensor", RobotMap.gearSensor);
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
    		//Drive.StegDrive(RobotMap.ForwardP, RobotMap.navx.getYaw(), RobotMap.Multiplier);
    		System.out.println("running steg");
    	} else {
    		Drive.yawZeroed = false;
        	Drive.arcadeDrive(RobotMap.ForwardP, RobotMap.TurnP, RobotMap.Multiplier, RobotMap.navx.getYaw(), RobotMap.onSteg, RobotMap.onTurbo, RobotMap.neutral);
    	}
    	//Climber
    	if(RobotMap.mechStick.getRawButton(2) == true) {
    		RobotMap.pClimber.set(Math.abs(RobotMap.mechStick.getRawAxis(1)));
    	} else {
    		RobotMap.pClimber.set(0);
    	}
    	
    	if(RobotMap.mechStick.getRawButton(5) == true) {
    		BetterVision.trackBoiler(1);
    	} else {
    		BetterVision.upAndDown = false;
    		BetterVision.sideToSide = false;
    		BetterVision.count = 0;
    	}
    	
    	
    	//Pneumatics
    	Pneumatics.air();
    	//Shooter
    	if(RobotMap.onShootSys == true) {
    		Shooter.BestShooter();
    		SmartDashboard.putNumber("Velocity", RobotMap.Shooter1.getEncVelocity());
    	} else if(RobotMap.mechStick.getRawButton(7) == true) {
    		Shooter.ShootLow();
    	}
    	else {
    		Shooter.stopShooter();
    		Shooter.stopIndexer();
    		Shooter.firstInterval = false;
    		Shooter.secondInterval = false;
    		Shooter.shooterAtSpeed = false;
    		Shooter.runIndexer = false;
    	}
    	
    	//RobotMap.Injector.set(RobotMap.mechStick.getRawAxis(2));
    	angle = (RobotMap.REncoder.getVoltage() * 360/5);
    	/*angleIncrease = angleInitial - anglePrevious;
    	angle = anglePrevious + angleIncrease;
    	anglePrevious = angle;*/
    	SmartDashboard.putNumber("encoder1", RobotMap.LM2.getEncPosition());
    	SmartDashboard.putNumber("encoder2", RobotMap.LM3.getEncPosition());
    	SmartDashboard.putNumber("encoder3", RobotMap.RM2.getEncPosition());
    	SmartDashboard.putNumber("encoder4", RobotMap.RM3.getEncPosition());

    }

    public void testPeriodic() {
    
    }
    
}
