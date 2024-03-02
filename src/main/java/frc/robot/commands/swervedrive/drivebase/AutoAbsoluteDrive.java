// package frc.robot.commands.swervedrive.drivebase;

// import edu.wpi.first.math.MathUtil;
// import edu.wpi.first.math.controller.PIDController;
// import edu.wpi.first.math.geometry.Pose2d;
// import edu.wpi.first.math.geometry.Translation2d;
// import edu.wpi.first.units.Measure;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj2.command.Command;
// import frc.robot.subsystems.swervedrive.SwerveSubsystem;


// /**
//  * Auto Balance command using a simple PID controller. Created by Team 3512
//  * <a href="https://github.com/frc3512/Robot-2023/blob/main/src/main/java/frc3512/robot/commands/AutoBalance.java">...</a>
//  */
// public class AutoAbsoluteDrive extends Command
// {

//   private final SwerveSubsystem swerveSubsystem;
//   private final PIDController   controller;

//   public AutoAbsoluteDrive(SwerveSubsystem swerveSubsystem)
//   {
//     this.swerveSubsystem = swerveSubsystem;
//     controller = new PIDController(1.0, 0.0, 0.0);
//     controller.setTolerance(1);
//     controller.setSetpoint(0.0);
//     // each subsystem used by the command must be passed into the
//     // addRequirements() method (which takes a vararg of Subsystem)
//     addRequirements(this.swerveSubsystem);
//   }

//   /**
//    * The initial subroutine of a command.  Called once when the command is initially scheduled.
//    */
//   @Override
//   public void initialize()
//   {

//   }

//   /**
//    * The main body of a command.  Called repeatedly while the command is scheduled. (That is, it is called repeatedly
//    * until {@link #isFinished()}) returns true.)
//    */
//   @Override
//   public void execute()
//   {
//     SmartDashboard.putBoolean("At Tolerance", controller.atSetpoint());
//     SmartDashboard.putNumber("Swerve System X", swerveSubsystem.getPose().getX());
//     double translationVal = MathUtil.clamp(controller.calculate(swerveSubsystem.getPose().getX(), 5.0), -0.5,0.5);
//     swerveSubsystem.drive(new Translation2d(translationVal, 0.0), 0.0, true);
//   }

//   /**
//    * <p>
//    * Returns whether this command has finished. Once a command finishes -- indicated by this method returning true --
//    * the scheduler will call its {@link #end(boolean)} method.
//    * </p><p>
//    * Returning false will result in the command never ending automatically. It may still be cancelled manually or
//    * interrupted by another command. Hard coding this command to always return true will result in the command executing
//    * once and finishing immediately. It is recommended to use *
//    * {@link edu.wpi.first.wpilibj2.command.InstantCommand InstantCommand} for such an operation.
//    * </p>
//    *
//    * @return whether this command has finished.
//    */
//   @Override
//   public boolean isFinished()
//   {
//     return controller.atSetpoint();
//   }

//   /**
//    * The action to take when the command ends. Called when either the command finishes normally -- that is it is called
//    * when {@link #isFinished()} returns true -- or when  it is interrupted/canceled. This is where you may want to wrap
//    * up loose ends, like shutting off a motor that was being used in the command.
//    *
//    * @param interrupted whether the command was interrupted/canceled
//    */
//   @Override
//   public void end(boolean interrupted)
//   {
//     swerveSubsystem.lock();
//   }
// }

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.swervedrive.drivebase;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import java.util.List;
import swervelib.SwerveController;
import swervelib.math.SwerveMath;

/**
 * An example command that uses an example subsystem.
 */
public class AutoAbsoluteDrive extends Command
{

  private final SwerveSubsystem swerve;
  private final Double  vX, vY;
  private final Double headingHorizontal, headingVertical;
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
  public AutoAbsoluteDrive(SwerveSubsystem swerve, Double vX, Double vY, Double headingHorizontal,Double headingVertical)
  {
    this.swerve = swerve;
    this.vX = vX;
    this.vY = vY;
    this.headingHorizontal = headingHorizontal;
    this.headingVertical = headingVertical;

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

    // Get the desired chassis speeds based on a 2 joystick module.
    ChassisSpeeds desiredSpeeds = swerve.getTargetSpeeds(vX, vY,
                                                         headingHorizontal,
                                                         headingVertical);

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
