package frc.robot.commands.ClimbCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.ClimbSubsystem;

public class ClimbUnlockCommand extends Command {
    
    final ClimbSubsystem climb;

    public ClimbUnlockCommand(ClimbSubsystem m_climb) {

        climb = m_climb;
        
    }

    @Override
    public void execute() {
        climb.servoOut();
    }

    @Override
    public boolean isFinished() {
        return climb.isServoOut();
    }

}
