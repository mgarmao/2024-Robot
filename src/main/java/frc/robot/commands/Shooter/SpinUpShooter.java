package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

public class SpinUpShooter extends Command
{
    private final Shooter shooter;

    public SpinUpShooter(Shooter shooter)
    {
        this.shooter = shooter;

        addRequirements(this.shooter);
    }


  @Override
  public void initialize()
  {
    shooter.fire(1);
  }


  @Override
  public void execute()
  {

  }


  @Override
  public boolean isFinished()
  {
    return false;
  }


  @Override
  public void end(boolean interrupted)
  {
  }
}