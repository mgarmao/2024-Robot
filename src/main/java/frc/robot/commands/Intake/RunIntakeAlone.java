package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;

public class RunIntakeAlone extends Command
{

    private final Intake intake;


    public RunIntakeAlone(Intake intake)
    {
        this.intake = intake;
        
        addRequirements(this.intake);
    }


  @Override
  public void initialize()
  {

  }


  @Override
  public void execute()
  {
    intake.run();
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