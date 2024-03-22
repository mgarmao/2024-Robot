// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
//test

package frc.robot.commands.Autonomous;

import static frc.robot.RobotContainer.indexer;
import static frc.robot.RobotContainer.shooter;
import static frc.robot.RobotContainer.intake;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.StopShooter;
import frc.robot.commands.Indexer.ReverseIndexer;
import frc.robot.commands.Indexer.StopIndexer;
import frc.robot.commands.Intake.DropIntake;
import frc.robot.commands.Intake.StopExtendRetract;
import frc.robot.commands.Shooter.AutoSmartFire;
import frc.robot.commands.Shooter.SpinUpShooter;
import frc.robot.commands.swervedrive.drivebase.AutoAbsoluteDrive;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

public class LeftShootGetOuttaDodge extends SequentialCommandGroup {
    public LeftShootGetOuttaDodge(SwerveSubsystem swerve) {
        addCommands(

            new DropIntake(intake).withTimeout(2.5),
            new StopIndexer(indexer).withTimeout(0.5),
            new ReverseIndexer(indexer).withTimeout(0.25),
            new StopIndexer(indexer).withTimeout(0.1),
            new AutoAbsoluteDrive(swerve, 0.2,0.0, 0.0, 0.0).withTimeout(0.4),
            new StopExtendRetract(intake).withTimeout(0.1),
            
            new SpinUpShooter(shooter).withTimeout(3),
            new AutoSmartFire(shooter, indexer).withTimeout(5),
            new StopShooter(shooter),
            
            new AutoAbsoluteDrive(swerve,0.53,-0.2,0.0,0.0).withTimeout(3.4)

            // new AutoAbsoluteDrive(swerve,0.0,0.0,-0.4,-0.4).withTimeout(2), //angle
            // new ZeroGyro(swerve),

            // new AutoAbsoluteDrive(swerve, 0.5,0.0, 0.0, 0.0).withTimeout(2)
        );
  }
}
