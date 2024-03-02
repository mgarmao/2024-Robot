// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Limelight;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import edu.wpi.first.wpilibj2.command.Command;
import static frc.robot.RobotContainer.*;

public class ChangePipeline extends Command {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    int pipeline;

    public ChangePipeline(int pipeline) {
        this.pipeline = pipeline;
        addRequirements(limelight);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        NetworkTableEntry pipelineEntry = table.getEntry("pipeline");
    	pipelineEntry.setNumber(pipeline);
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}