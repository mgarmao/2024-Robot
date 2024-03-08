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
import frc.robot.commands.swervedrive.drivebase.AutoAbsoluteDrive;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

public class GetOuttaDodge extends SequentialCommandGroup {
    public GetOuttaDodge(SwerveSubsystem swerve) {
        addCommands(
            new AutoAbsoluteDrive(swerve, 0.5,0.0, 0.0, 0.0).withTimeout(5)
        );
    }
}
