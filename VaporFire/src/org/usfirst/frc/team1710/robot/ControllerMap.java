package org.usfirst.frc.team1710.robot;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ControllerMap extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public static void runIsaacMode(){
    	RobotMap.TurnP = RobotMap.driveStick.getRawAxis(RobotMap.axisType);
    	RobotMap.ForwardP = RobotMap.driveStick.getRawAxis(1);
    	RobotMap.Multiplier = (-1*RobotMap.driveStick.getRawAxis(3)*.5+.5);
    	RobotMap.onTurbo = RobotMap.driveStick.getRawButton(1);
    	RobotMap.onSteg = RobotMap.driveStick.getRawButton(2);
    	RobotMap.onLPiston = RobotMap.driveStick.getRawButton(3);
    	RobotMap.onRPiston = RobotMap.driveStick.getRawButton(4);
    	RobotMap.neutral = RobotMap.driveStick.getRawButton(5);
    	RobotMap.zeroYaw = RobotMap.driveStick.getRawButton(6);
    	RobotMap.onCompress = RobotMap.driveStick.getRawButton(10);
    	//Mech Controls
    	RobotMap.ClimbP = (RobotMap.mechStick.getRawAxis(2)*.5 + RobotMap.mechStick.getRawAxis(3)*.5);
    	RobotMap.onShootSys = RobotMap.mechStick.getRawButton(1);
    }
    public static void runPennMode(){
    	RobotMap.TurnP = RobotMap.driveStick.getRawAxis(4) * .75;
    	RobotMap.ForwardP = RobotMap.driveStick.getRawAxis(1);
    	RobotMap.Multiplier = 0.75;
    	RobotMap.onTurbo = RobotMap.driveStick.getRawButton(5);
    	RobotMap.onSteg = RobotMap.driveStick.getRawButton(6);
    	RobotMap.neutral = RobotMap.driveStick.getRawButton(7);
    	RobotMap.zeroYaw = RobotMap.driveStick.getRawButton(8);
    	RobotMap.onCompress = RobotMap.driveStick.getRawButton(1);
    	RobotMap.trackLift = RobotMap.driveStick.getRawButton(3);
    	//Mech Controls
    	RobotMap.ClimbP = -RobotMap.driveStick.getRawAxis(2);
    	RobotMap.ClimbDown = RobotMap.driveStick.getRawAxis(3);
    	RobotMap.climb = RobotMap.driveStick.getRawButton(2);
    	RobotMap.onShootSys = RobotMap.mechStick.getRawButton(1);
    	RobotMap.onLPiston = RobotMap.mechStick.getRawButton(3);
    	RobotMap.onRPiston = RobotMap.mechStick.getRawButton(4);
    }
}

