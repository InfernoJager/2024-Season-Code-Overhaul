package frc.robot.commands.ButtonCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ClimbCommands.ArmMoveCommand;
import frc.robot.commands.ClimbCommands.ClimbStopCommand;
import frc.robot.subsystems.ClimbSubsystem;

public class ClimbExecuteCommand extends SequentialCommandGroup {
    
    public ClimbExecuteCommand(ClimbSubsystem climb) {

        double climbLength = 16; // 2 inches
        double climbSpeed = 1;

        addCommands(
            new ArmMoveCommand(climb, climbLength, climbSpeed),
            new ClimbStopCommand(climb)
        );

        addRequirements(climb);

    }

}

