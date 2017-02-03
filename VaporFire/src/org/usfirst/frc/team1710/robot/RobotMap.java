package org.usfirst.frc.team1710.robot;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;

public class RobotMap {
		
	public static Joystick driveStick, mechStick;
	
	public static CANTalon climber, shooter, lift, leftOne, leftTwo, leftThree, rightOne, rightTwo, rightThree;
	
	public static DoubleSolenoid shifter;;

	public static Compressor moreAir;
	
	public static AHRS navx;
	
	public static int axisType;
}
