// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import edu.wpi.first.util.datalog.DataLog;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class AutoRecorder extends SubsystemBase {
  /** Creates a new DataLogger. */
  DataLog log;
  ArrayList<Double> driveRightInstructions;
  ArrayList<Double> driveLeftInstructions;
  ArrayList<Double> feedInstructions;
  ArrayList<Double> intakeMotorInstructions;
  ArrayList<Double> intakeExtentionInstructions;
  ArrayList<Double> shooterMotorInstructions;
  DriveTrain m_driveTrain;
  Intake m_intake;
  Feeder m_feeder;
  Shooter m_shooter;
  int time;
  double timeToMeasure;
  boolean recording;
  String recordingName;
  String saveDirectory;



  public AutoRecorder(DriveTrain drive, Intake intake, Feeder feed, Shooter shoot) {
    driveRightInstructions = new ArrayList<>();
    driveLeftInstructions = new ArrayList<>();
    feedInstructions =  new ArrayList<>();
    intakeMotorInstructions = new ArrayList<>();
    intakeExtentionInstructions = new ArrayList<>();
    shooterMotorInstructions = new ArrayList<>();
    time = 0;
    timeToMeasure = SmartDashboard.getNumber("Recording Time", 750);
    recording = SmartDashboard.getBoolean("Recording?", false);
    recordingName = SmartDashboard.getString("Recording Name", "test");
    saveDirectory = "/u/recordings/";
    m_driveTrain = drive;
    m_intake = intake;
    m_feeder = feed;
    m_shooter = shoot; 
  }
  

  @Override
  public void periodic() {
    recording = SmartDashboard.getBoolean("Recording?", false);
    timeToMeasure = SmartDashboard.getNumber("Recording Time", 750);
    if (recording) {
      if (time == 0) {
        SmartDashboard.putString("Record Status", "recording");
      }
      if (time < timeToMeasure) {
        driveRightInstructions.add(m_driveTrain.rightMotorSpeed());
        driveLeftInstructions.add(m_driveTrain.leftMotorSpeed());
        feedInstructions.add(m_feeder.feederSpeed());
        intakeMotorInstructions.add(m_intake.motorSpeed());
        shooterMotorInstructions.add(m_shooter.setPoint);
        if (m_intake.extentionState() == Value.kReverse) {
          intakeExtentionInstructions.add(1.0);
        } else {
          intakeExtentionInstructions.add(0.0);
        }
        time++;
      }
      if (time == timeToMeasure) {
        writeListsToFile();
        SmartDashboard.putString("Record Status", "done");
        SmartDashboard.putBoolean("Recording?", false);
      }
    } else {
      time = 0;
      SmartDashboard.putString("Record Status", "off");
    }


    // This method will be called once per scheduler run
  }


  public void writeListsToFile() {
    recordingName = SmartDashboard.getString("Recording Name", "test");
        File driveLeftFile = new File(saveDirectory + recordingName + "/driveLeft.txt");
        File driveRightFile = new File(saveDirectory + recordingName + "/driveRight.txt");
        File feedFile = new File(saveDirectory + recordingName + "/feed.txt");
        File intakeMotorFile = new File(saveDirectory + recordingName + "/intakeMotor.txt");
        File intakeExtentionFile = new File(saveDirectory + recordingName + "/intakeExtention.txt");
        File shooterFile = new File(saveDirectory + recordingName + "/shooter.txt");

        if (driveLeftFile.exists()) {
          driveLeftFile.delete();
        }
        if (driveRightFile.exists()) {
          driveRightFile.delete();
        }
        if (feedFile.exists()) {
          feedFile.delete();
        }
        if (intakeMotorFile.exists()) {
          intakeMotorFile.delete();
        }
        if (intakeExtentionFile.exists()) {
          intakeExtentionFile.delete();
        }
        if (shooterFile.exists()) {
          shooterFile.delete();
        }

        driveLeftFile.getParentFile().mkdirs();
        driveRightFile.getParentFile().mkdirs();
        feedFile.getParentFile().mkdirs();
        intakeMotorFile.getParentFile().mkdirs();
        shooterFile.getParentFile().mkdirs();
        intakeExtentionFile.getParentFile().mkdirs();
        try {
          

          FileWriter writer = new FileWriter(saveDirectory + recordingName + "/driveLeft.txt");
          for (double i : driveLeftInstructions) {
            writer.write(i + "\n");
          }
          writer.close();
          writer = new FileWriter(saveDirectory + recordingName + "/driveRight.txt");
          for (double i : driveRightInstructions) {
            writer.write(i + "\n");
          }
          writer.close();
          writer = new FileWriter(saveDirectory + recordingName + "/feed.txt");
          for (double i : feedInstructions) {
            writer.write(i + "\n");
          }
          writer.close();
          writer = new FileWriter(saveDirectory + recordingName + "/intakeMotor.txt");
          for (double i : intakeMotorInstructions) {
            writer.write(i + "\n");
          }
          writer.close();
          writer = new FileWriter(saveDirectory + recordingName + "/intakeExtention.txt");
          for (double i : intakeExtentionInstructions) {
            writer.write(i + "\n");
          }
          writer.close();

          writer = new FileWriter(saveDirectory + recordingName + "/shooter.txt");
          for (double i : shooterMotorInstructions) {
            writer.write(i + "\n");
          }
          writer.close();

        } catch (Exception e) {}
        
  }
}
