// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutonomousCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class AutoShootRoutine extends CommandBase {
  /** Creates a new AutoShootRoutine. */
  Shooter a_shooter;
  Timer shootTimer;
  double inputedShooterPercentage;

  public AutoShootRoutine(Double shooterPercentage, Shooter shoot) {
    // Use addRequirements() here to declare subsystem dependencies.

    a_shooter = shoot;
    shootTimer = new Timer();
    inputedShooterPercentage = shooterPercentage;

    addRequirements(a_shooter);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    shootTimer.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    a_shooter.shooterWheel1.set(inputedShooterPercentage);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
