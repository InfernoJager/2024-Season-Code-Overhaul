package frc.robot.commands.CompositeCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.subsystems.PivotSubsystem;
import frc.robot.subsystems.ClimbSubsystem;

import frc.robot.commands.ClimbCommands.ArmMoveCommand;
import frc.robot.commands.ClimbCommands.ClimbStopCommand;
import frc.robot.commands.PivotCommands.PivotStopCommand;

public class ClimbCancelCommand extends SequentialCommandGroup {
    
    public ClimbCancelCommand (PivotSubsystem pivot, ClimbSubsystem climb) {
        
        double executeCancelLength = 104;
        double climbSpeed = 1;

        addCommands(
            new ClimbStopCommand(climb).alongWith(new PivotStopCommand(pivot)),
            new ArmMoveCommand(climb, executeCancelLength, climbSpeed),
            new ClimbStopCommand(climb)
        );
    }

}
