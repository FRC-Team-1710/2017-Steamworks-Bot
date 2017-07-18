package org.usfirst.frc.team1710.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1710.robot.Drive;
import org.usfirst.frc.team1710.robot.RobotMap;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class RotatetoAngle extends Command {
	float turningDegreePublic;
	double initialTurningSpeed = .5;
	float currentYaw;
	boolean done;
    public RotatetoAngle(float d) {
    	turningDegreePublic = d;
    }
    protected void initialize() {
    }
    protected void execute() {
    	if (turningDegreePublic > 0){
    		currentYaw = RobotMap.navx.getYaw();
    		// was 5
    		if (currentYaw > turningDegreePublic - 1 && currentYaw < turningDegreePublic + 1  || (currentYaw / turningDegreePublic) > 0.85){
    			Drive.simpleArcade(0, 0, 0);
    			done = true;
    		} else if(currentYaw > turningDegreePublic) {
    			Drive.simpleArcade(0, -0.5, 1);
    		} else if(currentYaw < turningDegreePublic) {
    			Drive.simpleArcade(0, 0.5, 1);
    		}
    	} else {
        	currentYaw = RobotMap.navx.getYaw();
        	System.out.println(currentYaw);
        	//wAS 5
    		if (currentYaw > turningDegreePublic - 1 && currentYaw < turningDegreePublic + 1 || (currentYaw / turningDegreePublic) > 0.85){
    			Drive.simpleArcade(0, 0, 0);
        		done = true;
        	} else if(currentYaw > turningDegreePublic) {
    			Drive.simpleArcade(0, -0.5, 1);
    		} else if(currentYaw < turningDegreePublic) {
    			Drive.simpleArcade(0, 0.5, 1);
    		}
    	}
		RobotMap.LM1.set(RobotMap.LPower);
		RobotMap.LM2.set(RobotMap.LPower);
		RobotMap.LM3.set(RobotMap.LPower);

		RobotMap.RM1.set(RobotMap.RPower * -1);
		RobotMap.RM2.set(RobotMap.RPower * -1);
		RobotMap.RM3.set(RobotMap.RPower * -1);
    }
    protected boolean isFinished() {
        return done;
    }
    protected void end() {
    	done = false;
    }
    protected void interrupted() {
    }
}