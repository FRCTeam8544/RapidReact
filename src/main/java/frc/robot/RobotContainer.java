// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.AutonomousCommands.AutoClimbExtentionRoutine;
import frc.robot.commands.AutonomousCommands.AutoClimbRotateRoutine;
import frc.robot.commands.AutonomousCommands.AutoClimbRoutine;
import frc.robot.commands.AutonomousCommands.AutoFeedRoutine;
import frc.robot.commands.AutonomousCommands.AutoIntakeRoutine;
import frc.robot.commands.AutonomousCommands.AutoShootRoutine;
import frc.robot.commands.AutonomousCommands.AutonomousRoutine2ball;
import frc.robot.commands.AutonomousCommands.AutonomousRoutine;
import frc.robot.commands.AutonomousCommands.DriveDistance;
import frc.robot.commands.AutonomousCommands.MoveDistance;
import frc.robot.commands.AutonomousCommands.RecordedRoutine;
import frc.robot.commands.AutonomousCommands.RotateDegrees;
import frc.robot.commands.AutonomousCommands.WaitTime;
import frc.robot.subsystems.ClimbingArm;
import frc.robot.subsystems.AutoRecorder;
import frc.robot.subsystems.Feeder;
//import frc.robot.commands.TankDrive;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.commands.DriveControl;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  public static final Joystick joystick1 = new Joystick(Constants.ROBOTCONTAINER_JOYSTICK1_PORTNUMBER);
  public static final Joystick joystick2 = new Joystick(Constants.ROBOTCONTAINER_JOYSTICK2_PORTNUMBER);
  public static final Joystick HIDController = new Joystick(Constants.ROBOTCONTAINER_HIDCONTROLLER_PORTNUMBER);

  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final DriveTrain m_drivetrain = new DriveTrain();
  private final Shooter m_shooter = new Shooter();
  private final Intake m_intake = new Intake();
  private final ClimbingArm m_cArm = new ClimbingArm();
  private final Feeder m_feeder = new Feeder();
  private final AutoRecorder m_autoRecorder = new AutoRecorder(m_drivetrain, m_intake, m_feeder, m_shooter);
  
  

  //robot commands 
  private final MoveDistance m_moveDistance = new MoveDistance(m_drivetrain, 0.8);
  private final AutonomousRoutine2ball a_AutonomousRoutine2ball = new AutonomousRoutine2ball(m_drivetrain, m_feeder, m_shooter, m_intake);
  private final DriveDistance a_DriveDistance = new DriveDistance(60, 0.75, m_drivetrain);
  private final RotateDegrees a_RotateDegrees = new RotateDegrees(180, 0.5, m_drivetrain);
  private final AutoFeedRoutine a_AutoFeedRoutine = new AutoFeedRoutine(0.25, 3, m_feeder);
  private final AutoShootRoutine a_AutoShootRoutine = new AutoShootRoutine(0.65, 20, m_shooter);
  private final AutoIntakeRoutine a_IntakeRoutine = new AutoIntakeRoutine(-0.75, 0, m_intake, true);
  private final AutonomousRoutine a_AutonomousRoutine = new AutonomousRoutine(m_drivetrain, m_feeder, m_shooter, m_intake);
  private final RecordedRoutine a_RecordedRoutine = new RecordedRoutine(m_drivetrain, m_intake, m_feeder, m_shooter);
  private final AutoClimbExtentionRoutine a_AutoClimbExtentionRoutine = new AutoClimbExtentionRoutine(0.85, 4.1, m_cArm);
  private final AutoClimbRotateRoutine a_AutoClimbRotateRoutine = new AutoClimbRotateRoutine(0.1, m_cArm);
  private final AutoClimbRoutine a_AutoClimbRoutine = new AutoClimbRoutine(m_cArm);
  private SendableChooser<Command> toggle = new SendableChooser<>();
  //private final TankDrive m_tankDrive = new TankDrive(m_drivetrain);
  private final DriveControl m_driveControl = new DriveControl(m_drivetrain);
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    
    toggle.setDefaultOption("3-5 ball", a_AutonomousRoutine);
    toggle.addOption("2 ball", a_AutonomousRoutine2ball);
    toggle.addOption("recorded", a_RecordedRoutine);
    SmartDashboard.putData("Select Autonomous", toggle);
    SmartDashboard.putNumber("Recording Time", 750);
    SmartDashboard.putBoolean("Recording?", false);
    SmartDashboard.putString("Recording Name", "test");
    
    //defining default commands
    m_drivetrain.setDefaultCommand(m_driveControl);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton button1 = new JoystickButton(joystick1, Constants.ROBOTCONTAINER_JOYSTICK_BASE_MODESWITCH_ID);
    JoystickButton button2 = new JoystickButton(joystick2, Constants.ROBOTCONTAINER_JOYSTICK_BASE_MODESWITCH_ID);
    button1.and(button2).whenActive(() -> this.SwitchDriveMode());


    new JoystickButton(HIDController, Constants.ROBOTCONTAINER_BUTTON_NUMBER_X)
  .whenPressed(() -> m_shooter.setShooterSpeed("blue"));
  //.whenReleased(() -> m_shooter.stopShooter());

  new JoystickButton(HIDController, Constants.ROBOTCONTAINER_BUTTON_NUMBER_Y)
  .whenPressed(() -> m_shooter.setShooterSpeed("yellow"));
  //.whenReleased(() -> m_shooter.stopShooter());

  new JoystickButton(HIDController, Constants.ROBOTCONTAINER_BUTTON_NUMBER_A)
  .whenPressed(() -> m_shooter.setShooterSpeed("green"));
  //.whenReleased(() -> m_shooter.stopShooter()); 



  new JoystickButton(HIDController, Constants.ROBOTCONTAINER_BUTTON_PNEUMATIC_TOGGLE)
  .whenPressed(() -> m_intake.toggle());

  new JoystickButton(HIDController, Constants.ROBOTCONTAINER_CONTROLLER_INTAKE_MOTOR_OUT)
  .whenPressed(() -> m_intake.runMotorBackward())
  .whenReleased(() -> m_intake.stopMotor());

  new JoystickButton(HIDController, Constants.ROBOTCONTAINER_CONTROLLER_INTAKE_MOTOR_IN)
  .whenPressed(() -> m_intake.runMotorForward())
  .whenReleased(() -> m_intake.stopMotor());

  new JoystickButton(HIDController, Constants.ROBOTCONTAINER_CONTROLLER_SHOOTER_FEED_IN)
  .whenPressed(() -> m_feeder.runFeederInOverride())
  .whenReleased(() -> m_feeder.stopFeeder());

  new JoystickButton(HIDController, Constants.ROBOTCONTAINER_CONTROLLER_SHOOTER_FEED_OUT)
  .whenPressed(() -> m_feeder.runFeederOut())
  .whenReleased(() -> m_feeder.stopFeeder());
  
  new JoystickButton(HIDController, Constants.ROBOTCONTAINER_CONTROLLER_OVERRIDE_FEEDER)
  .whenPressed(() -> m_feeder.runFeederInOverride())
  .whenReleased(() -> m_feeder.stopFeeder());

  new JoystickButton(HIDController, Constants.ROBOTCONTAINER_CONTROLLER_CLIMB_ARM_ROTATE)
  .whenPressed(() -> m_cArm.rotateArm());

  //new JoystickButton(HIDController, Constants.ROBOTCONTAINER_CONTROLLER_CLIMB_ROUTINE)
  //.whenPressed(a_AutoClimbRoutine)
  //.whenReleased(() -> a_AutoClimbRoutine.cancel());
  
  }


  public void SwitchDriveMode() {
    if (m_drivetrain.getDriveType().equals("tank")) {
        m_drivetrain.setDriveType("arcade");
      } else {
        m_drivetrain.setDriveType("tank");
      }
      SmartDashboard.putString("DriveType", m_drivetrain.getDriveType());
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    /*Command auto = a_AutonomousRoutine;
    String[] options = {"3-5 ball", "2 ball"};
    SmartDashboard.putStringArray("Auto List", options);
    String autoName = SmartDashboard.getString("Auto Selector", "3-5 ball");
    if (autoName.equals("3-5 ball")) {
      auto = a_AutonomousRoutine;
    } else if (autoName.equals("2 ball")) {
      auto = a_AutonomousRoutine2ball;
    }
    return auto;*/
    return toggle.getSelected();
    //return a_AutoShootRoutine;
    

  }
}
