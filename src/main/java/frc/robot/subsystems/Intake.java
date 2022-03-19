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
  private static Intake intake = null;
  public IntakeSTM intakeSTM;
  DoubleSolenoid intakeExtension;
  VictorSPX intakeMotor;
  ColorSensorV3 colorSensor;
  ColorMatch colorMatch;
  DigitalInput hopperLimitSwitch;
  

  /* Creates a new Intake. */

  

  public Intake() {
    intakeSTM = new IntakeSTM(this, false, false); // Setup state machines with default empty states. Should be fine even if full as the transitions will occur within a few ticks
    intakeExtension = new DoubleSolenoid(Constants.PNEUMATICS_PCM_ID, Constants.INTAKE_PNEUMATICS_TYPE, 
    Constants.INTAKE_PNEUMATICS_FORWARD, Constants.INTAKE_PNEUMATICS_REVERSE);
    intakeExtension.set(Value.kReverse);
    intakeMotor = new VictorSPX(Constants.INTAKE_MOTOR_ID);
    colorSensor = new ColorSensorV3(Port.kOnboard);
    hopperLimitSwitch = new DigitalInput(Constants.INTAKE_HOPPER_LIMITSWITCH);
  }

  public static Intake getInstance() {
    if (intake == null) {
      intake = new Intake();
    }
    return intake;
  }

  public void toggle() {
    intakeExtension.toggle();
  }

  public void setIntakeOut() {
    intakeExtension.set(Value.kForward);
  }

  public void setIntakeIn() {
    intakeExtension.set(Value.kReverse);
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

  public boolean isHopperFull() {
    return this.hopperLimitSwitch.get();
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // Get data from sensors. feeder_full is retrieved from the Shooter subsystem
    boolean feeder_full = Shooter.getInstance().isFeederFull();
    boolean hopper_full = this.isHopperFull();
    this.intakeSTM.tick(feeder_full, hopper_full);
  }
}
