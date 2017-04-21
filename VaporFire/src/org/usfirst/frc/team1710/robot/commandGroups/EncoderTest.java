package org.usfirst.frc.team1710.robot.commandGroups;

import org.usfirst.frc.team1710.robot.Profiles;
import org.usfirst.frc.team1710.robot.Commands.DriveToPosition;
import org.usfirst.frc.team1710.robot.Commands.EncoderDrive;
import org.usfirst.frc.team1710.robot.Commands.LETLITRIP;
import org.usfirst.frc.team1710.robot.Commands.MotionProfile;
import org.usfirst.frc.team1710.robot.Commands.RotateToAngleButWithEncoders;
import org.usfirst.frc.team1710.robot.Commands.RotatetoAngle;
import org.usfirst.frc.team1710.robot.Commands.RunShooterAuto;
import org.usfirst.frc.team1710.robot.Commands.ZeroYaw;
import org.usfirst.frc.team1710.robot.Commands.followcurve;

import edu.wpi.first.wpilibj.command.CommandGroup;
import trajectoryGeneration.FollowPath;
import trajectoryGeneration.FollowPathFromFile;
import trajectoryGeneration.Waypoints;

/**
 *
 */
public class EncoderTest extends CommandGroup {

    public EncoderTest() {
    	//low gear is 4 to 1 so if it was 1 to 1, 9.5 would move us ten feet but 9.5 * 4 = 38 so 38 "rotations" get us 10 ft
    	//@param rotations
    	//@param velocity
    	//addSequential(new ZeroYaw());
    	//radius, angle to stop at
    	/*addSequential(new ZeroYaw());
    	addSequential(new followcurve(.0015, 40));
    	addSequential(new ZeroYaw());
    	addSequential(new DriveToPosition(20, 0));
    	addSequential(new RotateToAngleButWithEncoders(true, 2500, -0.5));*/
    	//addSequential(new MotionProfile(Waypoints.testPoints));
      	addSequential(new ZeroYaw());
    	addSequential(new FollowPath(Waypoints.gearRightRed));
    }
}
