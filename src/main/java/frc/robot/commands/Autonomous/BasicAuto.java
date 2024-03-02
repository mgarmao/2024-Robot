// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
//test
// Autonomous program created with help from Team 303

package frc.robot.commands.Autonomous;

import static frc.robot.RobotContainer.indexer;
import static frc.robot.RobotContainer.shooter;
import static frc.robot.RobotContainer.intake;

import java.io.File;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.StopShooter;
import frc.robot.commands.Intake.DropIntake;
import frc.robot.commands.Intake.RunIntake;
import frc.robot.commands.Intake.StopExtendRetract;
import frc.robot.commands.Shooter.AutoSmartFire;
import frc.robot.commands.Shooter.SmartFire;
import frc.robot.commands.swervedrive.drivebase.AbsoluteDrive;
import frc.robot.commands.swervedrive.drivebase.AbsoluteDriveAdv;
import frc.robot.commands.swervedrive.drivebase.AutoAbsoluteDrive;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

public class BasicAuto extends SequentialCommandGroup {
    public BasicAuto(SwerveSubsystem swerve) {
        addCommands(
            new DropIntake(intake).withTimeout(2),
            new AutoSmartFire(shooter, indexer).withTimeout(5),
            new StopExtendRetract(intake),
            new StopShooter(shooter),
            new RunIntake(intake,indexer).withTimeout(1),
            new AutoAbsoluteDrive(swerve,0.5,0.0,0.0,0.0).withTimeout(4),
            new AutoAbsoluteDrive(swerve,-0.5,0.0,0.0,0.0).withTimeout(4),
            new AutoSmartFire(shooter, indexer).withTimeout(5),
            new StopShooter(shooter)
        );
  }
}
