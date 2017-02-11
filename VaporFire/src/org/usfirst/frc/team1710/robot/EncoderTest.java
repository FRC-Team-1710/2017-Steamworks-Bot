package org.usfirst.frc.team1710.robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class EncoderTest extends CommandGroup {

    public EncoderTest() {
    	addSequential(new ZeroYaw());
    	//low gear is 4 to 1 so if it was 1 to 1, 9.5 would move us ten feet but 9.5 * 4 = 38 so 38 "rotations" get us 10 ft
    	addSequential(new EncoderDrive(38, .5));
    }
}
