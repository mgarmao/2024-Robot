package frc.robot.commands.Intake;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;

public class Outtake extends Command
{

    private final Indexer indexer;
    private final Intake intake;


    public Outtake(Intake intake, Indexer indexer)
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
    indexer.run(-Constants.indexerIntakeSpeed);;
    intake.set(-1);
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