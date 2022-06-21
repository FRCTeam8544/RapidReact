// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutonomousCommands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Feeder;

public class AutoFeedRoutine extends CommandBase {
  /** Creates a new AutoIntakeRoutine. */
  Feeder a_feeder;
 //Intake a_intake;
 Timer feedTimer;
  double inputedFeedPowerPercentage;
  double inputedDelayTime;

  public AutoFeedRoutine(double feedPowerPercentage, double delayTime, Feeder feed) {
    // Use addRequirements() here to declare subsystem dependencies.

  //  a_intake = intake;
    a_feeder = feed;
    inputedFeedPowerPercentage = feedPowerPercentage;
    inputedDelayTime = delayTime;

  //  addRequirements(a_intake);
    addRequirements(a_feeder);

    feedTimer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    feedTimer.reset();
    feedTimer.start();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (feedTimer.get() >= inputedDelayTime){
      a_feeder.feeder.set(ControlMode.PercentOutput, inputedFeedPowerPercentage);
    }
    else {
      a_feeder.feeder.set(ControlMode.PercentOutput, 0);
      
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    a_feeder.feeder.set(ControlMode.PercentOutput, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
