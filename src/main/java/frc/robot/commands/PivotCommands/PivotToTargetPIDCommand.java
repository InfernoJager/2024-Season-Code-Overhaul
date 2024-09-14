package frc.robot.commands.PivotCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.PivotSubsystem;

public class PivotToTargetPIDCommand extends Command {
    
    final PivotSubsystem pivot;
    final double target;
    final double speed;
    final double deadzone;

    public PivotToTargetPIDCommand(PivotSubsystem m_pivot, double m_target, double m_speed, double m_deadzone) {

        pivot = m_pivot;
        speed = m_speed;
        target = m_target;
        deadzone = m_deadzone;

        addRequirements(pivot);

    }

    @Override
    public void initialize() {
        
        pivot.setTargetAngle(target);
        pivot.setPivotSpeed(speed);
        pivot.startPivot();
        pivot.setPID();

    }

    @Override
    public void execute() {

        pivot.pivotToTargetPID();

    }

    @Override
    public boolean isFinished() {

        return pivot.isReady(deadzone);

    }
    
    @Override
    public void end(boolean interrupted) {
        
        pivot.hold();

    }
}
