package frc.robot.commands.ShootCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.ShootSubsystem;

public class ShootCommand extends Command {
    
    final ShootSubsystem shoot;
    private final double shootSpeed;
    private final double targetVelocity;
    
    public ShootCommand(ShootSubsystem m_shoot, double speed, double motorVelocity) {

        shoot = m_shoot;
        shootSpeed = speed;
        targetVelocity = motorVelocity;

        addRequirements(shoot);

    }

    @Override
    public void initialize() {

        shoot.shoot(shootSpeed);

    }

    @Override
    public boolean isFinished() {

        return shoot.isUpToSpeed(targetVelocity);

    }

}
