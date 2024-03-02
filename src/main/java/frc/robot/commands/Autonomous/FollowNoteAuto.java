package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ChaseNote;

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
