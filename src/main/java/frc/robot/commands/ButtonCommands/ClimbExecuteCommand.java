package frc.robot.commands.ButtonCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.commands.ClimbCommands.ArmRetractCommand;

import frc.robot.subsystems.ClimbSubsystem;

public class ClimbExecuteCommand extends SequentialCommandGroup {
    
    public ClimbExecuteCommand(ClimbSubsystem climb) {

        double climbLength = 24;
        double climbSpeed = 1;

        addCommands(
            new ArmRetractCommand(climb, climbLength, climbSpeed)
        );

        addRequirements(climb);

    }

}

