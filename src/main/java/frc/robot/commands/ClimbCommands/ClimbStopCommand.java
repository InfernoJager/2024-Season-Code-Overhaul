package frc.robot.commands.ClimbCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.ClimbSubsystem;

public class ClimbStopCommand extends Command {

    final ClimbSubsystem climb;

    public ClimbStopCommand(ClimbSubsystem m_climb) {

        climb = m_climb;

        addRequirements(climb);

    }

    @Override
    public void initialize() {
        climb.stop();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
    
}
