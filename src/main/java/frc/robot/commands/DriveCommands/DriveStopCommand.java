package frc.robot.commands.DriveCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.DriveSubsystem;

public class DriveStopCommand extends Command {
    
    final DriveSubsystem drive;

    public DriveStopCommand(DriveSubsystem m_drive) {

        drive = m_drive;

        addRequirements(drive);

    }

    @Override
    public void execute() {
        drive.stop();
    }

}