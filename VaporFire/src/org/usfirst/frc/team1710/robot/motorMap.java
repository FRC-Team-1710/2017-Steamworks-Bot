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
		RobotMap.LM2 = new CANTalon(8);
		RobotMap.LM3 = new CANTalon(10);
		
		RobotMap.pClimber = new Talon(0);
		RobotMap.pInjector = new Talon(1);
		
		
		RobotMap.Shooter1 = new CANTalon(6);
		RobotMap.Shooter2 = new CANTalon(9);
		runningPracticeBot = true;
		runningCompetitionBot = false;
	}
	public static void competitionBot(){
		
    	RobotMap.LM1 = new CANTalon(16);
    	RobotMap.LM2 = new CANTalon(4);
    	RobotMap.LM3 = new CANTalon(3);
    	RobotMap.RM1 = new CANTalon(15);
    	RobotMap.RM2 = new CANTalon(14);
    	RobotMap.RM3 = new CANTalon(13);
    	RobotMap.Injector = new CANTalon(1);
    	RobotMap.Climber = new CANTalon(2);
    	RobotMap.Shooter1 = new CANTalon(11);
    	RobotMap.Shooter2 = new CANTalon(12);
    	
		runningPracticeBot = false;
		runningCompetitionBot = true;
	}
	
	
}
