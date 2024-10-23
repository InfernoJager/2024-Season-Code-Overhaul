package frc.robot.commands.ShootCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.ShootSubsystem;

public class ShootStopCommand extends Command {
    
    final ShootSubsystem shoot;

    public ShootStopCommand(ShootSubsystem m_shoot) {

        shoot = m_shoot;

        addRequirements(shoot);

    }

    @Override
    public void initialize() {
        shoot.stop();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
