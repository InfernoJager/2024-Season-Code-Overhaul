package frc.robot.auto.Routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.ButtonCommands.SpeakerShootCommand;

import frc.robot.subsystems.BeltSubsystem;
import frc.robot.subsystems.PivotSubsystem;
import frc.robot.subsystems.ShootSubsystem;

public class OneNoteAutoRoutine extends SequentialCommandGroup {

    public OneNoteAutoRoutine(ShootSubsystem shoot, BeltSubsystem belt, PivotSubsystem pivot) {
        addCommands(
            new WaitCommand(1.5),
            new SpeakerShootCommand(pivot, belt, shoot)
        );
    }
    
}
