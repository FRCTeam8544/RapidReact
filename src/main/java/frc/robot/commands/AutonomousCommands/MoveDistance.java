// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutonomousCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class MoveDistance extends CommandBase {

  DriveTrain a_drivetrain;
  

  double gearRatio = 10.71;
  double wheelDiameter = 6;
  double moveSpeed;


  double target;
  double distanceMoved;



  /** Creates a new Autonomous. */
  public MoveDistance(DriveTrain m_drivetrain, double speed) {
    a_drivetrain = m_drivetrain;
    moveSpeed = speed;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(a_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    a_drivetrain.resetEncoders();
    SmartDashboard.putNumber("encoder1", a_drivetrain.encoderDM1.getPosition());
    SmartDashboard.putNumber("encoder2", a_drivetrain.encoderDM2.getPosition());
    SmartDashboard.putNumber("rotations?", a_drivetrain.encoderDM1.getPosition()/10.71);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    distanceMoved = a_drivetrain.encoderDM1.getPosition()/gearRatio * wheelDiameter * Math.PI;
    if (distanceMoved < target) {
      a_drivetrain.tankDrive(moveSpeed, moveSpeed);
    } 
    if (distanceMoved > target) {
      a_drivetrain.tankDrive(-moveSpeed, -moveSpeed);
    }
    if (distanceMoved == target) {
      a_drivetrain.tankDrive(0, 0);
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    a_drivetrain.tankDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  public void moveRobot(String direction, double distance) {
    a_drivetrain.resetEncoders();
    if (direction.equals("forward")) {
      target = -distance;
    } else if (direction.equals("backward")) {
      target = distance;
    }
  }
  
}