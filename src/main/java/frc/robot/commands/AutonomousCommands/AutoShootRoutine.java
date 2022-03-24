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
  double commandInSecconds; 

  public AutoShootRoutine(Double shooterPercentage, double lenghtOfCommand, Shooter shoot) {
    // Use addRequirements() here to declare subsystem dependencies.

    a_shooter = shoot;
    shootTimer = new Timer();
    inputedShooterPercentage = shooterPercentage;
    commandInSecconds = lenghtOfCommand;

    addRequirements(a_shooter);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    shootTimer.reset();
    shootTimer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    a_shooter.shooterWheel2.set(inputedShooterPercentage);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    a_shooter.shooterWheel2.set(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (shootTimer.get() >= commandInSecconds);
  }
}
