// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutonomousCommands;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.subsystems.AutoFeeder;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutonomousRoutine extends SequentialCommandGroup {
  /** Creates a new AutonomousRoutine. */
  DriveTrain ar_driveTrain;
  AutoFeeder ar_feeder;
  Intake ar_intake;
  Shooter ar_shooter;

  public AutonomousRoutine(DriveTrain drive, AutoFeeder feed, Shooter shoot, Intake intake) {
    ar_feeder = feed;
    ar_shooter = shoot;
    ar_driveTrain = drive; 
    ar_intake = intake;
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands( 
        
      new ParallelDeadlineGroup(new AutoShootRoutine(0.50, 4, ar_shooter), new AutoIntakeRoutine(0.25, 2, ar_intake, ar_feeder)),
      new DriveDistance(24, 0.50, ar_driveTrain)

    
    );
  }
}