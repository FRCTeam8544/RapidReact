// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.TankDrive;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.commands.ArcadeDrive;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
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
  

  //robot commands 
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  private final TankDrive m_tankDrive = new TankDrive(m_drivetrain);
  private final ArcadeDrive m_arcadeDrive = new ArcadeDrive(m_drivetrain);
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    //defining default commands
    m_drivetrain.setDefaultCommand(m_arcadeDrive);
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

  }


  public void SwitchDriveMode() {
    if (m_arcadeDrive.getDriveType().equals("tank")) {
        m_arcadeDrive.setDriveType("arcade");
        SmartDashboard.putString("DriveType", "arcade");
      } else {
        m_arcadeDrive.setDriveType("tank");
        SmartDashboard.putString("DriveType", "arcade");
      }
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
