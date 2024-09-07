package frc.robot.commands.PivotCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.PivotSubsystem;

public class PivotStopCommand extends Command {

    final PivotSubsystem pivot;

    public PivotStopCommand(PivotSubsystem m_pivot) {

        pivot = m_pivot;

        addRequirements(pivot);

    }

    @Override
    public void initialize() {
        pivot.stop();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
