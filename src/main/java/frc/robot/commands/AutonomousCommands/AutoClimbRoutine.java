// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutonomousCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ClimbingArm;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoClimbRoutine extends SequentialCommandGroup {
  /** Creates a new AutoClimbRoutine. */
  ClimbingArm a_cArm;
  public AutoClimbRoutine(ClimbingArm cArm) {
    a_cArm = cArm;
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new AutoClimbExtentionRoutine(1, 3.35, cArm),
      new AutoClimbExtentionRoutine(-1, 1.675, cArm),
      new AutoClimbRotateRoutine(0, cArm),
      new AutoClimbExtentionRoutine(-1, 1.675, cArm),
      new AutoClimbRotateRoutine(0, cArm),

      new AutoClimbExtentionRoutine(1, 3.35, cArm),
      new AutoClimbExtentionRoutine(-1, 1.675, cArm),
      new AutoClimbRotateRoutine(0, cArm),
      new AutoClimbExtentionRoutine(-1, 1.675, cArm),
      new AutoClimbRotateRoutine(0, cArm),
      
      new AutoClimbExtentionRoutine(1, 3.35, cArm)

    );
  }
}
