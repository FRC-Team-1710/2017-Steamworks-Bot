package org.usfirst.frc.team1710.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;

public class RobotMap {
		
	public static Joystick driveStick, mechStick;
	
	public static Talon climber, shooter, lift, leftOne, leftTwo, leftThree, rightOne, rightTwo, rightThree;
	
	public static DoubleSolenoid shifterRight, shifterLeft;

	public static Compressor moreAir;
}
