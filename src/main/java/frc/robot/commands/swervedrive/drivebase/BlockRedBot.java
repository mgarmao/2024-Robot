package frc.robot.commands.swervedrive.drivebase;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

import static frc.robot.RobotContainer.photon;

import java.util.List;
import swervelib.SwerveController;
import swervelib.math.SwerveMath;

/**
 * An example command that uses an example subsystem.
 */
public class BlockRedBot extends Command
{

    private final SwerveSubsystem swerve;
    private Double  vX, vY;
    private final Double headingHorizontal=-1.0;
    private final Double headingVertical=0.0;
    private boolean initRotation = false;

  /**
   * Used to drive a swerve robot in full field-centric mode.  vX and vY supply translation inputs, where x is
   * torwards/away from alliance wall and y is left/right. headingHorzontal and headingVertical are the Cartesian
   * coordinates from which the robot's angle will be derived— they will be converted to a polar angle, which the robot
   * will rotate to.
   *
   * @param swerve            The swerve drivebase subsystem.
   * @param vX                DoubleSupplier that supplies the x-translation joystick input.  Should be in the range -1
   *                          to 1 with deadband already accounted for.  Positive X is away from the alliance wall.
   * @param vY                DoubleSupplier that supplies the y-translation joystick input.  Should be in the range -1
   *                          to 1 with deadband already accounted for.  Positive Y is towards the left wall when
   *                          looking through the driver station glass.
   * @param headingHorizontal DoubleSupplier that supplies the horizontal component of the robot's heading angle. In the
   *                          robot coordinate system, this is along the same axis as vY. Should range from -1 to 1 with
   *                          no deadband.  Positive is towards the left wall when looking through the driver station
   *                          glass.
   * @param headingVertical   DoubleSupplier that supplies the vertical component of the robot's heading angle.  In the
   *                          robot coordinate system, this is along the same axis as vX.  Should range from -1 to 1
   *                          with no deadband. Positive is away from the alliance wall.
   */
  public BlockRedBot(SwerveSubsystem swerve)
  {
    this.swerve = swerve;
    addRequirements(swerve);
  }

  @Override
  public void initialize()
  {
    initRotation = true;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {

    vX = 0.0; 
    photon.setCamera(0);
    photon.setPipline(0);
    if(photon.hasTarget()){
        vX = 0.5;
    }

    photon.setCamera(0);
    photon.setPipline(0);
    if(photon.hasTarget()){
        vX = -0.5;
    }


    

    // Get the desired chassis speeds based on a 2 joystick module.
    ChassisSpeeds desiredSpeeds = swerve.getTargetSpeeds(vX, vY,headingHorizontal,headingVertical);

    // Prevent Movement After Auto
    if(initRotation)
    {
      if(headingHorizontal == 0 && headingVertical == 0)
      {
        // Get the curretHeading
        Rotation2d firstLoopHeading = swerve.getHeading();
      
        // Set the Current Heading to the desired Heading
        desiredSpeeds = swerve.getTargetSpeeds(0, 0, firstLoopHeading.getSin(), firstLoopHeading.getCos());
      }
      //Dont Init Rotation Again
      initRotation = false;
    }

    // Limit velocity to prevent tippy
    Translation2d translation = SwerveController.getTranslation2d(desiredSpeeds);
    translation = SwerveMath.limitVelocity(translation, swerve.getFieldVelocity(), swerve.getPose(),
                                           Constants.LOOP_TIME, Constants.ROBOT_MASS, List.of(Constants.CHASSIS),
                                           swerve.getSwerveDriveConfiguration());
    SmartDashboard.putNumber("LimitedTranslation", translation.getX());
    SmartDashboard.putString("Translation", translation.toString());

    // Make the robot move
    swerve.drive(translation, desiredSpeeds.omegaRadiansPerSecond, true);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted)
  {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished()
  {
    return false;
  }


}
