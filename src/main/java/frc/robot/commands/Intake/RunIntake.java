package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;

public class RunIntake extends Command
{

    private final Indexer indexer;
    private final Intake intake;


    public RunIntake(Intake intake, Indexer indexer)
    {
        this.intake = intake;
        this.indexer = indexer;
        
        addRequirements(this.intake);
        addRequirements(this.indexer);
    }


  @Override
  public void initialize()
  {

  }


  @Override
  public void execute()
  {
    intake.run();
    indexer.run(0.1);
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