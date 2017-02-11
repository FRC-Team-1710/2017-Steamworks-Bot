package org.usfirst.frc.team1710.robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class EncoderTest extends CommandGroup {

    public EncoderTest() {
    	addSequential(new ZeroYaw());
    	addSequential(new EncoderDrive(9.5, .5));
    }
}
