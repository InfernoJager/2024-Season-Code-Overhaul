package frc.robot.commands.ButtonCommands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.subsystems.PivotSubsystem;
import frc.robot.subsystems.ShootSubsystem;
import frc.robot.subsystems.BeltSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

import frc.robot.commands.IntakeCommands.IntakeCommand;
import frc.robot.commands.BeltCommands.BeltPushCommand;
import frc.robot.commands.BeltCommands.NoteDetectionCommand;
import frc.robot.commands.CompositeCommands.NotePrepCommand;
import frc.robot.commands.DriveCommands.ControllerRumbleCommand;
import frc.robot.commands.PivotCommands.PivotToTargetPIDCommand;


public class PickupCommand extends SequentialCommandGroup {

    public PickupCommand(PivotSubsystem pivot, BeltSubsystem belt, IntakeSubsystem intake, ShootSubsystem shoot, XboxController driveController) {

        double target = 21;
        double safeAngle = 33;
        double pivotSpeed = 0.3;
        double intakeSpeed = 0.6;
        double beltSpeed = intakeSpeed/1.592;

        addCommands(
            new PivotToTargetPIDCommand(pivot, target, pivotSpeed, 1),
            new IntakeCommand(intake, intakeSpeed).alongWith(new BeltPushCommand(belt, beltSpeed)).raceWith(new NoteDetectionCommand(belt)),
            new NotePrepCommand(belt, shoot).alongWith(new PivotToTargetPIDCommand(pivot, safeAngle, pivotSpeed, 1), new ControllerRumbleCommand(driveController))
        );

        addRequirements(pivot, belt, intake);

    }
    
}
