// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class AutoFeeder extends SubsystemBase {
  /** Creates a new AutoFeeder. */
  public VictorSPX ar_feeder;
  public AutoFeeder() {
    ar_feeder = new VictorSPX(8);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}