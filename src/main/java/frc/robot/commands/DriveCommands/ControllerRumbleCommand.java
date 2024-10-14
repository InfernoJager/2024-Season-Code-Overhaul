package frc.robot.commands.DriveCommands;

import edu.wpi.first.wpilibj2.command.Command;

import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;

public class ControllerRumbleCommand extends Command {

    private final XboxController controller;
    private final Timer rumbleEnd;
    
    public ControllerRumbleCommand(XboxController driveController) {

        controller = driveController;
        rumbleEnd = new Timer();

    }

    @Override
    public void initialize() {
        
        controller.setRumble(RumbleType.kBothRumble, 1);
        rumbleEnd.start();

    }

    @Override
    public void execute() {

        if (rumbleEnd.get() >= 1.25) {
            rumbleEnd.stop();
            controller.setRumble(RumbleType.kBothRumble,0);
            rumbleEnd.reset();
        }

    }

}
