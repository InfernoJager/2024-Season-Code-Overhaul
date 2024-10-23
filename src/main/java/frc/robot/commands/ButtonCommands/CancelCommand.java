package frc.robot.commands.ButtonCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.PivotSubsystem;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.ShootSubsystem;
import frc.robot.subsystems.BeltSubsystem;

import frc.robot.commands.CompositeCommands.DefaultCancelCommand;
import frc.robot.commands.CompositeCommands.ClimbPrepCancelCommand;
import frc.robot.commands.CompositeCommands.ArmCancelCommand;
import frc.robot.commands.CompositeCommands.ClimbCancelCommand;

public class CancelCommand extends Command {

    public final DefaultCancelCommand defaultCancel;
    public final ClimbPrepCancelCommand climbPrepCancel;
    public final ClimbCancelCommand climbCancel;
    public final ArmCancelCommand armCancel;

    public final ClimbSubsystem climb;
    
    public Command cancelCommand;

    public CancelCommand(IntakeSubsystem intake, PivotSubsystem pivot, ClimbSubsystem m_climb, ShootSubsystem shoot, BeltSubsystem belt) {

        climb = m_climb;

        defaultCancel = new DefaultCancelCommand(pivot, intake, climb, belt, shoot);
        armCancel = new ArmCancelCommand(pivot, intake, climb, belt, shoot);
        climbPrepCancel = new ClimbPrepCancelCommand(pivot, climb);
        climbCancel = new ClimbCancelCommand(pivot, climb);

        addRequirements(intake, pivot, climb, shoot, belt);

    }

    @Override
    public void initialize() {

        /*  CANCEL CHAIN
         *  Servo Locked (CC -> CPC) -> AC/DC
         *  CC = Arm extension and makes sure pivot is not moving
         *  CPC = Arm retraction to minimum value, unlock servos, pivot to safe angle
         *  AC = kill all systems, retract arm fully, pivot to safe angle
         *  DC = kill all systems, pivot to safe angle
         */

        if (climb.isLocked()) {
            if (climb.armLength() > 100) {
                cancelCommand = climbPrepCancel;
            } else {
                cancelCommand = climbCancel;
            }
        } else if (climb.armLength() > 6) {
            cancelCommand = armCancel;
        } else {
            cancelCommand = defaultCancel;
        }

        cancelCommand.initialize();
    }
    
    @Override
    public final void execute() {
        cancelCommand.execute();
    }

    @Override
    public final void end(boolean interrupted) {
        cancelCommand.end(interrupted);
    }

    @Override
    public final boolean isFinished() {
        return cancelCommand.isFinished();
    }

    @Override
    public boolean runsWhenDisabled() {
        return cancelCommand.runsWhenDisabled();
    }

    @Override
    public InterruptionBehavior getInterruptionBehavior() {
        return cancelCommand.getInterruptionBehavior();
    }
}
