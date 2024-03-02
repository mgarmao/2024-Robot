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
    indexer.run(-0.14);;
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