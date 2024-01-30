package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.Shooter;

public class RobotContainer {

    private final CommandXboxController xboxController = new CommandXboxController(0);
    private final Shooter shooter = new Shooter(); // Assuming you have a Shooter subsystem

    public RobotContainer() {
        configureButtonBindings();
        configureDefaultCommands();
    }

    private void configureButtonBindings() {
      // Configure button bindings here
      xboxController.a().toggleOnTrue(shooter.fire());
    }

    private void configureDefaultCommands() {
        // Set the default command for a subsystem here.
        // For example, setDefaultCommand(new MyDefaultCommand());
    }

    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        // return new ExampleCommand(m_exampleSubsystem);
        return null;
    }
}
