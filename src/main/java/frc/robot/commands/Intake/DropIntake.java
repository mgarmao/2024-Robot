package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class DropIntake extends Command
{
    private final Intake intake;


    public DropIntake(Intake intake)
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
    intake.extend();
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