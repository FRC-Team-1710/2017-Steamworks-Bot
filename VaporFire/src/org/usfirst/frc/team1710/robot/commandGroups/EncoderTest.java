package org.usfirst.frc.team1710.robot.commandGroups;

import org.usfirst.frc.team1710.robot.Profiles;
import org.usfirst.frc.team1710.robot.Commands.EncoderDrive;
import org.usfirst.frc.team1710.robot.Commands.MotionProfile;
import org.usfirst.frc.team1710.robot.Commands.ZeroYaw;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class EncoderTest extends CommandGroup {

    public EncoderTest() {
    	addSequential(new ZeroYaw());
    	//low gear is 4 to 1 so if it was 1 to 1, 9.5 would move us ten feet but 9.5 * 4 = 38 so 38 "rotations" get us 10 ft
    	//@param rotations
    	//@param velocity
    	addSequential(new MotionProfile(Profiles.rightProfile, Profiles.leftProfile, 4));
    }
}
