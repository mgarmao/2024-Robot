package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

public class ReverseShooterSlow extends Command
{

    private final Shooter shooter;


    public ReverseShooterSlow(Shooter shooter)
    {
      this.shooter = shooter;
      addRequirements(this.shooter);
    }


  @Override
  public void initialize()
  {
    shooter.fire(-0.05);
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