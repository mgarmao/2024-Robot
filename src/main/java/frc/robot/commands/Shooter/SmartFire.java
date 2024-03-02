package frc.robot.commands.Shooter;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

public class SmartFire extends Command
{

    private final Shooter shooter;
    private final Indexer indexer;

    private double timerZero;
    private Timer timer;
    private boolean indexing;
    private CommandXboxController XboxController;

    public SmartFire(Shooter shooter, Indexer indexer,CommandXboxController XboxController)
    {
        this.shooter = shooter;
        this.indexer = indexer;
        this.XboxController = XboxController;
        indexing = false;
        timer = new Timer();
        addRequirements(this.shooter);
        addRequirements(this.indexer);
    }


  @Override
  public void initialize()
  {
    shooter.fire(1);
    timer.reset();
  }


  @Override
  public void execute()
  {
    if((shooter.getRPM()>6000)&&!indexing){
        indexer.run(0.4);
        timer.start();
        timerZero = timer.get();
        indexing = true;
    }
    SmartDashboard.putNumber("Timer", timer.get());
  }


  @Override
  public boolean isFinished()
  {
    SmartDashboard.putNumber("Timer", timer.get());
    SmartDashboard.putNumber("TimerZero", timerZero);
    if((timer.get()-timerZero)>=1||!(XboxController.rightTrigger().getAsBoolean())){
      shooter.fire(0);
      indexer.run(0);
      indexing=false;
      return true;
    }
    else{
      return false;
    }
  }


  @Override
  public void end(boolean interrupted)
  {
  }
}