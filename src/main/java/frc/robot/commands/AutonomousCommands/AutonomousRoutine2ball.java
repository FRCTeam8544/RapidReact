// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutonomousCommands;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutonomousRoutine2ball extends ParallelCommandGroup {
  /** Creates a new AutonomousRoutine. */
  DriveTrain ar_driveTrain;
  Feeder ar_feeder;
  Intake ar_intake;
  Shooter ar_shooter;

  public AutonomousRoutine2ball(DriveTrain drive, Feeder feed, Shooter shoot, Intake intake) {
    ar_feeder = feed;
    ar_shooter = shoot;
    ar_driveTrain = drive; 
    ar_intake = intake;
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new AutoShootRoutine(0.65, 60, ar_shooter), new SequentialCommandGroup(
      new SetIdle(IdleMode.kBrake, ar_driveTrain),
      new ParallelDeadlineGroup(new WaitTime(1.5), new AutoFeedRoutine(0.75, 1, ar_feeder)),

      //shoot from start
      new ParallelDeadlineGroup(new WaitTime(3), new DriveDistance(40, -0.5, ar_driveTrain), new AutoIntakeRoutine(-0.6, 0, ar_intake, true)),

      new ParallelDeadlineGroup(new WaitTime(3), new DriveDistance(40, 0.5, ar_driveTrain)),

      new SetIdle(IdleMode.kCoast, ar_driveTrain),
      
      new ParallelDeadlineGroup(new WaitTime(3), new AutoFeedRoutine(0.75, 0, ar_feeder), new AutoIntakeRoutine(-0.6, 0, ar_intake, false)),
      new DriveDistance(60, -0.5, ar_driveTrain)
    ));
  }
}
