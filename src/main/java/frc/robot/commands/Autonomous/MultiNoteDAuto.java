// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
//test

package frc.robot.commands.Autonomous;

import static frc.robot.RobotContainer.indexer;
import static frc.robot.RobotContainer.shooter;
import static frc.robot.RobotContainer.intake;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ChaseNote;
import frc.robot.commands.StopShooter;
import frc.robot.commands.Indexer.StopIndexer;
import frc.robot.commands.Intake.DropIntake;
import frc.robot.commands.Intake.RaiseIntake;
import frc.robot.commands.Intake.RunIntake;
import frc.robot.commands.Intake.StopExtendRetract;
import frc.robot.commands.Intake.StopIntake;
import frc.robot.commands.Shooter.ShooterEjectNote;
import frc.robot.commands.swervedrive.drivebase.AutoAbsoluteDrive;
import frc.robot.commands.swervedrive.drivebase.ZeroGyro;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

public class MultiNoteDAuto extends SequentialCommandGroup {
    public MultiNoteDAuto(SwerveSubsystem swerve) {
        addCommands(
            new ZeroGyro(swerve),
            new DropIntake(intake).withTimeout(1.0),
            new StopIndexer(indexer).withTimeout(0.01),
            new RunIntake(intake,indexer).withTimeout(0.2),
            
            //going to first note
            new AutoAbsoluteDrive(swerve,0.85,0.0,0.0,0.0).withTimeout(1.8),
            new ChaseNote(swerve, 0.8, 0.0, 0.0).withTimeout(0.8),
            new AutoAbsoluteDrive(swerve,0.85,0.0,0.0,0.0).withTimeout(0.2),
            new AutoAbsoluteDrive(swerve,-0.85,0.0,0.0,0.0).withTimeout(0.2),
            new ShooterEjectNote(shooter, indexer).withTimeout(1.0),
            new AutoAbsoluteDrive(swerve,0.0,0.0,0.9,0.0).withTimeout(0.8), //ANGLE
            new AutoAbsoluteDrive(swerve,0.0,0.6,0.9,0.15).withTimeout(3.8)


            // new RaiseIntake(intake).withTimeout(0.3),
            // new DropIntake(intake).withTimeout(0.3),
            // new StopExtendRetract(intake).withTimeout(0.1),

            // //Going back and Ejecting first
            // new ShooterEjectNote(shooter, indexer).withTimeout(0.01),
            // new AutoAbsoluteDrive(swerve, -0.75,0.0, 0.0, 0.0).withTimeout(0.4),

            // //Getting 2nd note
            // new AutoAbsoluteDrive(swerve, 0.0,0.75, 0.0, 0.0).withTimeout(1.3),
            // new ChaseNote(swerve, 0.75, 0.0, 0.0).withTimeout(0.3),
            // new AutoAbsoluteDrive(swerve, 0.75,0.0, 0.0, 0.0).withTimeout(0.1),

            // //Back up and Translate to next note
            // new AutoAbsoluteDrive(swerve, -0.75,0.0, 0.0, 0.0).withTimeout(0.4),
            // new AutoAbsoluteDrive(swerve, 0.0,0.75, 0.0, 0.0).withTimeout(1.3),

            // //Getting 3rd note
            // new ChaseNote(swerve, 0.75, 0.0, 0.0).withTimeout(0.3),
            // new AutoAbsoluteDrive(swerve, 0.75,0.0, 0.0, 0.0).withTimeout(0.1),

            // //Back up and Translate to next note
            // new AutoAbsoluteDrive(swerve, -0.75,0.0, 0.0, 0.0).withTimeout(0.4),
            // new AutoAbsoluteDrive(swerve, 0.0,0.75, 0.0, 0.0).withTimeout(1.3),

            // //Getting 4th note
            // new ChaseNote(swerve, 0.75, 0.0, 0.0).withTimeout(0.3),
            // new AutoAbsoluteDrive(swerve, 0.75,0.0, 0.0, 0.0).withTimeout(0.1),

            // //Back up and Translate to next note
            // new AutoAbsoluteDrive(swerve, -0.75,0.0, 0.0, 0.0).withTimeout(0.4),
            // new AutoAbsoluteDrive(swerve, 0.0,0.75, 0.0, 0.0).withTimeout(1.3),

            // //going back behind line
            // new AutoAbsoluteDrive(swerve,-0.75,0.0,0.0,0.0).withTimeout(1.0),
            // new ShooterEjectNote(shooter, indexer).withTimeout(2.0),

            // new StopIntake(intake).withTimeout(0.01),
            // new StopIndexer(indexer).withTimeout(0.01),
            // new StopShooter(shooter).withTimeout(0.01)
        );
  }
}
