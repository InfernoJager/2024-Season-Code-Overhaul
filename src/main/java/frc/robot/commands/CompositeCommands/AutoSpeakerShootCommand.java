package frc.robot.commands.CompositeCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import frc.robot.subsystems.PivotSubsystem;
import frc.robot.subsystems.BeltSubsystem;
import frc.robot.subsystems.ShootSubsystem;

import frc.robot.commands.PivotCommands.PivotToTargetPIDCommand;
import frc.robot.commands.BeltCommands.BeltPushCommand;
import frc.robot.commands.BeltCommands.BeltStopCommand;
import frc.robot.commands.ShootCommands.ShootCommand;
import frc.robot.commands.ShootCommands.ShootStopCommand;


public class AutoSpeakerShootCommand extends SequentialCommandGroup {

    public AutoSpeakerShootCommand(PivotSubsystem pivot, BeltSubsystem belt, ShootSubsystem shoot) {
        
        // 50 target 7.3 feet, 64 target 3 feet (at subwoofer)
        double target = 64;
        double safeAngle = 33;
        double pivotSpeed = 1;
        double safePivot = 0.3;
        double shootSpeed = 1;
        double motorVelocity = 5750;
        double beltSpeed = 1;
        double shootTime = 0.5;
        
        addCommands(
            new PivotToTargetPIDCommand(pivot, target, pivotSpeed, 0.25).alongWith(new ShootCommand(shoot, shootSpeed, motorVelocity)).raceWith(new WaitCommand(0.75)),
            new BeltPushCommand(belt, beltSpeed).raceWith(new WaitCommand(shootTime)),
            new BeltStopCommand(belt).alongWith(new ShootStopCommand(shoot)).alongWith(new PivotToTargetPIDCommand(pivot, safeAngle, safePivot, 1)).raceWith(new WaitCommand(0.1))
        );

        addRequirements(pivot, belt, shoot);

    }

}
