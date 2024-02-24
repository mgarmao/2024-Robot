// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Climber;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import static frc.robot.RobotContainer.*;

public class AutoClimb extends CommandBase {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    int pipeline;

    CommandXboxController controller;

    public AutoClimb(CommandXboxController driverXbox) {
        controller = driverXbox;
        addRequirements(climber);
        addRequirements(gyroscope);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        Double yaw = gyroscope.getYaw();
        if((-10<=yaw)&&(yaw<=10)){
            climber.retractRightClimber(1);
            climber.retractLeftClimber(1);
        }
        else if ((yaw<=-10)){
            climber.retractRightClimber(1);
            climber.retractLeftClimber(0.3);
        }
        else if ((yaw>=10)){
            climber.retractRightClimber(0.3);
            climber.retractLeftClimber(1);
        }
    }

    @Override
    public void end(boolean interrupted) {
        
    }

    @Override
    public boolean isFinished() {
        boolean isPressed = controller.povDownRight().getAsBoolean();
        if(isPressed){
            return true;
        }
        else{
            return false;
        }
    }
}