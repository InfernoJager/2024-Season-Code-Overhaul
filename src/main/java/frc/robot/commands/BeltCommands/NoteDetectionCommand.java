package frc.robot.commands.BeltCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.BeltSubsystem;

public class NoteDetectionCommand extends Command{

    final BeltSubsystem belt;

    public NoteDetectionCommand(BeltSubsystem m_belt) {

        belt = m_belt;
        
    }

    @Override
    public boolean isFinished() {
        return belt.isNoteIn();
    }
    
}
