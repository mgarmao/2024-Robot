package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.StopShooter;
import frc.robot.commands.Autonomous.BasicAuto;
import frc.robot.commands.Autonomous.FollowNoteAuto;
import frc.robot.commands.Autonomous.LeftStartFromAngle;
import frc.robot.commands.Autonomous.ThreeNote;
import frc.robot.commands.Autonomous.ShortThreeNote;
import frc.robot.commands.Autonomous.LeftThreeNote;
import frc.robot.commands.Autonomous.RightShootGetOuttaDodge;
import frc.robot.commands.Autonomous.GetOuttaDodge;
import frc.robot.commands.Autonomous.LeftShootGetOuttaDodge;
import frc.robot.commands.Autonomous.RightStartFromAngle;
import frc.robot.commands.Autonomous.OneNoteDAuto;
import frc.robot.commands.Autonomous.MultiNoteDAuto;
import frc.robot.commands.Autonomous.RightAngleTwoNote;
import frc.robot.commands.Autonomous.LeftAngleTwoNote;
import frc.robot.commands.Shooter.SmartFire;
import frc.robot.commands.swervedrive.drivebase.AbsoluteDrive;
import frc.robot.commands.swervedrive.drivebase.AbsoluteFieldDrive;
import frc.robot.commands.swervedrive.drivebase.AbsoluteDriveAdv;
import frc.robot.commands.swervedrive.drivebase.TeleopDrive;
import frc.robot.subsystems.Climber;
// import frc.robot.subsystems.ColorSensor;
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
    // public static final ColorSensor colorSensor = new ColorSensor();
    
    private SendableChooser<Command> m_auto = new SendableChooser<>();
    private final Command BasicAuto = new BasicAuto(drivebase); 
    private final Command FollowNoteAuto = new FollowNoteAuto(drivebase); 
    private final Command LeftStartFromAngle = new LeftStartFromAngle(drivebase);
    private final Command RightStartFromAngle = new RightStartFromAngle(drivebase);        
    private final Command ThreeNote = new ThreeNote(drivebase);
    private final Command GetOuttaDodge = new GetOuttaDodge(drivebase);  
    private final Command ShortThreeNote = new ShortThreeNote(drivebase);            
    private final Command LeftThreeNote = new LeftThreeNote(drivebase);   
    private final Command RightShootGetOuttaDodge = new RightShootGetOuttaDodge(drivebase);                      
    private final Command LeftShootGetOuttaDodge = new LeftShootGetOuttaDodge(drivebase);  
    private final Command OneNoteDAuto = new OneNoteDAuto(drivebase);    
    private final Command MultiNoteDAuto = new MultiNoteDAuto(drivebase);         
    private final Command RightAngleTwoNote = new RightAngleTwoNote(drivebase);     
    private final Command LeftAngleTwoNote = new LeftAngleTwoNote(drivebase);                                                                                                                                                                                                                                                                                                         
                   

    // Rotation3d rotation3dOffsetRads = new Rotation3d(0.0,0.0,Constants.offsetAngle);

    private final Command SmartFire = new SmartFire(shooter,indexer,driverXbox);   
    private final Command StopShooter = new StopShooter(shooter); 


    public RobotContainer() {
        m_auto.setDefaultOption("2Note", BasicAuto);
        m_auto.addOption("LeftStartFromAngle", LeftStartFromAngle);
        m_auto.addOption("RightStartFromAngle", RightStartFromAngle);
        m_auto.addOption("Follow Note", FollowNoteAuto);
        m_auto.addOption("ThreeNote", ThreeNote);
        m_auto.addOption("LeftThreeNote", LeftThreeNote);
        m_auto.addOption("ShortThreeNote", ShortThreeNote);
        m_auto.addOption("getOuttaDodge", GetOuttaDodge);
        m_auto.addOption("RightShootGetOuttaDodge", RightShootGetOuttaDodge);
        m_auto.addOption("LeftShootGetOuttaDodge", LeftShootGetOuttaDodge);

        m_auto.addOption("RightAngleTwoNote", RightAngleTwoNote);
        m_auto.addOption("LeftAngleTwoNote", LeftAngleTwoNote);
        
        m_auto.addOption("OneNoteDAuto", OneNoteDAuto);
        m_auto.addOption("MultiNoteDAuto", MultiNoteDAuto);

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

        // driverXbox.a().onTrue(new InstantCommand(()->drivebase.setGyroOffset(rotation3dOffsetRads)));
                
        driverXbox.rightTrigger().onTrue(new InstantCommand(()->indexer.run(0.95))).onFalse(new InstantCommand(()->indexer.stop()));
        // driverXbox.rightTrigger().toggleOnTrue(indexer.run(1)).toggleOnFalse(indexer.stop());

        // driverXbox.leftTrigger().onTrue(shooter.fire(-1)).toggleOnFalse(shooter.stop());
        driverXbox.leftTrigger().toggleOnTrue(new InstantCommand(()->indexer.run(-0.7))).toggleOnFalse(new InstantCommand(()->indexer.stop()));

        driverXbox.leftBumper().toggleOnTrue(climber.runFoward()).onFalse(climber.stop());   
        driverXbox.rightBumper().toggleOnTrue(climber.runBackwards()).onFalse(climber.stop());   

        driverXbox.povUp().onTrue(new InstantCommand(()->intake.retract())).toggleOnFalse(new InstantCommand(()->intake.stopExtendRetract())); 
        driverXbox.povDown().onTrue(new InstantCommand(()->intake.extend())).toggleOnFalse(new InstantCommand(()->intake.stopExtendRetract())); 

        driverXbox.x().toggleOnTrue(new InstantCommand(()->intake.run())).toggleOnFalse(intake.stop());
        driverXbox.x().onTrue(new InstantCommand(()->indexer.run(Constants.indexerIntakeSpeed))).onFalse(new InstantCommand(()->indexer.stop()));

        driverXbox.b().toggleOnTrue(intake.reverse()).toggleOnFalse(intake.stop());


        driverXbox.y().toggleOnTrue(new InstantCommand(() -> shooter.fire(0.9))).onFalse(new InstantCommand(() -> shooter.fire(0)));
        
        // driverXbox.povRight().onTrue(new AutoClimb(driverXbox)).onFalse(climber.stop());
        driverXbox.povLeft().onTrue(new InstantCommand(()->shooter.setRPM(3200))).onFalse(shooter.stop());

        new JoystickButton(rightStick, 1).onTrue(new InstantCommand(drivebase::zeroGyro));
        new JoystickButton(leftStick, 1).onTrue(new InstantCommand(drivebase::zeroGyro));

        // new JoystickButton(leftStick, 2).(() -> Intake.eject(Constants.EJECT_SPEED)).whenReleased(() -> Intake.stop());

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
