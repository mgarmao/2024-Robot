// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.math.controller.PIDController;
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

    public final CommandXboxController driverXbox = new CommandXboxController(Constants.CONTROLLER_OPERATOR);

    private PIDController PIDController;
    double desiredRPM = 0;
    

    public Shooter() {
        PIDController = new PIDController(0.0002, 0.0005, 0.000002);
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

    public void setRPM (double rpm){
        desiredRPM = rpm;
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
        
        // if(driverXbox.povLeft().getAsBoolean()){
        //     PIDController.setSetpoint(desiredRPM);
        //     double motorPIDOutput = clamp(PIDController.calculate(motor.getEncoder().getVelocity(),desiredRPM),-1,1);
        //     double motor2PIDOutput = clamp(PIDController.calculate(motor2.getEncoder().getVelocity(),desiredRPM),-1,1);

            
        //     SmartDashboard.putNumber("ShooterPID1", motorPIDOutput);
        //     SmartDashboard.putNumber("ShooterPID2", motor2PIDOutput);
        //     motor.set(motorPIDOutput);
        //     motor2.set(motor2PIDOutput);
        // }

    }


    public static double clamp(double value, double min, double max) {
        double clampedValue = Math.max(value, min);
        clampedValue = Math.min(clampedValue, max);
        return clampedValue;
    }
}