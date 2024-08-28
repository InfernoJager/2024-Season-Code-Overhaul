package frc.robot.commands.BeltCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.BeltSubsystem;

public class BeltStopCommand extends Command {
    
    final BeltSubsystem belt;

    public BeltStopCommand(BeltSubsystem m_belt) {

        belt = m_belt;

        addRequirements(belt);

    }

    @Override
    public void initialize() {
        belt.stop();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
