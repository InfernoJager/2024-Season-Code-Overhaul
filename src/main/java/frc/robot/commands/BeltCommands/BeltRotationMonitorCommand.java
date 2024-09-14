package frc.robot.commands.BeltCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.BeltSubsystem;

public class BeltRotationMonitorCommand extends Command {
    
    final BeltSubsystem belt;
    private final double targetRotation;

    private double initialRotations;
    private double currentRotations;

    public BeltRotationMonitorCommand(BeltSubsystem m_belt, double m_targetRotation) {

        belt = m_belt;
        targetRotation = m_targetRotation;

    }

    @Override
    public void initialize() {

        initialRotations = belt.beltEncoderVal();

    }

    @Override
    public boolean isFinished() {

        currentRotations = belt.beltEncoderVal();

        return (currentRotations >= initialRotations + targetRotation);

    }

}
