package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.StopShooter;
import frc.robot.commands.Autonomous.BasicAuto;
import frc.robot.commands.Autonomous.FollowNoteAuto;
import frc.robot.commands.Autonomous.StartFromAngle;
import frc.robot.commands.Climber.AutoClimb;
import frc.robot.commands.Shooter.SmartFire;
import frc.robot.commands.swervedrive.drivebase.AbsoluteDrive;
import frc.robot.commands.swervedrive.drivebase.AbsoluteFieldDrive;
import frc.robot.commands.swervedrive.drivebase.AbsoluteDriveAdv;
import frc.robot.commands.swervedrive.drivebase.TeleopDrive;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Gyroscope;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Photon;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import java.io.File;

public class RobotContainer {
    public SwerveSubsystem drivebase = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(),"swerve"));
    public final CommandXboxController driverXbox = new CommandXboxController(Constants.CONTROLLER_OPERATOR);
    private final Joystick leftStick = new Joystick(1);
    private final Joystick rightStick = new Joystick(2);
    private Translation2d targetPosition = new Translation2d(1,0);

    public static final Shooter shooter = new Shooter(); 
    public static final Climber climber = new Climber();
    public static final Intake intake = new Intake();
    public static final Limelight limelight = new Limelight();
    public static final Photon photon = new Photon();
    public static final Indexer indexer =  new Indexer(); 
    public static final Gyroscope gyroscope = new Gyroscope();
    
    private SendableChooser<Command> m_auto = new SendableChooser<>();
    private final Command BasicAuto = new BasicAuto(drivebase); 
    private final Command FollowNoteAuto = new FollowNoteAuto(drivebase); 
    private final Command StartFromAngle = new StartFromAngle(drivebase);     


    private final Command SmartFire = new SmartFire(shooter,indexer,driverXbox);   
    private final Command StopShooter = new StopShooter(shooter); 


    public RobotContainer() {
        m_auto.setDefaultOption("BasicAuto", BasicAuto);
        m_auto.addOption("Start From Angle", StartFromAngle);
        m_auto.addOption("Follow Note", FollowNoteAuto);
        SmartDashboard.putData("Autonomous Routine", m_auto);

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
        driverXbox.a().onTrue((new InstantCommand(drivebase::zeroGyro)));
        
        driverXbox.rightTrigger().onTrue(SmartFire).onFalse(new InstantCommand(()->intake.stop()));
        // driverXbox.rightTrigger().toggleOnTrue(indexer.run(1)).toggleOnFalse(indexer.stop());

        // driverXbox.leftTrigger().onTrue(shooter.fire(-1)).toggleOnFalse(shooter.stop());
        driverXbox.leftTrigger().toggleOnTrue(new InstantCommand(()->indexer.run(-0.7))).toggleOnFalse(new InstantCommand(()->indexer.stop()));

        driverXbox.leftBumper().toggleOnTrue(climber.runFoward());   
        driverXbox.rightBumper().toggleOnTrue(climber.runBackwards());   

        driverXbox.povUp().onTrue(new InstantCommand(()->intake.retract())).toggleOnFalse(new InstantCommand(()->intake.stopExtendRetract())); 
        driverXbox.povDown().onTrue(new InstantCommand(()->intake.extend())).toggleOnFalse(new InstantCommand(()->intake.stopExtendRetract())); 

        driverXbox.x().toggleOnTrue(new InstantCommand(()->intake.run())).toggleOnFalse(intake.stop());
        driverXbox.x().onTrue(new InstantCommand(()->indexer.run(1))).onFalse(new InstantCommand(()->indexer.stop()));

        driverXbox.b().toggleOnTrue(intake.reverse()).toggleOnFalse(intake.stop());


        driverXbox.y().toggleOnTrue(new InstantCommand(() -> shooter.fire(1.0))).onFalse(new InstantCommand(() -> shooter.fire(0)));
        
        driverXbox.povRight().onTrue(new AutoClimb(driverXbox)).onFalse(climber.stop());
        // driverXbox.button(3).onTrue(new InstantCommand(drivebase::addFakeVisionReading));
    }

    private void configureDefaultCommands() {
        // Set the default command for a subsystem here.
        // For example, setDefaultCommand(new MyDefaultCommand());
    }

    public Command getAutonomousCommand() {
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
