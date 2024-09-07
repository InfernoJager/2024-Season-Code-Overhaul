package frc.robot.commands.ButtonCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.PivotSubsystem;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.ShootSubsystem;
import frc.robot.subsystems.BeltSubsystem;

import frc.robot.commands.IntakeCommands.IntakeStopCommand;
import frc.robot.commands.PivotCommands.PivotStopCommand;
import frc.robot.commands.PivotCommands.PivotToTargetPIDCommand;
import frc.robot.commands.ClimbCommands.ClimbStopCommand;
import frc.robot.commands.ShootCommands.ShootStopCommand;
import frc.robot.commands.BeltCommands.BeltStopCommand;


public class CancelCommand extends SequentialCommandGroup {

    public CancelCommand(IntakeSubsystem intake, PivotSubsystem pivot, ClimbSubsystem climb, ShootSubsystem shoot, BeltSubsystem belt) {

        double safeAngle = 33;
        double pivotSpeed = 0.3;

        addCommands(
            new IntakeStopCommand(intake).alongWith(
                new PivotStopCommand(pivot),
                new ShootStopCommand(shoot),
                new BeltStopCommand(belt),
                new ClimbStopCommand(climb)),
            new PivotToTargetPIDCommand(pivot, safeAngle, pivotSpeed)
        );

        addRequirements(intake, pivot, climb, shoot, belt);

    }
    
}
