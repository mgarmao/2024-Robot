package frc.robot.commands.swervedrive.drivebase;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;


public class ZeroGyro extends Command
{
  private final SwerveSubsystem swerve;


    public ZeroGyro(SwerveSubsystem swerve)
    {
        this.swerve = swerve;
        addRequirements(this.swerve);
    }


  @Override
  public void initialize()
  {

  }


  @Override
  public void execute()
  {
    swerve.zeroGyro();
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