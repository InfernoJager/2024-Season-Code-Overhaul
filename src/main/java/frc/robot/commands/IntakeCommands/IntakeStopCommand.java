package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.IntakeSubsystem;

public class IntakeStopCommand extends Command {

    final IntakeSubsystem intake;

    public IntakeStopCommand(IntakeSubsystem m_intake) {

        intake = m_intake;

        addRequirements(intake);

    }

    @Override
    public void initialize() {
        intake.stop();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
    
}
