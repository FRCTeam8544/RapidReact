// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class RampUp extends CommandBase {
  /** Creates a new RampUp. */
  
   //time (1/50 seconds) that the joystick has been pushed
   int t;
   //time (1/50 seconds) to accelerate to full speed
   int accelTime;

   boolean active;

   //for SmartDashboard
   String type;

  public RampUp(String system, int accel) {
    t = 0;
    accelTime = accel;
    active = false;
    type = system;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  public int getT() {
    return t;
  }

  public int accelTime() {
    return accelTime;
  }


  public void setActive(Boolean value) {
    active = value;
    String s;
    s = "true";
    if (!active) s = "false";
    SmartDashboard.putString(type ,  s);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (!active) {
      t = 0;
    } else if (t < accelTime) {
      t++;
    }

    SmartDashboard.putNumber(type + " t" , t);
  }


  public double speedMultiplier() {
    return (double) t/accelTime;
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
