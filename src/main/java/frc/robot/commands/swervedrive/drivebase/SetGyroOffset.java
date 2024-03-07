package frc.robot.commands.swervedrive.drivebase;

import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import swervelib.SwerveDrive;
import swervelib.imu.SwerveIMU;

public class SetGyroOffset extends Command
{
  private final SwerveSubsystem swerve;
  private final double angleOffset;


    public SetGyroOffset(SwerveSubsystem swerve,double angleOffset)
    {
        this.swerve = swerve;
        this.angleOffset = angleOffset;

        addRequirements(this.swerve);
    }


  @Override
  public void initialize()
  {

  }


  @Override
  public void execute()
  {
    Rotation3d rotation3dOffset = new Rotation3d(0.0,0.0,angleOffset);
    swerve.setGyroOffset(rotation3dOffset);
  }


  @Override
  public boolean isFinished()
  {
    return true;
  }


  @Override
  public void end(boolean interrupted)
  {
  }
}