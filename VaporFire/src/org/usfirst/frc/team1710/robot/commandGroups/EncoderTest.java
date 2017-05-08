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
import trajectoryGeneration.FileGen;
import trajectoryGeneration.FollowPath;
import trajectoryGeneration.FollowPathFromFile;
import trajectoryGeneration.Waypoints;

/**
 *
 */
public class EncoderTest extends CommandGroup {

    public EncoderTest() {
    	addSequential(new ZeroYaw());
    	addSequential(new DriveToPosition(90, 0, 0.45));
    }
}
