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
import frc.robot.commands.Indexer.ReverseIndexer;
import frc.robot.commands.Indexer.StopIndexer;
import frc.robot.commands.Intake.DropIntake;
import frc.robot.commands.Intake.RaiseIntake;
import frc.robot.commands.Intake.RunIntake;
import frc.robot.commands.Intake.StopExtendRetract;
import frc.robot.commands.Intake.StopIntake;
import frc.robot.commands.Shooter.AutoSmartFire;
import frc.robot.commands.Shooter.ReverseShooter;
import frc.robot.commands.Shooter.ReverseShooterSlow;
import frc.robot.commands.swervedrive.drivebase.AutoAbsoluteDrive;
import frc.robot.commands.swervedrive.drivebase.ZeroGyro;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

public class BasicAuto extends SequentialCommandGroup {
    public BasicAuto(SwerveSubsystem swerve) {
        addCommands(
            // new SetGyroOffset(swerve, Math.PI).withTimeout(0.2),
            new ZeroGyro(swerve),
            new DropIntake(intake).withTimeout(2.5),
            new StopIndexer(indexer).withTimeout(0.5),
            new ReverseIndexer(indexer).withTimeout(0.25),
            new StopIndexer(indexer).withTimeout(0.1),
            new AutoAbsoluteDrive(swerve, 0.2,0.0, 0.0, 0.0).withTimeout(0.4),
            new StopExtendRetract(intake).withTimeout(0.1),
            
            new AutoSmartFire(shooter, indexer).withTimeout(5),
            new StopShooter(shooter),
            
            new ReverseShooterSlow(shooter).withTimeout(0.01),
            new RunIntake(intake,indexer).withTimeout(1),
            new ChaseNote(swerve, 0.65, 0.0, 0.0).withTimeout(1.1),
            new AutoAbsoluteDrive(swerve, 0.6,0.0, 0.0, 0.0).withTimeout(0.3),
            new RaiseIntake(intake).withTimeout(0.3),
            new DropIntake(intake).withTimeout(0.3),
            new StopExtendRetract(intake).withTimeout(0.1),
            
            new AutoAbsoluteDrive(swerve,-0.75,0.0,0.0,0.0).withTimeout(2.5),
            new StopIntake(intake),
            new ReverseShooter(shooter,indexer).withTimeout(0.08),
            new StopIndexer(indexer).withTimeout(0.1),
            new StopShooter(shooter).withTimeout(0.1),
            new AutoAbsoluteDrive(swerve, 0.2,0.0, 0.0, 0.0).withTimeout(0.4),
            new AutoSmartFire(shooter, indexer).withTimeout(5),
            new StopShooter(shooter)

            // new SetGyroOffset(swerve, 3.7).withTimeout(0.1)
            // new AutoAbsoluteDrive(swerve, 0.0,0.0, 0.0, -1.0).withTimeout(0.1)

        );
    }
}
