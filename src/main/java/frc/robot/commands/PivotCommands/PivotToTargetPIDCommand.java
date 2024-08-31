package frc.robot.commands.PivotCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.PivotSubsystem;

public class PivotToTargetPIDCommand extends Command {
    
    final PivotSubsystem pivot;
    final double target;
    final double speed;

    public PivotToTargetPIDCommand(PivotSubsystem m_pivot, double m_target, double m_speed) {

        pivot = m_pivot;
        speed = m_speed;
        target = m_target;

        addRequirements(pivot);

    }

    @Override
    public void initialize() {
        
        pivot.setTargetAngle(target);
        pivot.setPivotSpeed(speed);
        pivot.startPivot();

    }

    @Override
    public void execute() {

        pivot.pivotToTargetPID();

    }

    @Override
    public boolean isFinished() {

        return pivot.isReady(0.5);

    }
    
    @Override
    public void end(boolean interrupted) {
        
        pivot.hold();

    }
}
