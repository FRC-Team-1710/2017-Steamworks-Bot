package org.usfirst.frc.team1710.robot.commandGroups;

import org.usfirst.frc.team1710.robot.Commands.FollowTrajectory;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MotionProfileTest extends CommandGroup {

    public MotionProfileTest() {
    	addSequential(new FollowTrajectory());
    }
}
