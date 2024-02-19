package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autonomous.BasicAuto;
import frc.robot.commands.swervedrive.drivebase.AbsoluteDrive;
import frc.robot.commands.swervedrive.drivebase.AbsoluteFieldDrive;
import frc.robot.commands.swervedrive.drivebase.AbsoluteDriveAdv;
import frc.robot.commands.swervedrive.drivebase.TeleopDrive;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Photon;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import java.io.File;

public class RobotContainer {
    public SwerveSubsystem drivebase = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(),"swerve"));
    private final CommandXboxController driverXbox = new CommandXboxController(Constants.CONTROLLER_OPERATOR);
    private final Joystick leftStick = new Joystick(1);
    private final Joystick rightStick = new Joystick(2);
    private Translation2d targetPosition = new Translation2d(1,0);

    public static final Shooter shooter = new Shooter(); 
    public static final Climber climber = new Climber();
    public static final Intake intake = new Intake();
    public static final Photon photon = new Photon();
    
    private SendableChooser<Command> m_auto = new SendableChooser<>();
    private final Command BasicAuto = new BasicAuto(drivebase);  


    public RobotContainer() {
        m_auto.setDefaultOption("BasicAuto", BasicAuto);

        configureButtonBindings();
        configureDefaultCommands();

        AbsoluteDrive closedAbsoluteDrive = new AbsoluteDrive(drivebase,
                                                          // Applies deadbands and inverts controls because joysticks
                                                          // are back-right positive while robot
                                                          // controls are front-left positive
                                                          () -> MathUtil.applyDeadband(leftStick.getY(),
                                                                                       OperatorConstants.LEFT_Y_DEADBAND),
                                                          () -> MathUtil.applyDeadband(leftStick.getX(),
                                                                                       OperatorConstants.LEFT_X_DEADBAND),
                                                          () -> -rightStick.getX(),
                                                          () -> -rightStick.getY());

    AbsoluteFieldDrive closedFieldAbsoluteDrive = new AbsoluteFieldDrive(drivebase,
                                                                         () ->
                                                                             MathUtil.applyDeadband(leftStick.getY(),
                                                                                                    OperatorConstants.LEFT_Y_DEADBAND),
                                                                         () -> MathUtil.applyDeadband(leftStick.getX(),
                                                                                                      OperatorConstants.LEFT_X_DEADBAND),
                                                                         () -> leftStick.getRawAxis(2));

    AbsoluteDriveAdv closedAbsoluteDriveAdv = new AbsoluteDriveAdv(drivebase,
                                                                      () -> MathUtil.applyDeadband(leftStick.getY(),
                                                                                                OperatorConstants.LEFT_Y_DEADBAND),
                                                                      () -> MathUtil.applyDeadband(leftStick.getX(),
                                                                                                  OperatorConstants.LEFT_X_DEADBAND),
                                                                      () -> MathUtil.applyDeadband(rightStick.getX(),
                                                                                                  OperatorConstants.RIGHT_X_DEADBAND), 
                                                                      driverXbox.y(), 
                                                                      driverXbox.a(), 
                                                                      driverXbox.x(), 
                                                                      driverXbox.b());

    TeleopDrive simClosedFieldRel = new TeleopDrive(drivebase,
                                                    () -> MathUtil.applyDeadband(leftStick.getY(),
                                                                                 OperatorConstants.LEFT_Y_DEADBAND),
                                                    () -> MathUtil.applyDeadband(leftStick.getX(),
                                                                                 OperatorConstants.LEFT_X_DEADBAND),
                                                    () -> driverXbox.getRawAxis(2), () -> true);
    TeleopDrive closedFieldRel = new TeleopDrive(
        drivebase,
        () -> MathUtil.applyDeadband(leftStick.getRawAxis(1), OperatorConstants.LEFT_Y_DEADBAND),
        () -> MathUtil.applyDeadband(leftStick.getRawAxis(0), OperatorConstants.LEFT_X_DEADBAND),
        () -> -leftStick.getRawAxis(2), () -> true);

    drivebase.setDefaultCommand(!RobotBase.isSimulation() ? closedAbsoluteDrive : closedAbsoluteDriveAdv);
    }

    private void configureButtonBindings() {
        // Configure button bindings here
        
        driverXbox.rightTrigger().toggleOnTrue(shooter.fire(1));
        driverXbox.rightTrigger().toggleOnFalse(shooter.stop());   

        driverXbox.leftTrigger().onTrue(shooter.fire(-1));
        driverXbox.leftTrigger().onFalse(shooter.stop());

        driverXbox.leftBumper().toggleOnTrue(climber.runFoward());   
        driverXbox.rightBumper().toggleOnTrue(climber.runBackwards());   

        driverXbox.povUp().onTrue(intake.retract());
        driverXbox.povDown().onTrue(intake.extend());
        driverXbox.povUp().toggleOnFalse(intake.stopExtendRetract()); 
        driverXbox.povDown().toggleOnFalse(intake.stopExtendRetract()); 

        driverXbox.button(1).onTrue((new InstantCommand(drivebase::zeroGyro)));
        driverXbox.button(3).onTrue(new InstantCommand(drivebase::addFakeVisionReading));
    }

    private void configureDefaultCommands() {
        // Set the default command for a subsystem here.
        // For example, setDefaultCommand(new MyDefaultCommand());
    }

    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        // return new ExampleCommand(m_exampleSubsystem);
        return m_auto.getSelected();        
    }

    public void setDriveMode()
    {
        //drivebase.setDefaultCommand();
    }

    public void setMotorBrake(boolean brake)
    {
        drivebase.setMotorBrake(brake);
    }
}
