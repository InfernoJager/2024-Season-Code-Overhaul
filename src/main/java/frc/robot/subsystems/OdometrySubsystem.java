package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.models.OdometryReading;

public class OdometrySubsystem extends SubsystemBase {

    private LimelightSubsystem s_limelight;
    private OdometryReading current_odometry;

    public OdometrySubsystem(LimelightSubsystem limelight) {
        s_limelight = limelight;
        if (limelight.can_get_position()) {
            this.update_odometry_from_limelight(0);
        }
    }
    
    public void update_odometry_from_limelight(double time_interval) {
        if (current_odometry == null) {
            current_odometry = OdometryReading.baseline_from_limelight(s_limelight.get_bot_pos());
        } else {
            current_odometry = OdometryReading.from_limelight(s_limelight.get_bot_pos(), current_odometry, time_interval);
        }
    }

    public OdometryReading get_odometry() {
        return current_odometry;
    }
}
