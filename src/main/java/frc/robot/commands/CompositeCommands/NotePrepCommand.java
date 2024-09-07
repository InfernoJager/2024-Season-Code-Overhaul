package frc.robot.commands.CompositeCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import frc.robot.subsystems.BeltSubsystem;

import frc.robot.commands.BeltCommands.BeltRetractCommand;


public class NotePrepCommand extends ParallelCommandGroup {

    double beltSpeed = 0.4;
    double preperationTime = 0.2;

    public NotePrepCommand(BeltSubsystem belt) {

        addCommands(
            new BeltRetractCommand(belt, beltSpeed).raceWith(new WaitCommand(preperationTime))
        );

    }

}
