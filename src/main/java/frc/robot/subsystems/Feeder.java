// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Feeder extends SubsystemBase {
  /** Creates a new AutoFeeder. */
  public VictorSPX feeder;
  DigitalInput feederLimitSwitch;
  boolean totalLimitOverride;
  double speed;
 
  
  public Feeder() {
  feeder = new VictorSPX(Constants.SHOOTER_FEED_MOTOR_ID);

  feederLimitSwitch = new DigitalInput(0);
  totalLimitOverride = false;
  
  }

  public void runFeederIn() {
    if (feederLimitSwitch.get()) {
      feeder.set(VictorSPXControlMode.PercentOutput, 0.40); 
    }
  }

  public void runFeederInOverride() {
      totalLimitOverride = true;
    feeder.set(VictorSPXControlMode.PercentOutput, 0.75);
    SmartDashboard.putString("Feeder: ", "Running");
    speed = 0.75;
  }

  public void runFeederOut() {
    totalLimitOverride = true;
    feeder.set(VictorSPXControlMode.PercentOutput, -0.40);
    SmartDashboard.putString("Feeder: ", "Reverse");
    speed = -0.40;
  }

  public void stopFeeder() {
    totalLimitOverride = false;
    feeder.set(VictorSPXControlMode.PercentOutput, 0);
    SmartDashboard.putString("Feeder: ", "Stopped");
    speed = 0;
  }

  public double feederSpeed() {
    return speed;
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    if (!totalLimitOverride) 
    if (!feederLimitSwitch.get()) {
      stopFeeder();
    }
  }  
}
