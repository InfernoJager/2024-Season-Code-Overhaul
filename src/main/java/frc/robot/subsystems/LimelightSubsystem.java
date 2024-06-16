package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LimelightSubsystem {

    /*39.37 inches in a meter, but limelight calibration required 43.3*/
    double meterToInch = 43.3;
    
    public LimelightSubsystem() {}

    public double[] TargetPose() {
    
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry("targetpose_robotspace").getDoubleArray(new double[6]);

    }

    public double[] BotPoseFromTarget() {
    
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry("botpose_targetspace").getDoubleArray(new double[6]);

    }

    public double[] BotPoseOnField() {

        return NetworkTableInstance.getDefault().getTable("limelight").getEntry("botpose_orb").getDoubleArray(new double[6]);
    
    } 

    public double AprilTagID() {

        return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tid").getFloat(0);

    }

    public void Debug() {

        double[] poseSpace = TargetPose();

        SmartDashboard.putNumber("LimelightX", poseSpace[0]*meterToInch);
        SmartDashboard.putNumber("LimelightY", poseSpace[1]*meterToInch);
        SmartDashboard.putNumber("LimelightZ", poseSpace[2]*meterToInch);
        SmartDashboard.putNumber("LimelightRoll", poseSpace[3]);
        SmartDashboard.putNumber("LimelightPitch", poseSpace[4]);
        SmartDashboard.putNumber("LimelightYaw", poseSpace[5]);
        SmartDashboard.putNumber("AprilTagID", AprilTagID());

    }

}
