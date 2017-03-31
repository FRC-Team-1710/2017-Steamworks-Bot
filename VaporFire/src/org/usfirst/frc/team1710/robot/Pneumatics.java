package org.usfirst.frc.team1710.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Pneumatics extends Subsystem {
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	RobotMap.Shifter.set(DoubleSolenoid.Value.kOff);
    	RobotMap.LPiston.set(DoubleSolenoid.Value.kOff);
    	RobotMap.RPiston.set(DoubleSolenoid.Value.kOff);
    }
    public static void air(){
    	if(RobotMap.onLPiston == true){
    		Pneumatics.LPistonForward();
    	}
    	else if(RobotMap.onLPiston == false){
    		Pneumatics.LPistonReverse();
    	}
    	else{
    		Pneumatics.neutralPistons();
    	}
    	//****
    	if(RobotMap.onRPiston == true){
    		Pneumatics.RPistonReverse();
    	}
    	else if (RobotMap.onRPiston == false){
    		Pneumatics.RPistonForward();
    	}
    	else{
    		Pneumatics.neutralPistons();
    	}
    	//****
    	if(RobotMap.onCompress == true){
    		Pneumatics.startCompressor();
    	}
    	else{
    		Pneumatics.stopCompressor();
    	}
    }
    
    //hella useful functions
    public static void shiftForward() {
    	RobotMap.Shifter.set(DoubleSolenoid.Value.kForward);
    	//RobotMap.RShifter.set(DoubleSolenoid.Value.kForward);
    	//RobotMap.LShifter.set(DoubleSolenoid.Value.kForward);
    }
    public static void shiftReverse(){
    	RobotMap.Shifter.set(DoubleSolenoid.Value.kReverse);
    	//RobotMap.RShifter.set(DoubleSolenoid.Value.kReverse);
    	//RobotMap.LShifter.set(DoubleSolenoid.Value.kReverse);
    }
    public static void shiftNeutral(){
    	RobotMap.Shifter.set(DoubleSolenoid.Value.kOff);
    	//RobotMap.RShifter.set(DoubleSolenoid.Value.kOff);
    	//RobotMap.LShifter.set(DoubleSolenoid.Value.kOff);
    }
    public static void RPistonForward(){
    	RobotMap.RPiston.set(DoubleSolenoid.Value.kForward);
    }
    public static void RPistonReverse(){
    	RobotMap.RPiston.set(DoubleSolenoid.Value.kReverse);
    }
    public static void LPistonForward(){
    	RobotMap.LPiston.set(DoubleSolenoid.Value.kForward);
    }
    public static void LPistonReverse(){
    	RobotMap.LPiston.set(DoubleSolenoid.Value.kReverse);
    }
    public static void neutralPistons(){
    	RobotMap.LPiston.set(DoubleSolenoid.Value.kOff);
    	RobotMap.RPiston.set(DoubleSolenoid.Value.kOff);
    }
    public static void startCompressor(){
    	RobotMap.Compressor.setClosedLoopControl(true);
    	SmartDashboard.putBoolean("Compressor On?", true);
    }
    public static void stopCompressor(){
    	RobotMap.Compressor.setClosedLoopControl(false);
    	SmartDashboard.putBoolean("Compressor On?", false);
    }
    
    //*************************
    //other stuff
    
    /*  public static void ToggleCompressor(int count) {
    	if(count%2 == 0) {
    		RobotMap.moreAir.setClosedLoopControl(true);
    		SmartDashboard.putBoolean("Compressor On?", true);
    	} else {
    		RobotMap.moreAir.setClosedLoopControl(false);
    		SmartDashboard.putBoolean("Compressor On?", false);
    	}
    }
    
    public static void CheckPressure() {
    	SmartDashboard.putBoolean("Pressure Low?", RobotMap.moreAir.getPressureSwitchValue());
    } */
    
    
}

