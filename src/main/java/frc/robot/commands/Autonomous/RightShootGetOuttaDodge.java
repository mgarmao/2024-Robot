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
import frc.robot.commands.Indexer.ReverseIndexer;
import frc.robot.commands.Indexer.StopIndexer;
import frc.robot.commands.Intake.DropIntake;
import frc.robot.commands.Intake.RaiseIntake;
import frc.robot.commands.Intake.RunIntake;
import frc.robot.commands.Intake.StopExtendRetract;
import frc.robot.commands.Intake.StopIntake;
import frc.robot.commands.Shooter.AutoSmartFire;

import frc.robot.commands.swervedrive.drivebase.AutoAbsoluteDrive;
import frc.robot.commands.swervedrive.drivebase.ZeroGyro;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

public class RightShootGetOuttaDodge extends SequentialCommandGroup {
    public RightShootGetOuttaDodge(SwerveSubsystem swerve) {
        addCommands(

            new DropIntake(intake).withTimeout(2.5),
            new StopIndexer(indexer).withTimeout(0.5),
            new ReverseIndexer(indexer).withTimeout(0.2),
            new StopIndexer(indexer).withTimeout(0.1),
            new AutoAbsoluteDrive(swerve, 0.2,0.0, 0.0, 0.0).withTimeout(0.4),
            new StopExtendRetract(intake).withTimeout(0.1),
            
            new AutoSmartFire(shooter, indexer).withTimeout(5),
            new StopShooter(shooter),
            
            new AutoAbsoluteDrive(swerve,0.5,0.0,0.0,0.0).withTimeout(3),

            new AutoAbsoluteDrive(swerve,0.0,0.0,0.4,-0.4).withTimeout(2), //angle
            new ZeroGyro(swerve),

            new AutoAbsoluteDrive(swerve, 0.5,0.0, 0.0, 0.0).withTimeout(2)
        );
  }
}
