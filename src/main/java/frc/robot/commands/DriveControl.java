// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class ArcadeDrive extends CommandBase {
  /** Creates a new TankDrive. */
  DriveTrain m_driveTrain;
  double leftJoystickPercentage;
  double rightJoystickPercentage;
  String driveType;

  public ArcadeDrive(DriveTrain drive) {
    // Use addRequirements() here to declare subsystem dependencies.
    
    driveType = "tank";
    m_driveTrain = drive;
    addRequirements(drive);
  }

  public void setDriveType(String type) {
    driveType = type;
  }
  public String getDriveType() {
    return driveType;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}



  public double leftDrivePercentage() {
    double Y = RobotContainer.joystick1.getY();
    double X = RobotContainer.joystick1.getX();

   
      if (Y > 0) return (Y*Math.abs(Y)-X*Math.abs(X));
      if (Y < 0) return (Y*Math.abs(Y)+X*Math.abs(X));
      if (Y == 0) return X*Math.abs(X);
   return Y*Math.abs(Y);
  }

  public double rightDrivePercentage() {
    double Y = RobotContainer.joystick1.getY();
    double X = RobotContainer.joystick1.getX();
    
      if (Y > 0) return (Y*Math.abs(Y)+X*Math.abs(X));
      if (Y < 0) return (Y*Math.abs(Y)-X*Math.abs(X));
      if (Y == 0) return -X*Math.abs(X);
  
   return Y*Math.abs(Y);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (driveType.equals("arcade")) {
    leftJoystickPercentage = leftDrivePercentage();
    rightJoystickPercentage = rightDrivePercentage();
    } else {
      leftJoystickPercentage = RobotContainer.joystick1.getRawAxis(1);
    rightJoystickPercentage = RobotContainer.joystick2.getRawAxis(1);
    }

    m_driveTrain.tankDrive(leftJoystickPercentage, rightJoystickPercentage);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveTrain.tankDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
