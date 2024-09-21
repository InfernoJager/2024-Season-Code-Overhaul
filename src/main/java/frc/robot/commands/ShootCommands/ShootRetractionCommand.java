package frc.robot.commands.ShootCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.ShootSubsystem;

public class ShootRetractionCommand extends Command {
    
    final ShootSubsystem shoot;
    private final double shootSpeed;
    
    public ShootRetractionCommand(ShootSubsystem m_shoot, double speed) {

        shoot = m_shoot;
        shootSpeed = speed;

        addRequirements(shoot);

    }

    @Override
    public void initialize() {

        shoot.retract(shootSpeed);

    }

    @Override
    public void end(boolean interrupted) {

        shoot.stop();

    }

}
