// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkBase.SoftLimitDirection;
import com.revrobotics.CANSparkLowLevel.MotorType;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
    private CANSparkMax motor;
    private CANSparkMax motor2;

    public Climber() {
        /** Create a new object to control the SPARK MAX motor controllers. */
        motor = new CANSparkMax(Constants.Climber1, MotorType.kBrushless);
        motor2 = new CANSparkMax(Constants.Climber2, MotorType.kBrushless);
        /**
         * Restore motor controller parameters to factory default until the next controller 
         * reboot.
         */
        motor.restoreFactoryDefaults();
        motor2.restoreFactoryDefaults();
        motor.setSmartCurrentLimit(Constants.ClimberAmpLimit);
        motor2.setSmartCurrentLimit(Constants.ClimberAmpLimit);

        // final float motorZero = (float)motor.getEncoder().getPosition();
        // final float motor2Zero = (float)motor2.getEncoder().getPosition();

        motor.setSoftLimit(SoftLimitDirection.kReverse, Constants.ClimberUpperLimit);
        motor.setSoftLimit(SoftLimitDirection.kForward, Constants.ClimberLowerLimit);

        motor.setSoftLimit(SoftLimitDirection.kReverse, Constants.ClimberUpperLimit);
        motor2.setSoftLimit(SoftLimitDirection.kForward, Constants.ClimberLowerLimit);

        motor.enableSoftLimit(SoftLimitDirection.kForward, false);
        motor.enableSoftLimit(SoftLimitDirection.kReverse, false);

        motor2.enableSoftLimit(SoftLimitDirection.kForward, false);
        motor2.enableSoftLimit(SoftLimitDirection.kReverse, false);

        /**
         * When the SPARK MAX is receiving a neutral command, the idle behavior of the motor 
         * will effectively disconnect all motor wires. This allows the motor to spin down at 
         * its own rate. 
         */
        motor.setIdleMode(IdleMode.kBrake);
        motor2.setIdleMode(IdleMode.kBrake);
        
        motor.setInverted(true);
        motor2.setInverted(false);
    }

    /** Retrieve cargo for transportation. */
    public Command runFoward() {
        return runOnce(
        ()->{
            motor.set(1);
            motor2.set(1);
        });
    }

    public Command runBackwards() {
        return runOnce(
        ()->{
            motor.set(-1);
            motor2.set(-1);
        });
    }

    /** Eject cargo from the robot. */
    public Command stop() {
        return runOnce(
        ()->{
            motor.set(0);
            motor2.set(0);
        });
    }

    public Command retractLeftClimber(double speed) {
        return runOnce(
        ()->{
            motor.set(speed);
        });
    }

    public Command retractRightClimber(double speed) {
        return runOnce(
        ()->{
            motor2.set(speed);
        });
    }

    /** This method will be called once per scheduler run. */
    @Override
    public void periodic() {
        SmartDashboard.putNumber("Left Climber Pos", motor.getEncoder().getPosition());
        SmartDashboard.putNumber("Right Climber Pos", motor2.getEncoder().getPosition());
    }

}