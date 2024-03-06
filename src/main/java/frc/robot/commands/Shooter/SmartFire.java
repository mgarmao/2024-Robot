package frc.robot.commands.Shooter;

import static frc.robot.RobotContainer.intake;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

public class SmartFire extends Command
{

    private final Shooter shooter;
    private final Indexer indexer;
    
    private CommandXboxController XboxController;
    int samples = 0;

    public SmartFire(Shooter shooter, Indexer indexer,CommandXboxController XboxController)
    {
        this.shooter = shooter;
        this.indexer = indexer;
        this.XboxController = XboxController;
        addRequirements(this.shooter);
        addRequirements(this.indexer);
    }


  @Override
  public void initialize()
  {
    intake.set(0);
  }


  @Override
  public void execute()
  {
    if(shooter.getRPM()>Constants.smartShooterRPMThresh){
      if(samples>=5){
        indexer.run(Constants.indexerShootSpeed);
      }
    }

    if(shooter.getRPM()>Constants.smartShooterRPMThresh){
      samples = samples+1;
    }
    else{
      samples = 0;
    }
  }


  @Override
  public boolean isFinished()
  {
    if(XboxController.rightTrigger().getAsBoolean()){
      return false;
    }
    else{
      indexer.run(0);
      return true;
    }
  }


  @Override
  public void end(boolean interrupted)
  {
  }
}