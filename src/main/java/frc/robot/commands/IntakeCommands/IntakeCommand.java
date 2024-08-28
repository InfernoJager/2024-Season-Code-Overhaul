package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCommand extends Command {
    
    final IntakeSubsystem intake;

    public IntakeCommand(IntakeSubsystem m_intake) {

        intake = m_intake;

        addRequirements(intake);

    }

    @Override
    public void initialize() {
        intake.feed(0.6);
    }

    @Override
    public void end(boolean interrupted) {
        intake.stop();
    }

}
