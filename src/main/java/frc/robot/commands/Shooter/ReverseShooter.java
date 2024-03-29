package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

public class ReverseShooter extends Command
{

    private final Shooter shooter;
    private final Indexer indexer;


    public ReverseShooter(Shooter shooter, Indexer indexer)
    {
      this.shooter = shooter;
      this.indexer = indexer;

      addRequirements(this.shooter);
      addRequirements(this.indexer);
    }


  @Override
  public void initialize()
  {
    shooter.fire(-0.05);
    indexer.run(-0.2);
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