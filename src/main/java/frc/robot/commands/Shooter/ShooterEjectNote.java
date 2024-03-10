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
    indexer.run(1);
    shooter.fire(0.3);
    timer.reset();
  }


  @Override
  public void execute()
  {

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