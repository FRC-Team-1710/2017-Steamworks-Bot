package org.usfirst.frc.team1710.robot;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;

public class RobotMap {
		
	public static Joystick driveStick, mechStick;
	
	public static CANTalon RM1, RM2, RM3, LM1, LM2, LM3, Shooter1, Shooter2, Climber;
	
	public static DoubleSolenoid RPiston, LPiston, Shifter;

	public static Compressor Compressor;
	
	public static AHRS navx;
	
	//public static ****analogpressure storedPressure, workingPressure, REncoder, LEncoder;
	
	public static int axisType;
	
	public static boolean onTurbo, onCompress, neutral, onRPiston, onLPiston, onSteg, zeroYaw;
	
	public static double TurnP, ForwardP, Multiplier, LPower, RPower, ShooterP, ClimbP, MultAxis2;
	
	public static float currentYaw;
}
