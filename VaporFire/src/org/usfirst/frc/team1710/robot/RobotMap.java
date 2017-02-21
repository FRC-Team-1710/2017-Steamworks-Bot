package org.usfirst.frc.team1710.robot;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Victor;


public class RobotMap {

		
	public static Victor pRM1, pLM1;
	
	public static Talon pClimber, pInjector;
	
	public static Joystick driveStick, mechStick;
	
	public static CANTalon RM1, RM2, RM3, LM1, LM2, LM3, Shooter1, Shooter2, Injector, Climber;
	
	public static DoubleSolenoid RPiston, LPiston, Shifter /*RShifter, LShifter*/;

	public static Compressor Compressor;
	
	public static AHRS navx;
	
	public static AnalogInput storedPressure, workingPressure, REncoder, LEncoder ;
	
	public static int axisType;
	
	public static boolean onShootSys, onTurbo, onCompress, climb, neutral, onRPiston, onLPiston, onSteg, zeroYaw, flipDir, trackLift;
	
	public static double TurnP, ForwardP, Multiplier, LPower, RPower, ShooterP, ClimbP, ClimbDown;
	
	public static float currentYaw;
}
