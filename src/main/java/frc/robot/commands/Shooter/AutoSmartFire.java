package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

public class AutoSmartFire extends Command
{

    private final Shooter shooter;
    private final Indexer indexer;

    private double timerZero;
    private Timer timer;
    private boolean indexing;
    private int counter; 

    public AutoSmartFire(Shooter shooter, Indexer indexer)
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
    shooter.fire(1);
    counter = 0;
    timer.reset();
    timerZero = timer.get();
  }


  @Override
  public void execute()
  {
    SmartDashboard.putBoolean("INDEXING", indexing);
    SmartDashboard.putNumber("Timer", timer.get());
    if((shooter.getRPM()>Constants.smartShooterRPMThresh)&&!indexing){
      if(counter>5){
        indexer.run(1);
        timer.start();
        timerZero = timer.get();
        indexing = true;
      }
    }
    if(shooter.getRPM()>Constants.smartShooterRPMThresh){
      counter=counter+1;
    }
    else{
      counter = 0;
    }
    
    if(!indexing){
      timer.stop();
    }
  }


  @Override
  public boolean isFinished()
  {
    SmartDashboard.putNumber("Timer", timer.get());
    SmartDashboard.putNumber("TimerZero", timerZero);
    if((timer.get()-timerZero)>=1){
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