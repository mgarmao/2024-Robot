// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkBase.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
    private CANSparkMax motor;
    private CANSparkMax motor2;
    private CANSparkMax rotator;

    public Intake() {
        /** Create a new object to control the SPARK MAX motor controllers. */
        motor = new CANSparkMax(Constants.Intake1, MotorType.kBrushless);
        motor2 = new CANSparkMax(Constants.Intake2, MotorType.kBrushless);
        rotator = new CANSparkMax(Constants.rotator, MotorType.kBrushless);

        /**
         * Restore motor controller parameters to factory default until the next controller 
         * reboot.
         */
        motor.restoreFactoryDefaults();
        motor2.restoreFactoryDefaults();
        rotator.restoreFactoryDefaults();
        
        motor.setSmartCurrentLimit(Constants.IntakeAmpLimit);
        motor2.setSmartCurrentLimit(Constants.IntakeAmpLimit);
        rotator.setSmartCurrentLimit(Constants.RotatorAmpLimit);

        /**
         * When the SPARK MAX is receiving a neutral command, the idle behavior of the motor 
         * will effectively disconnect all motor wires. This allows the motor to spin down at 
         * its own rate. 
         */
        motor.setIdleMode(IdleMode.kCoast);
        motor2.setIdleMode(IdleMode.kCoast);
        rotator.setIdleMode(IdleMode.kCoast);

        rotator.setSoftLimit(SoftLimitDirection.kForward, Constants.rotatorUpperLimit);
        rotator.setSoftLimit(SoftLimitDirection.kReverse,Constants.rotatorLowerLimit);
        
        motor2.follow(motor);
    }

    /** Retrieve cargo for transportation. */
    public Command run() {
        return runOnce(
        ()->{
            motor.set(1);
        });
    }

    /** Eject cargo from the robot. */
    public Command stop() {
        return runOnce(
        ()->{
            motor.set(0);
        });
    }

    public Command extend(){
        return runOnce(
        ()->{
            rotator.set(0.4);
        });
    }

    public Command retract(){
        return runOnce(
        ()->{
            rotator.set(-0.4);
        });
    }

    /** This method will be called once per scheduler run. */
    @Override
    public void periodic() {
        
    }

}