// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.RampUp;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  CANSparkMax shooterWheel1;
  CANSparkMax shooterWheel2;

  RelativeEncoder shooter1Encoder;
  RelativeEncoder shooter2Encoder;

  RampUp rampup;

  String shooterSpeed;

  public Shooter() {
    shooterWheel2 = new CANSparkMax(Constants.SHOOTER_MOTOR2_ID, Constants.SHOOTER_MOTOR2_MOTORTYPE);
    shooterWheel1 = new CANSparkMax(Constants.SHOOTER_MOTOR1_ID, Constants.SHOOTER_MOTOR1_MOTORTYPE);

    shooterWheel2.restoreFactoryDefaults();
    shooterWheel1.restoreFactoryDefaults();

    shooterWheel2.setInverted(Constants.SHOOTER_MOTOR2_INVERSION);
    shooterWheel1.setInverted(Constants.SHOOTER_MOTOR1_INVERSION);

    shooter2Encoder = shooterWheel2.getEncoder();
    shooter1Encoder = shooterWheel1.getEncoder();

    rampup = new RampUp("Shooter", 100);
    
    
  }

  //method to set specific speeds
  //colors correspond to the color of the button (I may change this because it might be a stupid way of doing it)
  //each if statement corresponds to a different speed and button --> robotContainer for button mappings
  //@change shooter speeds to correct values
  //@
  public void setShooterSpeed(String button){
    shooterSpeed = button;
  }

  public void setPercentPower(double speed){
    shooterWheel2.set(speed);
    shooterWheel1.set(speed);
  }
  //sets both shooter speed controllers to zero (i.e. stopping the motors)
  public void stopShooter(){
    shooterWheel2.set(0);
    shooterWheel1.set(0);
    rampup.setActive(false);
    shooterSpeed = "";
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    rampup.execute();
    
      if (shooterSpeed == "red"){
        rampup.setActive(true);
        shooterWheel2.set(.2 * rampup.speedMultiplier());
        shooterWheel1.set(.2 * rampup.speedMultiplier());
      }
      else if (shooterSpeed == "blue"){
        rampup.setActive(true);
        shooterWheel2.set(.5 * rampup.speedMultiplier());
        shooterWheel1.set(.5 * rampup.speedMultiplier());
      }
      else if (shooterSpeed == "green"){
        rampup.setActive(true);
        shooterWheel2.set(.8 * rampup.speedMultiplier());
        shooterWheel1.set(.8 * rampup.speedMultiplier());
      }
      else if (shooterSpeed == "yellow"){
        rampup.setActive(true);
        shooterWheel2.set(1 * rampup.speedMultiplier());
        shooterWheel1.set(1 * rampup.speedMultiplier());
      }
      else {
        SmartDashboard.putString("SetShooterSpeed: ", "No button pressed");
      }
    
  }
}