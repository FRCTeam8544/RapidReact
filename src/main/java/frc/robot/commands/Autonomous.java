// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.AutonomousCommands.MoveDistance;
//import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class Autonomous extends CommandBase {

  Timer timer;
  MoveDistance a_movedistance;
  Shooter a_shooter;
  Intake a_intake;
  
  

  double gearRatio = 10.71;
  double wheelDiameter = 6;


  /** Creates a new Autonomous. */
  public Autonomous(MoveDistance m_movedistance, Shooter m_shooter, Intake m_intake) {
    a_movedistance = m_movedistance;
    a_shooter = m_shooter;
    a_intake = m_intake;
    timer = new Timer();
    // Use addRequirements() here to declare subsystem dependencies.
    a_movedistance.schedule();
    addRequirements(a_shooter);
    addRequirements(a_intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    a_movedistance.moveRobot("forward", 120);
    Timer.delay(5);
    a_movedistance.moveRobot("backward", 120);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    timer.start();
    SmartDashboard.putNumber("Timer", timer.get());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
