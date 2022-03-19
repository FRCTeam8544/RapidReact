// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
//import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  private static Shooter shooter = null;
  /** Creates a new Shooter. */
  CANSparkMax shooterWheel1;
  CANSparkMax shooterWheel2;

  RelativeEncoder shooter1Encoder;
  RelativeEncoder shooter2Encoder;

  VictorSPX feeder;
  DigitalInput feederLimitSwitch;
  boolean allowLimitChecks;
  boolean totalLimitOverride;

  //SparkMaxPIDController shooterPIDController;

  /*double kP;
  double kI;
  double kD;
  double kIz;
  double kFF;
  double maxOutput;
  double minOutput;
  double maxRPM;

  double p;
  double i;
  double d;
  double iz;
  double ff;
  double max;
  double min;*/
  double setPoint;

  public Shooter() {
    shooterWheel2 = new CANSparkMax(Constants.SHOOTER_MOTOR2_ID, Constants.SHOOTER_MOTOR2_MOTORTYPE);
    shooterWheel1 = new CANSparkMax(Constants.SHOOTER_MOTOR1_ID, Constants.SHOOTER_MOTOR1_MOTORTYPE);

    shooterWheel2.restoreFactoryDefaults();
    shooterWheel1.restoreFactoryDefaults();

    shooterWheel2.setInverted(Constants.SHOOTER_MOTOR2_INVERSION);

    shooterWheel1.follow(shooterWheel2, !Constants.SHOOTER_MOTOR2_INVERSION);

    shooter2Encoder = shooterWheel2.getEncoder();
    shooter1Encoder = shooterWheel1.getEncoder();

    feeder = new VictorSPX(Constants.SHOOTER_FEED_MOTOR_ID);

    //shooterPIDController = shooterWheel2.getPIDController();

    /*kP = 0.00005;
    kI = 0;
    kD = 0;
    kIz = 0;
    kFF = 0.000015;
    maxOutput = 1;
    minOutput = 0;*/
    setPoint = 0;

    /*shooterPIDController.setP(kP);
    shooterPIDController.setI(kI);
    shooterPIDController.setD(kD);
    shooterPIDController.setIZone(kIz);
    shooterPIDController.setFF(kFF);
    shooterPIDController.setOutputRange(minOutput, maxOutput);

    SmartDashboard.putNumber("P Gain: ", kP);
    SmartDashboard.putNumber("I Gain: ", kI);
    SmartDashboard.putNumber("D Gain: ", kD);
    SmartDashboard.putNumber("I Zone: ", kIz);
    SmartDashboard.putNumber("Feed Forward Gain: ", kFF);
    SmartDashboard.putNumber("Min Output: " , minOutput);
    SmartDashboard.putNumber("Max Output: ", maxOutput);*/

    
    
    feederLimitSwitch = new DigitalInput(Constants.SHOOTER_FEEDER_LIMITSWITCH);
    totalLimitOverride = false;

  }

  public static Shooter getInstance() {
    if (shooter == null) {
      shooter = new Shooter();
    }
    return shooter;
  }

  //method to set specific speeds
  //colors correspond to the color of the button (I may change this because it might be a stupid way of doing it)
  //each if statement corresponds to a different speed and button --> robotContainer for button mappings
  //@change shooter speeds to correct values
  //@
  public void setShooterSpeed(String button){
    if (button == "blue"){
      setPoint = 0.5 ;
    }
    else if (button == "green"){
      setPoint = 0;
    }
    else if (button == "yellow"){
      setPoint = 1 ;
    }
    else {
      SmartDashboard.putString("SetShooterSpeed: ", "No button pressed");
    }
}

  //sets both shooter speed controllers to zero (i.e. stopping the motors)
  /*public void stopShooter(){
    shooterWheel2.set(0);
  }*/

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

public boolean isFeederFull() {
    return this.feederLimitSwitch.get();
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    /*p = SmartDashboard.getNumber("P Gain: ", 0);
    i = SmartDashboard.getNumber("I Gain: ", 0);
    d = SmartDashboard.getNumber("D Gain: ", 0);
    iz = SmartDashboard.getNumber("I Zone: ", 0);
    ff = SmartDashboard.getNumber("Feed Forward: ", 0);
    max = SmartDashboard.getNumber("Max: ", 0);
    min = SmartDashboard.getNumber("Min: ", 0);

    if ((p != kP)) {
      shooterPIDController.setP(p);
      kP = p;
    }
    if ((i != kI)) {
      shooterPIDController.setI(i);
      kI = i;
    }
    if ((d != kD)) {
      shooterPIDController.setD(d);
      kD = d;
    }
    if ((iz != kIz)) {
      shooterPIDController.setIZone(iz);
      kIz = iz;
    }
    if ((ff != kFF)) {
      shooterPIDController.setFF(ff);
      kFF = ff;
    }
    if ((max != maxOutput || min != minOutput)) {
      shooterPIDController.setOutputRange(min, max);
      maxOutput = max;
      minOutput = min;
    }

    

    shooterPIDController.setReference(setPoint, CANSparkMax.ControlType.kSmartVelocity);

    SmartDashboard.putNumber("SetPoint", setPoint);
    SmartDashboard.putNumber("Processed Variable: ", shooter2Encoder.getVelocity());*/

    shooterWheel2.set(setPoint);
    
    if (!totalLimitOverride) 
        if (!feederLimitSwitch.get()) {
          stopFeeder();
        }
  }  
}