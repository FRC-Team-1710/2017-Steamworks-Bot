package org.usfirst.frc.team1710.robot.commandGroups;

import org.usfirst.frc.team1710.robot.Drive;
import org.usfirst.frc.team1710.robot.Pneumatics;
import org.usfirst.frc.team1710.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateToAngleButWithEncoders extends Command {
	double anglePublic;
	double rightPosition, leftPosition;
    public RotateToAngleButWithEncoders(double angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	anglePublic  = angle * 82;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Pneumatics.shiftForward();
    	RobotMap.RM2.setEncPosition(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	rightPosition =(double) RobotMap.RM2.getEncPosition();
    	leftPosition =(double) RobotMap.LM3.getEncPosition();
    	System.out.println(0.5 - (Math.abs(rightPosition)/anglePublic));
    	if (Math.abs(rightPosition) < anglePublic) {
    		Drive.simpleArcade(1 - (Math.abs(rightPosition)/anglePublic),0, 1);
    		System.out.println("gogogo");
    		RobotMap.pRM1.set(RobotMap.RPower);
    		RobotMap.RM2.set(RobotMap.RPower);
    		RobotMap.RM3.set(RobotMap.RPower);
    		RobotMap.pLM1.set(RobotMap.LPower*1);
    		RobotMap.LM2.set(RobotMap.LPower*1);
    		RobotMap.LM3.set(RobotMap.LPower*1);
    	} else {
    		RobotMap.pRM1.set(0);
    		RobotMap.RM2.set(0);
    		RobotMap.RM3.set(0);
    		RobotMap.pLM1.set(0);
    		RobotMap.LM2.set(0);
    		RobotMap.LM3.set(0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
