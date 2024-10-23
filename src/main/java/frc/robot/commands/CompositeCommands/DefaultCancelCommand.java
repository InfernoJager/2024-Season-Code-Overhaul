package frc.robot.commands.CompositeCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.PivotSubsystem;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.ShootSubsystem;
import frc.robot.subsystems.BeltSubsystem;

import frc.robot.commands.BeltCommands.BeltStopCommand;
import frc.robot.commands.ClimbCommands.ClimbStopCommand;
import frc.robot.commands.IntakeCommands.IntakeStopCommand;
import frc.robot.commands.PivotCommands.PivotStopCommand;
import frc.robot.commands.PivotCommands.PivotToTargetPIDCommand;
import frc.robot.commands.ShootCommands.ShootStopCommand;

public class DefaultCancelCommand extends SequentialCommandGroup {

    double safeAngle = 33;
    double pivotSpeed = 0.3;
    
    public DefaultCancelCommand(PivotSubsystem pivot, IntakeSubsystem intake, ClimbSubsystem climb, BeltSubsystem belt, ShootSubsystem shoot) {
        addCommands(
            new IntakeStopCommand(intake).alongWith(
            new PivotStopCommand(pivot),
            new ShootStopCommand(shoot),
            new BeltStopCommand(belt),
            new ClimbStopCommand(climb)),
            new PivotToTargetPIDCommand(pivot, safeAngle, pivotSpeed, 1),
            new PivotStopCommand(pivot)
        );
    }

}
