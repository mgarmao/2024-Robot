// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.


// angle conversion factor 53.3333333333
package frc.robot;

import com.pathplanner.lib.util.PIDConstants;

import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.util.Units;

// import swervelib.math.Matter;
// import swervelib.parser.PIDFConfig;
import swervelib.math.Matter;

public final class Constants
{
  public static final int CONTROLLER_OPERATOR = 0;
  public static final int JOYSTICK_LEFT = 1;
  public static final int JOYSTICK_RIGHT = 2;
  
  public static final double ROBOT_MASS = (148 - 20.3) * 0.453592; // 32lbs * kg per pound
  public static final Matter CHASSIS    = new Matter(new Translation3d(0, 0, Units.inchesToMeters(8)), ROBOT_MASS);
  public static final double LOOP_TIME  = 0.13; //s, 20ms + 110ms sprk max velocity lag

  public static final int Shooter1 = 9;
  public static final int Shooter2 = 10;
  public static final int ShooterAmpLimit = 35;

  public static final int Intake = 13;
  public static final int rotator1 = 11;
  public static final int rotator2 = 12;
  public static final int IntakeAmpLimit = 40;
  public static final int RotatorAmpLimit = 40;

  public static final int rotatorUpperLimit = 40;
  public static final int rotatorLowerLimit = 0;
  
  public static final int Climber1 = 13;
  public static final int Climber2 = 14;
  public static final int ClimberAmpLimit = 10;

  public static final int IndexMotor = 14;
  public static final int IndexerAmpLimit = 16;

  public static int requestedPipeline = 0;
  public static int startingApriltag = 0;

  public static int ClimberUpperLimit = 0;
  public static int ClimberLowerLimit = -285;


  public static final class Auton
  {

    // public static final PIDFConfig xAutoPID     = new PIDFConfig(0.7, 0, 0);
    // public static final PIDFConfig yAutoPID     = new PIDFConfig(0.7, 0, 0);
    // public static final PIDFConfig angleAutoPID = new PIDFConfig(0.4, 0, 0.01);

    public static final double MAX_SPEED        = 4;
    public static final double MAX_ACCELERATION = 2;
  }

  public static final class Drivebase
  {
    // Hold time on motor brakes when disabled
    public static final double WHEEL_LOCK_TIME = 10; // seconds
  }

  public static final class AutonConstants{
    public static final PIDConstants TRANSLATION_PID = new PIDConstants(0.7, 0, 0);
    public static final PIDConstants ANGLE_PID   = new PIDConstants(0.4, 0, 0.01);
  }

  public static class OperatorConstants
  {

    // Joystick Deadband
    public static final double LEFT_X_DEADBAND = 0.01;
    public static final double LEFT_Y_DEADBAND = 0.01;
    public static final double RIGHT_X_DEADBAND = 0.01;
    public static final double TURN_CONSTANT = 0.75;
  }
}
