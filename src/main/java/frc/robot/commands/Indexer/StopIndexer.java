package frc.robot.commands.Indexer;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Indexer;


public class StopIndexer extends Command
{

    private final Indexer indexer;

    public StopIndexer(Indexer indexer)
    {
      this.indexer = indexer;
      
      addRequirements(this.indexer);
    }


  @Override
  public void initialize()
  {

  }


  @Override
  public void execute()
  {
    indexer.stop();
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