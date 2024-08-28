package frc.robot.commands.BeltCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.BeltSubsystem;

public class BeltRetractCommand extends Command {
    
    final BeltSubsystem belt;
    private final double beltSpeed;

    public BeltRetractCommand(BeltSubsystem m_belt, double speed) {

        belt = m_belt;

        beltSpeed = speed;

        addRequirements(belt);

    }

    @Override
    public void initialize() {
        belt.retract(beltSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        belt.stop();
    }

}
