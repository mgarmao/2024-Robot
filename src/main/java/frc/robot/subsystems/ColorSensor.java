// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.ColorSensorV3;

public class ColorSensor extends SubsystemBase {
    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
    public ColorSensor() {

    }

    public double getIRRed() {
        Color detectedColor = m_colorSensor.getColor();
        return detectedColor.red;
    }

    public boolean noteIn(){
        if(getIRRed()>Constants.redColorSensorThresh){
            return true;
        }
        else{
            return false;
        }
    }


    @Override
    public void periodic() {

        SmartDashboard.putBoolean("Note In", noteIn());
        

        SmartDashboard.putNumber("Red", getIRRed());
    }

    @Override
    public void simulationPeriodic() {

    }
}
