package frc.robot.commands;


import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

public class SmartFire extends Command
{

  private final Shooter shooter;

  public SmartFire(Shooter shooter)
  {
    this.shooter = shooter;


    addRequirements(this.shooter);
  }


  @Override
  public void initialize()
  {
    shooter.fire(1);
  }


  @Override
  public void execute()
  {
    // shooter.fire(1);
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