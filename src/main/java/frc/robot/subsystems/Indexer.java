// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class Indexer extends SubsystemBase {
    // private final CANcoder motor_cc = new CANcoder(0);
    private final TalonFX motor = new TalonFX(2);
    private final DutyCycleOut m_dutycycle = new DutyCycleOut(0); 

    public Indexer() {
        /** Create a new object to control the SPARK MAX motor controllers. */
        CurrentLimitsConfigs configs = new CurrentLimitsConfigs().withStatorCurrentLimit(Constants.IndexerAmpLimit).withSupplyCurrentLimit(Constants.IndexerAmpLimit).withStatorCurrentLimitEnable(true).withSupplyCurrentLimitEnable(true);
        motor.getConfigurator().apply(new TalonFXConfiguration().withCurrentLimits(configs));
        motor.setNeutralMode(NeutralModeValue.Brake);
        motor.setInverted(false);

    
        /**
         * When the SPARK MAX is receiving a neutral command, the idle behavior of the motor 
         * will effectively disconnect all motor wires. This allows the motor to spin down at 
         * its own rate. 
         */
    }

    /** Retrieve cargo for transportation. */
    public void run(double speed) {
        motor.set(speed);
    }

    public Command runCommand(double speed) {
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