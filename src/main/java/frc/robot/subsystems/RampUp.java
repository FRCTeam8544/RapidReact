// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class RampUp extends CommandBase {
  /** Creates a new RampUp. */
  
   //time (1/50 seconds) that the joystick has been pushed
   int runTime;
   //time (1/50 seconds) to accelerate to full speed
   int accelTime;
  //speed pulled from subsystem to accelerate to
   double targetSpeedPercentage;

   //for SmartDashboard
   String type;

  public RampUp(String system, int accel) {
    runTime = 0;
    accelTime = accel;
    targetSpeedPercentage = 0;
    type = system;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  public int getRunTime() {
    return runTime;
  }

  public int accelTime() {
    return accelTime;
  }

  public void setTarget(double value) {
    targetSpeedPercentage = value;
  }

  public void execute() {
    //moves output at a constant rate toward input
    if (((double) runTime/accelTime) > targetSpeedPercentage) {
      runTime--;
    } else if (((double) runTime/accelTime) < targetSpeedPercentage) {
      runTime++;
    }

    SmartDashboard.putNumber(type + " runTime" , runTime);
  }


  public double getSpeed() {
    return (double) runTime/accelTime;
  }
 
}
