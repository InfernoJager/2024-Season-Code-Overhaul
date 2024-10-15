package frc.robot.commands.ButtonCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.ClimbCommands.ArmMoveCommand;
import frc.robot.commands.ClimbCommands.ClimbLockCommand;
import frc.robot.commands.ClimbCommands.ClimbStopCommand;
import frc.robot.commands.PivotCommands.PivotStopCommand;
import frc.robot.commands.PivotCommands.PivotToTargetPIDCommand;

import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.PivotSubsystem;

public class ClimbPrepCommand extends SequentialCommandGroup {
    
    public ClimbPrepCommand(ClimbSubsystem climb, PivotSubsystem pivot) {

        double climbLength = 104;
        double climbSpeed = 1;

        double pivotAngle = 68;
        double pivotSpeed = 0.3;

        addCommands(
            new PivotToTargetPIDCommand(pivot, pivotAngle, pivotSpeed, 0.5),
            new ClimbLockCommand(climb).alongWith(new PivotStopCommand(pivot), new ArmMoveCommand(climb, climbLength, climbSpeed)).raceWith(new WaitCommand(10)),
            new ClimbStopCommand(climb)
        );

        addRequirements(climb, pivot);

    }

}
