// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.REVLibError;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  /** Creates a new DriveTrain. */

  //creating variables for drive motors (# corresponds to ID #)
  CANSparkMax driveMotor1;
  CANSparkMax driveMotor2;
  CANSparkMax driveMotor3;
  CANSparkMax driveMotor4;

  //DM# = drive motor #
  public RelativeEncoder encoderDM1;
  public RelativeEncoder encoderDM2;
  RelativeEncoder encoderDM3;
  RelativeEncoder encoderDM4;

  //creating variables for speedcontroller groups
  //DM = Drive motors
  MotorControllerGroup leftDM;
  MotorControllerGroup rightDM;

  DifferentialDrive robotDrive;
  String driveType;
  double leftDrivePercentage;
  double rightDrivePercentage;

  public DriveTrain() {
    
    //speed controllers for drive wheels 
    driveMotor1 = new CANSparkMax(Constants.DRIVETRAIN_DMOTOR1_ID, Constants.DRIVETRAIN_DMOTOR1_MOTORTYPE);
    driveMotor2 = new CANSparkMax(Constants.DRIVETRAIN_DMOTOR2_ID, Constants.DRIVETRAIN_DMOTOR2_MOTORTYPE);
    driveMotor3 = new CANSparkMax(Constants.DRIVETRAIN_DMOTOR3_ID, Constants.DRIVETRAIN_DMOTOR3_MOTORTYPE);
    driveMotor4 = new CANSparkMax(Constants.DRIVETRAIN_DMOTOR4_ID, Constants.DRIVETRAIN_DMOTOR4_MOTORTYPE);

    //restoring default settings on drive motors to start with clean slate before applying new settings 
    driveMotor1.restoreFactoryDefaults();
    driveMotor2.restoreFactoryDefaults();
    driveMotor3.restoreFactoryDefaults();
    driveMotor4.restoreFactoryDefaults();

    //inverted lead motors 
    driveMotor1.setInverted(Constants.DRIVETRAIN_DMOTOR1_INVERSION);
    driveMotor2.setInverted(Constants.DRIVEMOTOR_DMOTOR2_INVERSION);

    //setting follow settings for 2 and 3 (2 follows 1 and 3 follows 4) &may need to change based on wiring
    driveMotor3.follow(driveMotor1);
    driveMotor4.follow(driveMotor2);

    //setting each encoder for each individual speed controller (make that variable equal to the encoder connected to that speed controller)
    encoderDM1 = driveMotor1.getEncoder(Constants.DRIVETRAIN_ENCODERDM1_ENCODERTYPE, Constants.DRIVETRAIN_ENCODERDM1_CPR);
    encoderDM2 = driveMotor1.getEncoder(Constants.DRIVETRAIN_ENCODERDM2_ENCODERTYPE, Constants.DRIVETRAIN_ENCODERDM2_CPR);
    encoderDM3 = driveMotor1.getEncoder(Constants.DRIVETRAIN_ENCODERDM3_ENCODERTYPE, Constants.DRIVETRAIN_ENCODERDM3_CPR);
    encoderDM4 = driveMotor1.getEncoder(Constants.DRIVETRAIN_ENCODERDM4_ENCODERTYPE, Constants.DRIVETRAIN_ENCODERDM4_CPR);

    //&
    rightDM = new MotorControllerGroup(driveMotor2, driveMotor4);
    leftDM = new MotorControllerGroup(driveMotor1, driveMotor3);

    robotDrive = new DifferentialDrive(leftDM, rightDM);

    driveType = "tank";
    setIdleMode(IdleMode.kCoast);

  }

  public void setDriveType(String type) {
    driveType = type;
  }

  public String getDriveType() {
    return driveType;
  }
  //method which takes a percentage for each side and passes it to the speedcontroller group called robotDrive
  public void tankDrive(double leftPercentage, double rightPercentage){
    leftDrivePercentage = leftPercentage;
    rightDrivePercentage = rightPercentage;
    robotDrive.tankDrive(leftPercentage, rightPercentage);
  }

  public void arcadeDrive(double xSpeed, double zRotation){
    robotDrive.arcadeDrive(xSpeed, zRotation);
  }
  public void resetEncoder(RelativeEncoder encoderName) {
    encoderName.setPosition(0);
  }

  public double currentRM(RelativeEncoder encoderName){
    return encoderName.getPosition();
  }

  public double leftMotorSpeed() {
    return leftDrivePercentage;
  }
  public double rightMotorSpeed() {
    return rightDrivePercentage;
  }

  public double distanceToEncoderPositionConversion(double inputedInches){
    double distance = inputedInches;
    double wheelDiameter = 6;
    double wheelCircumference = Math.PI * wheelDiameter;
    double gearRatio = 10.71;
    double wheelRevPerInch = 1 / wheelCircumference;

    return distance * wheelRevPerInch * gearRatio;
  }

  public double encoderPositionToDistanceConversion(RelativeEncoder encoderName){
    double encoderPosition = encoderName.getPosition();
    double wheelDiameter = 6;
    double wheelCircumference = Math.PI * wheelDiameter;
    double gearRatio = 10.71;
    double wheelRevPerInch = 1 / wheelCircumference;

    return encoderPosition / (wheelRevPerInch*gearRatio);
  }

  public void setIdleMode(IdleMode mode) {
    if (driveMotor1.setIdleMode(mode) != REVLibError.kOk) {
      System.out.println("Could not set idle mode on drive motor 1");
      System.exit(1);
    }
    if (driveMotor2.setIdleMode(mode) != REVLibError.kOk) {
      System.out.println("Could not set idle mode on drive motor 2");
      System.exit(1);
    }
    if (driveMotor3.setIdleMode(mode) != REVLibError.kOk) {
      System.out.println("Could not set idle mode on drive motor 3");
      System.exit(1);
    }
    if (driveMotor4.setIdleMode(mode) != REVLibError.kOk) {
      System.out.println("Could not set idle mode on drive motor 4");
      System.exit(1);
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
