// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
//test
// Autonomous program created with help from Team 303

package frc.robot.commands.Autonomous;

import static frc.robot.RobotContainer.indexer;
import static frc.robot.RobotContainer.shooter;
import static frc.robot.RobotContainer.intake;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ChaseNote;
import frc.robot.commands.StopShooter;
import frc.robot.commands.Intake.DropIntake;
import frc.robot.commands.Intake.RaiseIntake;
import frc.robot.commands.Intake.RunIntake;
import frc.robot.commands.Intake.StopExtendRetract;
import frc.robot.commands.Shooter.AutoSmartFire;

import frc.robot.commands.swervedrive.drivebase.AutoAbsoluteDrive;

import frc.robot.subsystems.swervedrive.SwerveSubsystem;

public class FollowNoteAuto extends SequentialCommandGroup {
    public FollowNoteAuto(SwerveSubsystem swerve) {
        addCommands(
            new AutoAbsoluteDrive(swerve, 0.3, 0.0, 0.0, 0.0),
            new ChaseNote(swerve, 0.3, 0.0, 0.0)
        );
  }
}
