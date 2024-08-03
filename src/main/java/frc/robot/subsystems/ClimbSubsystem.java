package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;
import frc.robot.motor.Motors;
import frc.robot.motor.Motor.encoderType;

public class ClimbSubsystem extends SubsystemBase {

    private final Motors motor;
    private final Servo servoRight;
    private final Servo servoLeft;
    private double targetLength;
    private double climbSpeed;
    
    public ClimbSubsystem() {

        motor = new Motors(Constants.CLIMB_ARM, encoderType.None);

        servoRight = new Servo(1);
        servoLeft = new Servo(2);

    }

    public void setArmLength(double length) {

        if (length < 4) {
            length = 4;
        } else if (length > 110) {
            length = 110;
        }
        
        targetLength = length;

    }

    public void setClimbSpeed(double speed) {
        
        climbSpeed = speed;
    
    }

    public boolean moveArm() {

        if (!this.isArmShorterThanTarget() && !this.isArmLongerThanTarget()) {
            this.stop();

            return true;
        }

        if(this.isArmShorterThanTarget()) {
            this.extendArm();
        } else {
            this.retractArm();
        }

        return false;
    }

    public double armLength() {

        return Math.abs(motor.motor.inBuiltEncoder.getPosition());

    }

    public double remainingDistance() {

        return targetLength - this.armLength();

    }

    public boolean isArmShorterThanTarget() {

        return this.remainingDistance() >= 2;

    }

    public boolean isArmLongerThanTarget() {

        return this.remainingDistance() <= 2;

    }
    
    public void extendArm() {

        this.setClimbSpeed(Math.abs(climbSpeed));

    }

    public void retractArm() {

        this.setClimbSpeed(-Math.abs(climbSpeed));

    }

    public void servoIn() {

        servoRight.set(1);
        servoLeft.set(1);

    }

    public void servoOut() {

        servoRight.set(0.25);
        servoLeft.set(0.25);

    }

    public void stop() {

        this.setClimbSpeed(0);

    }

}
