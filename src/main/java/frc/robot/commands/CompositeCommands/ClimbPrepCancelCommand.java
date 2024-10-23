package frc.robot.commands.CompositeCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.subsystems.PivotSubsystem;
import frc.robot.subsystems.ClimbSubsystem;

import frc.robot.commands.ClimbCommands.ArmMoveCommand;
import frc.robot.commands.ClimbCommands.ClimbStopCommand;
import frc.robot.commands.ClimbCommands.ClimbUnlockCommand;
import frc.robot.commands.PivotCommands.PivotStopCommand;
import frc.robot.commands.PivotCommands.PivotToTargetPIDCommand;

public class ClimbPrepCancelCommand extends SequentialCommandGroup {
    
    public ClimbPrepCancelCommand(PivotSubsystem pivot, ClimbSubsystem climb) {
        
        double safeAngle = 33;
        double climbSafeAngle = 68;
        double pivotSpeed = 0.3;

        double prepCancelLength = 4;
        double climbSpeed = 1;
        
        addCommands(
            new ClimbStopCommand(climb).alongWith(new PivotStopCommand(pivot)),
            new ArmMoveCommand(climb, prepCancelLength, climbSpeed).alongWith(new ClimbUnlockCommand(climb), new PivotToTargetPIDCommand(pivot, climbSafeAngle, pivotSpeed, 0.5)),
            new PivotToTargetPIDCommand(pivot, safeAngle, pivotSpeed, 1).alongWith(new ClimbStopCommand(climb)),
            new PivotStopCommand(pivot)
        );
    }

}
