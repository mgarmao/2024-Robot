// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
//test
// Autonomous program created with help from Team 303

package frc.robot.commands.Autonomous;

import java.io.File;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.swervedrive.drivebase.AbsoluteDrive;
import frc.robot.commands.swervedrive.drivebase.AbsoluteDriveAdv;
import frc.robot.commands.swervedrive.drivebase.AutoAbsoluteDrive;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

public class BasicAuto extends SequentialCommandGroup {
    public BasicAuto(SwerveSubsystem swerve) {
        addCommands(
            new AutoAbsoluteDrive(swerve,4,0,0,0).withTimeout(3)
            );
  }
}
