package org.usfirst.frc.team1710.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;




public class motorMap {

	public static boolean runningPracticeBot;
	public static boolean runningCompetitionBot;
	
	
	
	public static void practiceBot(){
		
		RobotMap.pRM1 = new Victor(2);
		RobotMap.RM2 = new CANTalon(7);
		RobotMap.RM3 = new CANTalon(5);
		
		RobotMap.pLM1 = new Victor(3);
		RobotMap.LM2 = new CANTalon(9);
		RobotMap.LM3 = new CANTalon(10);
		
		RobotMap.pClimber = new Talon(0);
		RobotMap.pInjector = new Talon(1);
		
		
		RobotMap.Shooter1 = new CANTalon(6);
		RobotMap.Shooter2 = new CANTalon(8);
		runningPracticeBot = true;
		runningCompetitionBot = false;
		
	}
	public static void competitionBot(){
		runningPracticeBot = false;
		runningCompetitionBot = true;
		
	}
	
	
}
