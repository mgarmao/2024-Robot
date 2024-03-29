// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
//test

package frc.robot.commands.Autonomous;

import static frc.robot.RobotContainer.shooter;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.StopShooter;
import frc.robot.commands.swervedrive.drivebase.AutoAbsoluteDrive;
import frc.robot.commands.swervedrive.drivebase.BlockRedBot;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

public class KickeyAuto extends SequentialCommandGroup {
    public KickeyAuto(SwerveSubsystem swerve) {
        addCommands(
            new AutoAbsoluteDrive(swerve,0.9,0.0,0.0,0.0).withTimeout(3),
            new AutoAbsoluteDrive(swerve,2.0,0.0,-1.0,0.0).withTimeout(2),
            
            new BlockRedBot(swerve).withTimeout(15),

            new StopShooter(shooter)
        );
  }
}
