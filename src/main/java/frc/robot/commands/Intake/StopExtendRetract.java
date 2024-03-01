package frc.robot.commands.Intake;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class StopExtendRetract extends Command
{

    private final Intake intake;


    public StopExtendRetract(Intake intake)
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
    intake.stopExtendRetract();
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