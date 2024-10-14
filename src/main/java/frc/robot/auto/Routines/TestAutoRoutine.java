package frc.robot.auto.Routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.commands.ButtonCommands.SpeakerShootCommand;
import frc.robot.commands.DriveCommands.AutoMoveCommand;
import frc.robot.commands.CompositeCommands.AutoIntakeCommand;
import frc.robot.commands.CompositeCommands.AutoSpeakerShootCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.BeltSubsystem;
import frc.robot.subsystems.PivotSubsystem;
import frc.robot.subsystems.ShootSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class TestAutoRoutine extends SequentialCommandGroup {
    
    public TestAutoRoutine(DriveSubsystem drive, ShootSubsystem shoot, BeltSubsystem belt, PivotSubsystem pivot, IntakeSubsystem intake) {

        addCommands(
            new AutoSpeakerShootCommand(pivot, belt, shoot),
            new AutoMoveCommand(drive, 3, 270, 0.11, 0, 0),
            new AutoMoveCommand(drive, 23, 270, 0.4, 0, 0)
                .alongWith(new AutoIntakeCommand(pivot, belt, intake, shoot)),
            new AutoMoveCommand(drive, 3, 90, 0.11, 0, 0),
            new AutoMoveCommand(drive, 23, 90, 0.4, 0, 0)
                .alongWith(new AutoSpeakerShootCommand(pivot, belt, shoot)),
            new AutoMoveCommand(drive, 3, 227, 0.11, 0, 0),
            new AutoMoveCommand(drive, 39, 227, 0.35, -0.125, 160)
                .alongWith(new AutoIntakeCommand(pivot, belt, intake, shoot)),
            new AutoMoveCommand(drive, 7.5, 32, 0.4, 0, 0),
            new AutoMoveCommand(drive, 34.5, 32, 0.4, 0.125, 180)
                .alongWith(new AutoSpeakerShootCommand(pivot, belt, shoot)),
            new AutoMoveCommand(drive, 3, 310, 0.11, 0, 0),
            new AutoMoveCommand(drive, 39, 310, 0.35, 0.125, 200)
                .alongWith(new AutoIntakeCommand(pivot, belt, intake, shoot)),
            new AutoMoveCommand(drive, 7.5, 145, 0.4, 0, 0),
            new AutoMoveCommand(drive, 34.5, 145, 0.4, -0.125, 180)
                .alongWith(new AutoSpeakerShootCommand(pivot, belt, shoot))
        );

    }

}