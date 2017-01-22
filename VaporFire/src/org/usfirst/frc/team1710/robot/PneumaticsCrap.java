package org.usfirst.frc.team1710.robot;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PneumaticsCrap extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    //you're welcome isaac
    public static void ToggleCompressor(int count) {
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
    }
    
}

