package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCommand extends Command {
    
    final IntakeSubsystem intake;
    private final double intakeSpeed;

    public IntakeCommand(IntakeSubsystem m_intake, double speed) {

        intake = m_intake;
        intakeSpeed = speed;

        addRequirements(intake);

    }

    @Override
    public void initialize() {

        intake.feed(intakeSpeed);

    }

    @Override
    public void end(boolean interrupted) {

        intake.stop();
        
    }

}
