// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  public DoubleSolenoid intakeExtension;
  public VictorSPX intakeMotor;
  ColorSensorV3 colorSensor;
  ColorMatch colorMatch;
  double speed;

  /* Creates a new Intake. */

  

  public Intake() {
   intakeExtension = new DoubleSolenoid(Constants.PNEUMATICS_PCM_ID, Constants.INTAKE_PNEUMATICS_TYPE, 
   Constants.INTAKE_PNEUMATICS_FORWARD, Constants.INTAKE_PNEUMATICS_REVERSE);
   intakeExtension.set(Value.kForward);
    intakeMotor = new VictorSPX(Constants.INTAKE_MOTOR_ID);
    speed = 0;
  }

  public void toggle() {
    intakeExtension.toggle();
  }

  public void runMotorForward() {
    intakeMotor.set(VictorSPXControlMode.PercentOutput, -0.6);
    speed = -0.6;
  }

  public void stopMotor() {
    intakeMotor.set(VictorSPXControlMode.PercentOutput, 0);
    speed = 0;
  }

  public void runMotorBackward() {
    intakeMotor.set(VictorSPXControlMode.PercentOutput, 0.6);
    speed = 0.6;
  }

  public double motorSpeed() {
    return speed;
  }
  public Value extentionState() {
    return intakeExtension.get();
  }




  @Override
  public void periodic() {
    // This method will be called once per scheduler run
 

  }
}
