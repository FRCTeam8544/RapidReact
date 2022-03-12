// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import javax.swing.text.html.HTMLDocument.HTMLReader.HiddenAction;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ExampleCommand;
//import frc.robot.commands.TankDrive;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.commands.DriveControl;
import edu.wpi.first.wpilibj2.command.Command;
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
  private final Intake m_intake = Intake.getInstance();
 
  

  //robot commands 
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  //private final TankDrive m_tankDrive = new TankDrive(m_drivetrain);
  private final DriveControl m_driveControl = new DriveControl(m_drivetrain);
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

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
  .whenPressed(() -> m_shooter.setShooterSpeed("blue"))
  .whenReleased(() -> m_shooter.stopShooter());

  new JoystickButton(HIDController, Constants.ROBOTCONTAINER_BUTTON_NUMBER_Y)
  .whenPressed(() -> m_shooter.setShooterSpeed("yellow"))
  .whenReleased(() -> m_shooter.stopShooter());

  new JoystickButton(HIDController, Constants.ROBOTCONTAINER_BUTTON_NUMBER_A)
  .whenPressed(() -> m_shooter.setShooterSpeed("green"))
  .whenReleased(() -> m_shooter.stopShooter()); 



  new JoystickButton(HIDController, Constants.ROBOTCONTAINER_BUTTON_PNEUMATIC_TOGGLE)
  .whenPressed(() -> m_intake.toggle());


  // TODO: Update the intake calls to work with the state machine as opposed to direct control
  new JoystickButton(HIDController, Constants.ROBOTCONTAINER_CONTROLLER_INTAKE_MOTOR_OUT)
  .whenPressed(() -> m_intake.runMotorBackward())
  .whenReleased(() -> m_intake.stopMotor());

  new JoystickButton(HIDController, Constants.ROBOTCONTAINER_CONTROLLER_INTAKE_MOTOR_IN)
  .whenPressed(() -> m_intake.runMotorForward())
  .whenReleased(() -> m_intake.stopMotor());

  new JoystickButton(HIDController, Constants.ROBOTCONTAINER_CONTROLLER_SHOOTER_FEED_IN)
  .whenPressed(() -> m_shooter.runFeederIn())
  .whenReleased(() -> m_shooter.stopFeeder());

  new JoystickButton(HIDController, Constants.ROBOTCONTAINER_CONTROLLER_SHOOTER_FEED_OUT)
  .whenPressed(() -> m_shooter.runFeederOut())
  .whenReleased(() -> m_shooter.stopFeeder());
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
    return m_autoCommand;
  }
}
