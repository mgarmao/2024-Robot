package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

public class ShooterEjectNote extends Command
{

    private final Shooter shooter;
    private final Indexer indexer;

    private double timerZero;
    private Timer timer;
    private boolean indexing;

    public ShooterEjectNote(Shooter shooter, Indexer indexer)
    {
        this.shooter = shooter;
        this.indexer = indexer;
        indexing = false;
        timer = new Timer();
        addRequirements(this.shooter);
        addRequirements(this.indexer);
    }


  @Override
  public void initialize()
  {
    shooter.fire(0.3);
    timer.reset();
  }


  @Override
  public void execute()
  {
    if((shooter.getRPM()>2000)&&!indexing){
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
    if((timer.get()-timerZero)>=0.5){
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