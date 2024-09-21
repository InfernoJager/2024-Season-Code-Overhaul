package frc.robot.commands.ButtonCommands;

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

public class AmpShootCommand extends SequentialCommandGroup {
    
    public AmpShootCommand(PivotSubsystem pivot, BeltSubsystem belt, ShootSubsystem shoot) {
        
        // Iffy numbers
        double firstTarget = 75;
        double secondTarget = 105;
        double safeAngle = 33;
        double pivotSpeed = 0.5;
        double shootSpeed = 0.0825;
        double motorVelocity = 1500;
        double beltSpeed = 0.7;
        double shootTime = 0.25;
        
        addCommands(
            new PivotToTargetPIDCommand(pivot, firstTarget, pivotSpeed, 0.25).raceWith(new WaitCommand(0.5)),
            new ShootCommand(shoot, shootSpeed, motorVelocity).raceWith(new WaitCommand(shootTime)),
            new PivotToTargetPIDCommand(pivot, secondTarget, pivotSpeed, 0.25).alongWith(new BeltPushCommand(belt, beltSpeed)).raceWith(new WaitCommand(1.25)),
            new BeltStopCommand(belt).alongWith(new ShootStopCommand(shoot)).alongWith(new PivotToTargetPIDCommand(pivot, safeAngle, pivotSpeed, 1))
        );

        addRequirements(pivot, belt, shoot);

    }

}
