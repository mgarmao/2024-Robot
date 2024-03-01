package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

public class StopShooter extends Command
{

    private final Shooter shooter;

    public StopShooter(Shooter shooter)
    {
        this.shooter = shooter;
       
        addRequirements(this.shooter);
    }


  @Override
  public void initialize()
  {
    shooter.stop();
  }


  @Override
  public void execute()
  {

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