package frc.robot.commands.ButtonCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;

import frc.robot.subsystems.PivotSubsystem;
import frc.robot.subsystems.BeltSubsystem;
import frc.robot.subsystems.ShootSubsystem;

import frc.robot.commands.PivotCommands.PivotToTargetPIDCommand;
import frc.robot.commands.BeltCommands.BeltPushCommand;
import frc.robot.commands.BeltCommands.BeltStopCommand;
import frc.robot.commands.ShootCommands.ShootCommand;
import frc.robot.commands.ShootCommands.ShootStopCommand;


public class SpeakerShootCommand extends SequentialCommandGroup {

    public SpeakerShootCommand(PivotSubsystem pivot, BeltSubsystem belt, ShootSubsystem shoot) {
        
        double target = 66;
        double safeAngle = 33;
        double pivotSpeed = 0.3;
        double shootSpeed = 1;
        double beltSpeed = 1;
        double shootTime = 0.5;
        
        addCommands(
            new PivotToTargetPIDCommand(pivot, target, pivotSpeed).alongWith(new ShootCommand(shoot, shootSpeed)),
            new BeltPushCommand(belt, beltSpeed).raceWith(new WaitCommand(shootTime)),
            new BeltStopCommand(belt).alongWith(new ShootStopCommand(shoot)).alongWith(new PivotToTargetPIDCommand(pivot, safeAngle, pivotSpeed))
        );

        addRequirements(pivot, belt, shoot);

    }

}
