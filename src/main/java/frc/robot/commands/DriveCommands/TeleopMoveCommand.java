package frc.robot.commands.DriveCommands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.utils.VectorR;

import frc.robot.subsystems.DriveSubsystem;

public class TeleopMoveCommand extends Command {
    
    final DriveSubsystem drive;
    final XboxController controller;

    public TeleopMoveCommand(DriveSubsystem m_drive, XboxController m_controller) {

        drive = m_drive;
        controller = m_controller;

        addRequirements(drive);

    }

    @Override
    public void execute() {
        VectorR leftJoystick = VectorR.fromCartesian(controller.getLeftX()*0.5, controller.getLeftY()*0.5);
        double turnSpeed;

        if (controller.getRightTriggerAxis() > 0.1) {
            turnSpeed = 0.25;
        } else if (controller.getLeftTriggerAxis() > 0.1) {
            turnSpeed = -0.25;
        } else if (controller.getRightBumper()) {
            turnSpeed = 0.125;
        } else if (controller.getLeftBumper()) {
            turnSpeed = -0.125;
        } else {
            turnSpeed = 0;
        }

        drive.move(leftJoystick, turnSpeed, controller.getAButton(), controller.getStartButton());
    }

}
