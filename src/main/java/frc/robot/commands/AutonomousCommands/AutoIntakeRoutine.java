// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutonomousCommands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class AutoIntakeRoutine extends CommandBase {
  /** Creates a new AutoShootRoutine. */
  Intake a_intake;
  Timer intakeTimer;
  double inputedRollerPercentage;
  double inputedDelayTime; 
  boolean extend;


  public AutoIntakeRoutine(Double rollerPercentage, double delay, Intake intake, boolean willExtend) {
    // Use addRequirements() here to declare subsystem dependencies.

    a_intake = intake;
    intakeTimer = new Timer();
    inputedRollerPercentage = rollerPercentage;
    inputedDelayTime = delay;
    extend = willExtend;

    addRequirements(a_intake);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    intakeTimer.reset();
    intakeTimer.start();
    
    if (extend) {
      a_intake.intakeExtension.toggle();
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (intakeTimer.get() >= inputedDelayTime){
      a_intake.intakeMotor.set(ControlMode.PercentOutput,inputedRollerPercentage);
    }
    else {
      a_intake.intakeMotor.set(ControlMode.PercentOutput,0);      
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intakeTimer.reset();
    if (extend) {
      a_intake.intakeExtension.toggle();
    }
    //if (intakeTimer.get() >= 0.5) {
      a_intake.intakeMotor.set(ControlMode.PercentOutput, 0);
    //}
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
