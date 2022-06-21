// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutonomousCommands;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class RecordedRoutine extends CommandBase {
  /** Creates a new RecordedRoutine. */
  ArrayList<Double> driveLeftInstructions = new ArrayList<>();
  ArrayList<Double> driveRightInstructions = new ArrayList<>();
  ArrayList<Double> feedInstructions = new ArrayList<>();
  ArrayList<Double> intakeMotorInstructions = new ArrayList<>();
  ArrayList<Double> intakeExtentionInstructions = new ArrayList<>();
  ArrayList<Double> shooterMotorInstructions = new ArrayList<>();
  DriveTrain m_driveTrain;
  Intake m_intake;
  Feeder m_feeder;
  Shooter m_shooter;
  int time;
  String recordingName;
  String saveDirectory;

  public RecordedRoutine(DriveTrain drive, Intake intake, Feeder feed, Shooter shoot) {
    time = 0;
    m_driveTrain = drive;
    m_intake = intake;
    m_feeder = feed;
    m_shooter = shoot;
    addRequirements(m_driveTrain);
    addRequirements(m_intake);
    addRequirements(m_feeder);
    addRequirements(m_shooter);
    recordingName = SmartDashboard.getString("Recording Name", "test");
    saveDirectory = "/u/recordings/";
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    time = 0;
    recordingName = SmartDashboard.getString("Recording Name", "test");
    readFiles();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_driveTrain.tankDrive(driveLeftInstructions.get(time), driveRightInstructions.get(time));
    m_feeder.feeder.set(ControlMode.PercentOutput, feedInstructions.get(time));
    m_intake.intakeMotor.set(ControlMode.PercentOutput, intakeMotorInstructions.get(time));
    m_shooter.shooterWheel2.set(shooterMotorInstructions.get(time));
    if (intakeExtentionInstructions.get(time) == 1) {
      m_intake.intakeExtension.set(Value.kReverse);
    } else {
      m_intake.intakeExtension.set(Value.kForward);
    }
    time ++;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveTrain.tankDrive(0,0);
    m_feeder.feeder.set(ControlMode.PercentOutput, 0);
    m_intake.intakeMotor.set(ControlMode.PercentOutput, 0);
    m_shooter.shooterWheel2.set(0);
    m_intake.intakeExtension.set(Value.kForward);
    
  }

  public void readFiles() {
    try {
      
        List<String> driveLeftStrings = Files.readAllLines((new File(saveDirectory + recordingName + "/driveLeft.txt")).toPath());
        List<String> driveRightStrings = Files.readAllLines((new File(saveDirectory + recordingName + "/driveRight.txt")).toPath());
        List<String> feedStrings = Files.readAllLines((new File(saveDirectory + recordingName + "/feed.txt")).toPath());
        List<String> intakeMotorStrings = Files.readAllLines((new File(saveDirectory + recordingName + "/intakeMotor.txt")).toPath());
        List<String> intakeExtentionStrings = Files.readAllLines((new File(saveDirectory + recordingName + "/intakeExtention.txt")).toPath());
        List<String> shooterMotorStrings = Files.readAllLines((new File(saveDirectory + recordingName + "/shooter.txt")).toPath());

        driveLeftInstructions = new ArrayList<>();
        driveRightInstructions = new ArrayList<>();
        feedInstructions = new ArrayList<>();
        intakeMotorInstructions = new ArrayList<>();
        intakeExtentionInstructions = new ArrayList<>();
        shooterMotorInstructions = new ArrayList<>();
        for (String s : driveLeftStrings) {
            driveLeftInstructions.add(Double.parseDouble(s));
        }
        for (String s : driveRightStrings) {
            driveRightInstructions.add(Double.parseDouble(s));
        }
        for (String s : feedStrings) {
            feedInstructions.add(Double.parseDouble(s));
        }
        for (String s : intakeMotorStrings) {
            intakeMotorInstructions.add(Double.parseDouble(s));
        }
        for (String s : intakeExtentionStrings) {
            intakeExtentionInstructions.add(Double.parseDouble(s));
        }
        for (String s : shooterMotorStrings) {
          shooterMotorInstructions.add(Double.parseDouble(s));
      }
    } catch (Exception e) {}
}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return time >= driveLeftInstructions.size();
  }
}
