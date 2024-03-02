package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

// import static frc.robot.RobotContainer.limelight;
import static frc.robot.RobotContainer.photon;

import java.util.List;
import swervelib.SwerveController;
import swervelib.math.SwerveMath;

/**
 * An example command that uses an example subsystem.
 */
public class ChaseNote extends Command
{

    private final SwerveSubsystem swerve;
    private final Double vX;
    private double vY = 0;
    private double PID = 0; 
    double kP = 0.005;
    double kI = 0.0;
    double kD = 0.002;

    PIDController pid = new PIDController(kP, kI, kD);

    private final Double headingHorizontal, headingVertical;
    private boolean initRotation = false;

 
  public ChaseNote(SwerveSubsystem swerve, Double vX, Double headingHorizontal,Double headingVertical)
    {
        this.swerve = swerve;
        this.vX = vX;
        this.headingHorizontal = headingHorizontal;
        this.headingVertical = headingVertical;

        addRequirements(swerve);
    }

    @Override
    public void initialize()
    {
        initRotation = true;
        photon.setCamera(1);
        photon.setPipline(0);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute()
    {
        PID = pid.calculate(photon.getYaw(), 0);
        Math.max(-1, Math.min(1, PID));

        vY = PID;

        // Get the desired chassis speeds based on a 2 joystick module.
        ChassisSpeeds desiredSpeeds = swerve.getTargetSpeeds(vX, vY,headingHorizontal,headingVertical);

        // Prevent Movement After Auto
        if(initRotation)
        {
        
            if(headingHorizontal == 0 && headingVertical == 0)
            {
                // Get the curretHeading
                Rotation2d firstLoopHeading = swerve.getHeading();
            
                // Set the Current Heading to the desired Heading
                desiredSpeeds = swerve.getTargetSpeeds(0, 0, firstLoopHeading.getSin(), firstLoopHeading.getCos());
            }
            //Dont Init Rotation Again
            initRotation = false;
        }

        // Limit velocity to prevent tippy
        Translation2d translation = SwerveController.getTranslation2d(desiredSpeeds);
        translation = SwerveMath.limitVelocity(translation, swerve.getFieldVelocity(), swerve.getPose(),Constants.LOOP_TIME, Constants.ROBOT_MASS, List.of(Constants.CHASSIS),swerve.getSwerveDriveConfiguration());
        SmartDashboard.putNumber("LimitedTranslation", translation.getX());
        SmartDashboard.putString("Translation", translation.toString());

        // Make the robot move
        swerve.drive(translation, desiredSpeeds.omegaRadiansPerSecond, true);

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted)
    {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished()
    {
        return false;
    }
}
