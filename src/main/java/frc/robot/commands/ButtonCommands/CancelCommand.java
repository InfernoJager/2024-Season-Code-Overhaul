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
import frc.robot.commands.ClimbCommands.ArmExtendCommand;
import frc.robot.commands.ClimbCommands.ArmRetractCommand;
import frc.robot.commands.ClimbCommands.ClimbStopCommand;
import frc.robot.commands.ClimbCommands.ClimbUnlockCommand;
import frc.robot.commands.ShootCommands.ShootStopCommand;
import frc.robot.commands.BeltCommands.BeltStopCommand;


public class CancelCommand extends SequentialCommandGroup {

    public CancelCommand(IntakeSubsystem intake, PivotSubsystem pivot, ClimbSubsystem climb, ShootSubsystem shoot, BeltSubsystem belt) {

        // Pivot Safe Variables
        double safeAngle = 33;
        double pivotSpeed = 0.3;

        // Climb Safe Variables
        double prepCancelLength = 4;
        double executeCancelLength = 104;
        double climbSpeed = 1;

        if (climb.armLength() <=6) {
            addCommands(
                new IntakeStopCommand(intake).alongWith(
                    new PivotStopCommand(pivot),
                    new ShootStopCommand(shoot),
                    new BeltStopCommand(belt),
                    new ClimbStopCommand(climb)),
                new PivotToTargetPIDCommand(pivot, safeAngle, pivotSpeed, 1)
            );
        } else if (climb.armLength() >= 102 && climb.armLength() <= 106) {
            addCommands(
                new ClimbStopCommand(climb).alongWith(new PivotStopCommand(pivot)),
                new ArmRetractCommand(climb, prepCancelLength, climbSpeed).alongWith(new ClimbUnlockCommand(climb)),
                new PivotToTargetPIDCommand(pivot, safeAngle, pivotSpeed, 1)
            );
        } else if (climb.armLength() >= 22 && climb.armLength() <= 26) {
            addCommands(
                new ClimbStopCommand(climb).alongWith(new PivotStopCommand(pivot)),
                new ArmExtendCommand(climb, executeCancelLength, climbSpeed)
            );
        }

        addRequirements(intake, pivot, climb, shoot, belt);

    }
    
}
