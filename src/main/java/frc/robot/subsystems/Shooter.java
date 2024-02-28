// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
    private CANSparkMax motor;
    private CANSparkMax motor2;
    private RelativeEncoder encoder;
    private RelativeEncoder encoder2;

    public Shooter() {
        /** Create a new object to control the SPARK MAX motor controllers. */
        motor = new CANSparkMax(Constants.Shooter1, MotorType.kBrushless);
        motor2 = new CANSparkMax(Constants.Shooter2, MotorType.kBrushless);

        encoder = motor.getEncoder();
        encoder2 = motor.getEncoder();
        /**
         * Restore motor controller parameters to factory default until the next controller 
         * reboot.
         */
        motor.restoreFactoryDefaults();
        motor2.restoreFactoryDefaults();
        motor.setSmartCurrentLimit(Constants.ShooterAmpLimit);
        motor2.setSmartCurrentLimit(Constants.ShooterAmpLimit);

        /**
         * When the SPARK MAX is receiving a neutral command, the idle behavior of the motor 
         * will effectively disconnect all motor wires. This allows the motor to spin down at 
         * its own rate. 
         */
        motor.setIdleMode(IdleMode.kCoast);
        motor2.setIdleMode(IdleMode.kCoast);
        
        motor.setInverted(false);
        motor2.setInverted(true);
        motor.burnFlash();
        motor2.burnFlash();
    }

    public void fire(double speed) {
        motor.set(speed);
        motor2.set(speed);
        
    }

    public Command stop() {
        return runOnce(
        ()->{
            motor.set(0);
            motor2.set(0);
        });
    }
    

    public double getRPM() {
        return encoder.getVelocity();
    }

    /** This method will be called once per scheduler run. */
    @Override
    public void periodic() {
        SmartDashboard.putNumber("Shooter M1 RPM", encoder.getVelocity());
        SmartDashboard.putNumber("Shooter M2 RPM", encoder2.getVelocity());
    }

}