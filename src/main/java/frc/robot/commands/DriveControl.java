// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.RampUp;

public class DriveControl extends CommandBase {
  /** Creates a new TankDrive. */
  DriveTrain m_driveTrain;
  //rampup instances for separate drivetrain values
  //tankdrive: right/left, arcadedrive: movement/rotation
  RampUp rampup1;
  RampUp rampup2;
  //tankdrive values
  double leftJoystickPercentage;
  double rightJoystickPercentage;
  //arcadedrive values
  double xJoystickSpeed;
  double zJoystickRotation;
 


  public DriveControl(DriveTrain drive) {
    // Use addRequirements() here to declare subsystem dependencies.
    rampup1 = new RampUp("DriveControl1", 25);
    rampup2 = new RampUp("DriveControl2", 25);
    m_driveTrain = drive;
    addRequirements(drive);
  }

  

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}



  /*public double rightDrivePercentage() {
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
  }*/


  //arbitrary value 0.1 used for stationary robot
  public boolean isMoving() {
    if (m_driveTrain.getDriveType().equals("arcade")) {
      if (Math.abs(xJoystickSpeed) < 0.1 
      && Math.abs(zJoystickRotation) < 0.1) {
        return false;
      }
    }
    if (m_driveTrain.getDriveType().equals("tank")) {
      if (Math.abs(leftJoystickPercentage) < 0.1 
      && Math.abs(rightJoystickPercentage) < 0.1) {
        return false;
      }
    }
    return true;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (!isMoving()) {
      //ensures that values are rounded down when low enough
      rampup1.setTarget(0);
      rampup2.setTarget(0);
    } 
    
    if (m_driveTrain.getDriveType().equals("arcade")) {
      xJoystickSpeed = RobotContainer.joystick1.getY();
      zJoystickRotation = RobotContainer.joystick1.getX();
      //set targets to gradually increase to
      rampup1.setTarget(xJoystickSpeed);
      rampup2.setTarget(-zJoystickRotation);
      //set speed to value from rampup
    m_driveTrain.arcadeDrive(rampup1.getSpeed(), rampup2.getSpeed());
    } else {
      rightJoystickPercentage = RobotContainer.joystick1.getRawAxis(1);
    leftJoystickPercentage = RobotContainer.joystick2.getRawAxis(1);
    //set targets to gradually increase to
    rampup1.setTarget(rightJoystickPercentage);
    rampup2.setTarget(leftJoystickPercentage);
    //set speed to value from rampup
    m_driveTrain.tankDrive(rampup2.getSpeed(), rampup1.getSpeed());
    }
    //run math
    rampup1.execute();
    rampup2.execute();
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveTrain.tankDrive(0, 0);
    m_driveTrain.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
