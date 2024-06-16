package frc.robot.autonomous_commands;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.TeleopMoveCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.utils.VectorR;

public class LimelightFollow extends TeleopMoveCommand{
    
    private final LimelightSubsystem limelight;
    private double followDistance = 1;

    public LimelightFollow(LimelightSubsystem m_limelight, DriveSubsystem m_drive, XboxController m_controller) {
        
        super(m_drive, m_controller);
        limelight = m_limelight;

    }

    public VectorR LeftJoystickPostion() {

        double[] position = limelight.BotPoseFromTarget();

        double l_x = position[0];
        double l_z = position[2];
        double l_yaw = position[4];

        // left-x, right+x, up-y, down+y
        return VectorR.fromCartesian(x, y);

    }

}
