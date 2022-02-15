// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class DriveControl extends CommandBase {
  /** Creates a new TankDrive. */
  DriveTrain m_driveTrain;
  RampUp rampup;
  double leftJoystickPercentage;
  double rightJoystickPercentage;
  //"tank" or "arcade"
  String driveType;

  public DriveControl(DriveTrain drive) {
    // Use addRequirements() here to declare subsystem dependencies.
    rampup = new RampUp("DriveControl", 100);
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



  public double rightDrivePercentage() {
    double Y = RobotContainer.joystick1.getY();
    double X = RobotContainer.joystick1.getX();

   
      if (Y > 0) return (Y*Math.abs(Y)-X*Math.abs(X));
      if (Y < 0) return (Y*Math.abs(Y)+X*Math.abs(X));
      if (Y == 0) return X*Math.abs(X);
   return Y*Math.abs(Y);
  }

  public double leftDrivePercentage() {
    double Y = RobotContainer.joystick1.getY();
    double X = RobotContainer.joystick1.getX();
    
      if (Y > 0) return (Y*Math.abs(Y)+X*Math.abs(X));
      if (Y < 0) return (Y*Math.abs(Y)-X*Math.abs(X));
      if (Y == 0) return -X*Math.abs(X);
  
   return Y*Math.abs(Y);
  }

  //arbitrary value 0.02 used for stationary robot
  public boolean isMoving() {
    if (driveType.equals("arcade")) {
      if (Math.abs(rightDrivePercentage()) < 0.1 
      && Math.abs(leftDrivePercentage()) < 0.1) {
        return false;
      }
    }
    if (driveType.equals("tank")) {
      if (Math.abs(RobotContainer.joystick1.getRawAxis(1)) < 0.1 
      && Math.abs(RobotContainer.joystick2.getRawAxis(1)) < 0.1) {
        return false;
      }
    }
    return true;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    rampup.setActive(isMoving());
    rampup.execute();
    if (driveType.equals("arcade")) {
    leftJoystickPercentage = leftDrivePercentage() * rampup.speedMultiplier();
    rightJoystickPercentage = rightDrivePercentage() *rampup.speedMultiplier();
    } else {
      rightJoystickPercentage = RobotContainer.joystick1.getRawAxis(1) * rampup.speedMultiplier();
    leftJoystickPercentage = RobotContainer.joystick2.getRawAxis(1) * rampup.speedMultiplier();
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
