// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ClimbingArm extends SubsystemBase {
  /** Creates a new ClimbingArm. */

  VictorSPX extentionMotor;
  double dPadValue;
  DigitalInput armLimitSwitch;

  public ClimbingArm() {
    extentionMotor = new VictorSPX(Constants.CLIMB_MOTOR_ID);
    armLimitSwitch = new DigitalInput(1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    dPadValue = RobotContainer.HIDController.getPOV();
    
    if ((dPadValue >= 0 && dPadValue <= 10) || (dPadValue >= 350 && dPadValue < 360)) {
      extentionMotor.set(ControlMode.PercentOutput, -0.5);
    }
    else if (dPadValue > 170 && dPadValue < 190) {
      if (armLimitSwitch.get()) {
      extentionMotor.set(ControlMode.PercentOutput, 0.5);
      } else {
        extentionMotor.set(ControlMode.PercentOutput, 0);
      }
    } else {
      extentionMotor.set(ControlMode.PercentOutput, 0);
    }
  }
}
