// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutonomousCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimbingArm;

public class AutoClimbExtentionRoutine extends CommandBase {
  /** Creates a new AutoClimbExtentionRoutine. */

  Timer extentionTimer;
  double inputedExtentionPowerPercentage;
  double inputedExtentionTime;
  ClimbingArm a_cArm;

  public AutoClimbExtentionRoutine(double extentionSpeed, double extentionTime, ClimbingArm cArm) {
    inputedExtentionPowerPercentage = extentionSpeed;
    inputedExtentionTime = extentionTime;
    a_cArm = cArm;
    extentionTimer = new Timer();

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(a_cArm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    extentionTimer.reset();
    extentionTimer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    a_cArm.runExtentionMotor(inputedExtentionPowerPercentage);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    a_cArm.runExtentionMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (extentionTimer.get() >= inputedExtentionTime);
  }
}
