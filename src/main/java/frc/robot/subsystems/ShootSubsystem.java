package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;
import frc.robot.motor.PairedMotors;
import frc.robot.motor.Motor.encoderType;

public class ShootSubsystem extends SubsystemBase {

    private final PairedMotors motor;

    public ShootSubsystem() {

        motor = new PairedMotors(Constants.CANNON_MAIN, Constants.CANNON_SLAVE, encoderType.None);
        motor.SetRampRate(0.75);

    }

    public void shoot(double speed) {

        this.setFlywheelSpeed(-Math.abs(speed));
        
    }

    public void retract(double speed) {

        this.setFlywheelSpeed(Math.abs(speed));

    }

    private void setFlywheelSpeed(double speed) {

        motor.Spin(speed);
        
    }

    public Boolean isUpToSpeed(double motorVelocity) {

        return Math.abs(motor.mainMotor.inBuiltEncoder.getVelocity()) >= Math.abs(motorVelocity);

    }
    
    public void stop() {

        this.setFlywheelSpeed(0);
    
    }

    
    
}
