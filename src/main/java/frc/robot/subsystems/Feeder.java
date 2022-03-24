// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Feeder extends SubsystemBase {
  /** Creates a new AutoFeeder. */
  public VictorSPX feeder;
  DigitalInput feederLimitSwitch;
  boolean allowLimitChecks;
  boolean totalLimitOverride;

  
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
  }

  public void runFeederOut() {
    totalLimitOverride = true;
    feeder.set(VictorSPXControlMode.PercentOutput, -0.40);
  }

  public void stopFeeder() {
    totalLimitOverride = false;
    feeder.set(VictorSPXControlMode.PercentOutput, 0);
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
