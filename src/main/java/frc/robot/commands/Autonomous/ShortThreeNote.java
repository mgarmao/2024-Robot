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
import frc.robot.commands.Shooter.SpinUpShooter;
import frc.robot.commands.swervedrive.drivebase.AutoAbsoluteDrive;
import frc.robot.commands.swervedrive.drivebase.ZeroGyro;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

public class ShortThreeNote extends SequentialCommandGroup {
    public ShortThreeNote(SwerveSubsystem swerve) {
        addCommands(
            new DropIntake(intake).withTimeout(2.5),
            new StopIndexer(indexer).withTimeout(0.01),
            new ReverseIndexer(indexer).withTimeout(0.25),
            new StopIndexer(indexer).withTimeout(0.01),
            new SpinUpShooter(shooter).withTimeout(3.0),

            new AutoAbsoluteDrive(swerve, 0.22,0.0, 0.0, 0.0).withTimeout(0.4),
            new StopExtendRetract(intake).withTimeout(0.01),
            
            new AutoSmartFire(shooter, indexer).withTimeout(5),
            new StopShooter(shooter),
            
            new RunIntake(intake,indexer).withTimeout(0.1),
            new ChaseNote(swerve, 0.65, 0.0, 0.0).withTimeout(0.5),
            new AutoAbsoluteDrive(swerve, 0.6,0.0, 0.0, 0.0).withTimeout(0.8),
            new RaiseIntake(intake).withTimeout(0.3),
            new DropIntake(intake).withTimeout(0.3),
            new StopExtendRetract(intake).withTimeout(0.1),
            
            new AutoAbsoluteDrive(swerve,-0.75,0.0,0.0,0.0).withTimeout(1.7),
            new StopIntake(intake).withTimeout(0.01),
            new ReverseShooter(shooter,indexer).withTimeout(0.07),
            new StopIndexer(indexer).withTimeout(0.1),
            new StopShooter(shooter).withTimeout(0.1),
            new SpinUpShooter(shooter),
            new AutoAbsoluteDrive(swerve, 0.2,0.0, 0.0, 0.0).withTimeout(0.4),
            new AutoSmartFire(shooter, indexer).withTimeout(5),
            new StopShooter(shooter).withTimeout(0.1),

            ///////////

            new AutoAbsoluteDrive(swerve,0.85,0.0,0.0,0.0).withTimeout(0.23),
            new AutoAbsoluteDrive(swerve,0.0,0.0,-0.6,0.5).withTimeout(1.5), //angle for one
            new ZeroGyro(swerve).withTimeout(1),
            
            new RunIntake(intake,indexer).withTimeout(1),
            new AutoAbsoluteDrive(swerve,0.7,0.0,0.0,0.0).withTimeout(0.2),
            new ChaseNote(swerve, 0.7, 0.0, 0.0).withTimeout(0.5),
            new AutoAbsoluteDrive(swerve,0.7,0.0,0.0,0.0).withTimeout(0.3),

            new AutoAbsoluteDrive(swerve,-0.8,0.0,0.0,0.0).withTimeout(0.6),
                  
            new AutoAbsoluteDrive(swerve,0.0,0.0,0.75,0.6).withTimeout(0.6), //angle for one
            new ZeroGyro(swerve).withTimeout(1),
            new AutoAbsoluteDrive(swerve,-0.7,0.0,0.0,0.0).withTimeout(0.2), 
            new ReverseShooter(shooter,indexer).withTimeout(0.08),
            new StopIndexer(indexer).withTimeout(0.1),
            new SpinUpShooter(shooter).withTimeout(3),
            new AutoAbsoluteDrive(swerve,-0.55,0.0,0.0,0.0).withTimeout(0.6), 
            new AutoAbsoluteDrive(swerve, 0.2,0.0, 0.0, 0.0).withTimeout(0.4),
            new AutoSmartFire(shooter, indexer).withTimeout(5),
            new StopShooter(shooter).withTimeout(0.1)
        );
    }
}
