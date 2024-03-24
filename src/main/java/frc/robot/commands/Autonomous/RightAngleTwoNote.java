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
import frc.robot.commands.Intake.RunIntake;
import frc.robot.commands.Intake.StopExtendRetract;
import frc.robot.commands.Intake.StopIntake;
import frc.robot.commands.Shooter.AutoSmartFire;
import frc.robot.commands.Shooter.ReverseShooter;
import frc.robot.commands.Shooter.ReverseShooterSlow;
import frc.robot.commands.Shooter.SpinUpShooter;
import frc.robot.commands.swervedrive.drivebase.AutoAbsoluteDrive;
import frc.robot.commands.swervedrive.drivebase.ZeroGyro;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

public class RightAngleTwoNote extends SequentialCommandGroup {
    public RightAngleTwoNote(SwerveSubsystem swerve) {
        addCommands(

            new DropIntake(intake).withTimeout(2.5),
            new StopIndexer(indexer).withTimeout(0.5),
            new ReverseIndexer(indexer).withTimeout(0.25),
            new StopIndexer(indexer).withTimeout(0.1),
            new SpinUpShooter(shooter).withTimeout(0.01),
            new AutoAbsoluteDrive(swerve, 0.2,0.0, 0.0, 0.0).withTimeout(0.4),
            new StopExtendRetract(intake).withTimeout(0.1),
            
            new AutoSmartFire(shooter, indexer).withTimeout(5),
            new StopShooter(shooter),
            



            //GOING back and zeroing
            new AutoAbsoluteDrive(swerve,0.85,0.0,0.0,0.0).withTimeout(0.4),
            new AutoAbsoluteDrive(swerve,0.0,0.0,0.8,0.3).withTimeout(0.5), //angle for 2nd note
            new ZeroGyro(swerve).withTimeout(0.03),
            ///


            //Going to note
            new ReverseShooterSlow(shooter).withTimeout(0.01),
            new RunIntake(intake,indexer).withTimeout(0.01),

            new AutoAbsoluteDrive(swerve,0.7,0.0,0.0,0.0).withTimeout(0.5),
            new ChaseNote(swerve, 0.7, 0.0, 0.0).withTimeout(0.6),
            new AutoAbsoluteDrive(swerve,0.7,0.0,0.0,0.0).withTimeout(0.3),
            ///


            //going back and setting angle
            new AutoAbsoluteDrive(swerve,-0.7,0.0,0.0,0.0).withTimeout(1.4),

            new AutoAbsoluteDrive(swerve,0.0,0.0,-0.75,0.6).withTimeout(0.5),//angle for aiming at speaker
            new ZeroGyro(swerve).withTimeout(0.01),
            /// 


            //Aiming into fender
            new ReverseShooter(shooter, indexer).withTimeout(0.01),
            new AutoAbsoluteDrive(swerve,-0.7,0.0,0.0,0.0).withTimeout(0.1), 
            new AutoAbsoluteDrive(swerve,0.0,0.0,0.0,0.0).withTimeout(0.01), 
            new StopIndexer(indexer).withTimeout(0.01),
            new StopShooter(shooter).withTimeout(0.01),


            // shooting
            new SpinUpShooter(shooter).withTimeout(0.01),

            new StopIndexer(indexer).withTimeout(0.01),
            new StopShooter(shooter).withTimeout(0.01),
            // new AutoAbsoluteDrive(swerve, 0.2,0.0, 0.0, 0.0).withTimeout(0.4),
            new AutoSmartFire(shooter, indexer).withTimeout(5),
            new StopShooter(shooter).withTimeout(0.1) ,
            new StopIntake(intake).withTimeout(0.01)

        );
  }
}
