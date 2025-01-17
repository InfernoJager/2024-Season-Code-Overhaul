package frc.robot.commands.ClimbCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.ClimbSubsystem;

public class ArmMoveCommand extends Command {
    
    final ClimbSubsystem climb;
    final double target;
    final double speed;

    public ArmMoveCommand(ClimbSubsystem m_climb, double targetLength, double climbSpeed) {

        climb = m_climb;
        target = targetLength;
        speed = climbSpeed;

        addRequirements(climb);

    }

    @Override
    public void initialize() {
        climb.setArmLength(target);
        climb.setClimbSpeed(speed);
    }

    @Override
    public void execute() {
        climb.moveArm();
    }

    @Override
    public boolean isFinished() {
        return (!climb.isArmLongerThanTarget() && !climb.isArmShorterThanTarget());
    }

}
