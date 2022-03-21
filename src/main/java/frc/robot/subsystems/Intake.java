// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  DoubleSolenoid intakeExtension;
  VictorSPX intakeMotor;
  ColorSensorV3 colorSensor;
  ColorMatch colorMatch;
  

  /* Creates a new Intake. */

  

  public Intake() {
   intakeExtension = new DoubleSolenoid(Constants.PNEUMATICS_PCM_ID, Constants.INTAKE_PNEUMATICS_TYPE, 
   Constants.INTAKE_PNEUMATICS_FORWARD, Constants.INTAKE_PNEUMATICS_REVERSE);
   intakeExtension.set(Value.kReverse);
    intakeMotor = new VictorSPX(Constants.INTAKE_MOTOR_ID);
    colorSensor = new ColorSensorV3(Port.kOnboard);

  }

  public void toggle() {
    intakeExtension.toggle();
  }

  public void runMotorForward() {
    intakeMotor.set(VictorSPXControlMode.PercentOutput, -0.75);
  }

  public void stopMotor() {
    intakeMotor.set(VictorSPXControlMode.PercentOutput, 0);
  }

  public void runMotorBackward() {
    intakeMotor.set(VictorSPXControlMode.PercentOutput, 0.75);
  }




  @Override
  public void periodic() {
    // This method will be called once per scheduler run
 

  }
}
