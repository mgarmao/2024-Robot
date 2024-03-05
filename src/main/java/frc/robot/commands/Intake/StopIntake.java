package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class StopIntake extends Command
{

    private final Intake intake;


    public StopIntake(Intake intake)
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
    intake.set(0);
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