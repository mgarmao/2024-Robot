// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import frc.robot.Constants;

public class Indexer extends SubsystemBase {
    private CANSparkMax motor;
    private CANSparkMax motor2;

    public Indexer() {
        /** Create a new object to control the SPARK MAX motor controllers. */
        motor = new CANSparkMax(Constants.IndexMotor, MotorType.kBrushless);
        /**
         * Restore motor controller parameters to factory default until the next controller 
         * reboot.
         */
        motor.restoreFactoryDefaults();
        motor.setSmartCurrentLimit(Constants.IndexerAmpLimit);

        /**
         * When the SPARK MAX is receiving a neutral command, the idle behavior of the motor 
         * will effectively disconnect all motor wires. This allows the motor to spin down at 
         * its own rate. 
         */
        motor.setIdleMode(IdleMode.kCoast);
        
        motor.setInverted(false);
    }

    /** Retrieve cargo for transportation. */
    public Command run(double speed) {
        return runOnce(
        ()->{
            
            motor.set(speed);

        });
    }

    /** Eject cargo from the robot. */
    public Command stop() {
        return runOnce(
        ()->{
            motor.set(0);
        });
    }
    

    /** This method will be called once per scheduler run. */
    @Override
    public void periodic() {
        
    }

}