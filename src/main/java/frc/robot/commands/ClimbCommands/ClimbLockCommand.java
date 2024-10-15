package frc.robot.commands.ClimbCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.ClimbSubsystem;

public class ClimbLockCommand extends Command {
    
    final ClimbSubsystem climb;

    public ClimbLockCommand(ClimbSubsystem m_climb) {

        climb = m_climb;

    }

    @Override
    public void execute() {
        climb.servoIn();
    }

}
