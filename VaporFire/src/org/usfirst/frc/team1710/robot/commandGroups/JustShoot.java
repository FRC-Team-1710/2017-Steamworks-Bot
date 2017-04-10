package org.usfirst.frc.team1710.robot.commandGroups;

import org.usfirst.frc.team1710.robot.Commands.DriveToPosition;
import org.usfirst.frc.team1710.robot.Commands.RunShooterAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class JustShoot extends CommandGroup {

    public JustShoot() {
    	addSequential(new DriveToPosition(100));
    	addSequential(new RunShooterAuto(4000, 2));
    }
}
