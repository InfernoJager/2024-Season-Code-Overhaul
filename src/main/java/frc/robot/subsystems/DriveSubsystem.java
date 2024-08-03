package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.swervemodule.SwerveModule;
import frc.robot.swervemodule.SwerveModules;
import frc.robot.utils.VectorR;

public class DriveSubsystem extends SubsystemBase {

  // COMPONENTS
  public final SwerveModules modules;
  private static AHRS gyro;
  public XboxController driverController;

  // OTHER
  private boolean defensiveMode = false;
  private static double yawOffsetDegrees = 0;

  public DriveSubsystem() {
    modules = new SwerveModules(
        new SwerveModule(Constants.FRONT_RIGHT), new SwerveModule(Constants.FRONT_LEFT),
        new SwerveModule(Constants.BACK_RIGHT), new SwerveModule(Constants.BACK_LEFT));

    gyro = new AHRS();
    gyro.reset();
  }

  /*
   * SYSTEM STANDARD FOLLOWS COORDINATE PLANE STANDARD
   * positive (+) = forwards/left/left turn CCW
   * negative (-) = backwards/right/right turn CW
   * velocity magnitude (0-1) 1:fastest 0:stopped
   * turn (0-1)
   * NOTE: the speed of any wheel can reach a maximum of turn + |velocity|
   */

  public double GetGyro() {

    double yaw = gyro.getYaw() + 180;

    return yaw;

  }
  
  public void move(VectorR directionalSpeed, double turnSpeed, boolean aPressed, boolean startPressed) {
    
    double speedMultiplier;

    if (startPressed && aPressed) {
      speedMultiplier = 1;
    } else if (aPressed) {
      speedMultiplier = 0.5;
    } else if (startPressed) {
      speedMultiplier = 2;
    } else {
      speedMultiplier = 1;
    }

    VectorR directionalPull = directionalSpeed.clone();
    directionalPull.rotate(getYawDegrees() + 90);

    for (SwerveModule module : modules) {

      VectorR rotationalPull = VectorR.fromPolar(turnSpeed, module.info.MODULE_TANGENT_DEG);
      VectorR wheelPull = VectorR.addVectors(directionalPull, rotationalPull);

      module.update(wheelPull.getMagnitude() * speedMultiplier, wheelPull.getAngle());

    }

  }
  
  public void stop() {

    for (SwerveModule module : modules) {
      
      if (getDefensiveMode()) {
        module.stopDefensively();
      } else {
        module.stop();
      }

    }

  }

  public void setDefensiveMode(boolean activated) {
    defensiveMode = activated;
  }
  public boolean getDefensiveMode() {
    return defensiveMode;
  }

  /*
   * positive (+) = left turn CCW
   * negative (-) = right turn CW
   */
  public static double getYawDegrees() {
    return -1 * gyro.getYaw() + yawOffsetDegrees;
  }

  public static void resetGyro(double yawDegrees) {
    gyro.reset();
    yawOffsetDegrees = yawDegrees;
  }
  
  // public void resetDriveEncoders() {
  //   for (var mod : modules)
  //     mod.resetDriveEncoder();
  // }

  public double getRawMagX(){
    return gyro.getRawMagX();
  }

  public double getRawMagY(){
    return gyro.getRawMagY();
  }
}