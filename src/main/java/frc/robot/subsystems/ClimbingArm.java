// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ClimbingArm extends SubsystemBase {
  /** Creates a new ClimbingArm. */

  VictorSPX extentionMotor;
  double dPadValue;
  DoubleSolenoid armRotation;
  DigitalInput armLimitSwitch;

  public ClimbingArm() {
    extentionMotor = new VictorSPX(Constants.CLIMB_MOTOR_ID);
    extentionMotor.setNeutralMode(NeutralMode.Brake);
    armRotation = new DoubleSolenoid(Constants.PNEUMATICS_PCM_ID, Constants.INTAKE_PNEUMATICS_TYPE, 
   Constants.ARM_PNEUMATICS_IN, Constants.ARM_PNEUMATICS_OUT);
    armRotation.set(Value.kForward);  
    armLimitSwitch = new DigitalInput(1);
  }




  public void runExtentionMotor(double speed) {
    extentionMotor.set(ControlMode.PercentOutput, speed);
  }

  public void rotateArm() {
    armRotation.toggle();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    dPadValue = RobotContainer.HIDController.getPOV();
    
    if ((dPadValue >= 0 && dPadValue <= 10) || (dPadValue >= 350 && dPadValue < 360)) {
      runExtentionMotor(-1);
    }
    else if (dPadValue > 170 && dPadValue < 190) {
      if (armLimitSwitch.get()) {
        runExtentionMotor(1);
      } else {
        runExtentionMotor(0);
      }
    } else {
      runExtentionMotor(0);
    }
  }
}
