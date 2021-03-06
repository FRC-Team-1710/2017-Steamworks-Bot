package org.usfirst.frc.team1710.robot.Commands;

import org.usfirst.frc.team1710.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ZeroYaw extends Command {

	boolean done;
    public ZeroYaw() {

    }

    protected void initialize() {
    	done = false;
    }

    protected void execute() {
    	RobotMap.RM2.setEncPosition(0);
    	RobotMap.navx.zeroYaw();
    	Timer.delay(0.4);
    	done = true;
    }

    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
