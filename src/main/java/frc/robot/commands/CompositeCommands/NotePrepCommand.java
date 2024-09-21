package frc.robot.commands.CompositeCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;

import frc.robot.subsystems.BeltSubsystem;
import frc.robot.subsystems.ShootSubsystem;

import frc.robot.commands.BeltCommands.BeltRetractCommand;
import frc.robot.commands.BeltCommands.BeltRotationMonitorCommand;
import frc.robot.commands.ShootCommands.ShootRetractionCommand;


public class NotePrepCommand extends ParallelCommandGroup {

    double beltSpeed = 0.2;
    double retractSpeed = 0.1;

    public NotePrepCommand(BeltSubsystem belt, ShootSubsystem shoot) {

        addCommands(
            new BeltRetractCommand(belt, beltSpeed).alongWith(new ShootRetractionCommand(shoot, retractSpeed)).raceWith(new BeltRotationMonitorCommand(belt, 3))
        );

    }

}
