package frc.robot.subsystems;

import com.revrobotics.SparkAbsoluteEncoder;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.Constants;
import frc.robot.motor.PairedMotors;
import frc.robot.motor.Motor.encoderType;

public class PivotSubsystem extends SubsystemBase {

    private final PairedMotors motor;
    private PIDController pivotpid;
    private double pivotSpeed;
    private boolean pivotActive;
    private double desiredAngle;
    private GenericEntry p;
    private GenericEntry i;
    private GenericEntry d;
    
    public PivotSubsystem() {

        ShuffleboardTab tab = Shuffleboard.getTab("PivotInfo");
        p = tab.add("Proportional", 0.013)
            .withPosition(3, 0)
            .getEntry();
        i = tab.add("Integral", 0.002)
            .withPosition(3, 1)
            .getEntry();
        d = tab.add("Derivative", 0.0003)
            .withPosition(3, 2)
            .getEntry();

        pivotSpeed = 0;
        desiredAngle = 0;
        pivotActive = false;

        // Provides the motor
        motor = new PairedMotors(Constants.PIVOT_MAIN, Constants.PIVOT_SLAVE, encoderType.Absolute);
        motor.mainMotor.absoluteEncoder.setInverted(true); // Inverted becauase encoder upside down
        motor.SetRampRate(0.1); // Sets ramp up rate per second to save energy

        // Proportional Integral Derivative
        this.setPID();

    }

    public void setPID() {

        this.pivotpid = new PIDController(p.getDouble(0.013), i.getDouble(0.002), d.getDouble(0.0003));
        this.pivotpid.enableContinuousInput(0, 360); // Limits PID to circle

    }

    public void setTargetAngle(double angle) {
        
        desiredAngle = angle;

    }

    public double getTargetAngle() {

        return desiredAngle;

    }

    public void setPivotSpeed(double speed) {
        
        pivotSpeed = Math.abs(speed);

    }

    private void pivotUp() {

        spin(-pivotSpeed);

    }

    private void pivotDown() {

        spin(pivotSpeed);

    }

    public void pivotToTarget() {

        if (!pivotActive) return;
        
        double angle = currentAngle();
        double minAngle = 20;
        double maxAngle = 120;

        if (angle < minAngle) {
            motor.Spin(-0.05);

            return;
        }
        if (angle > maxAngle) {
            motor.Spin(0.05);

            return;
        }

        if (isInDeadzone(angle, 0.5)) {
            stop();
        } else if (angle < desiredAngle) {
            pivotUp();
        } else {
            pivotDown();
        }

    }

    public void pivotToTargetPID() {

        if (!pivotActive) return;
        
        double angle = currentAngle();
        double minAngle = 20;
        double maxAngle = 120;

        if (angle < minAngle) {
            motor.Spin(-0.05);
            return;
        } else if (angle > maxAngle) {
            motor.Spin(0.05);
            return;
        }

        double pidValue = getPIDValue(angle);

        spin(pidValue);
            
    }

    public double currentAngle() {
        double offset = 20;

        return (motor.mainMotor.getAbsoluteRawAngle() + offset) % 360;
    }

    private boolean isInDeadzone(double angle, double deadzone) {

        return desiredAngle > angle - deadzone && desiredAngle < angle + deadzone;

    }

    public boolean isReady(double deadzone) {

        return isInDeadzone(currentAngle(), deadzone);

    }

    private double getPIDValue(double angle) {

        double pidBaseValue = pivotpid.calculate(angle, desiredAngle);
        double pidSetValue;
        if (pidBaseValue > 0) {
            pidSetValue = Math.min(pidBaseValue, pivotSpeed);  
        } else {
            pidSetValue = Math.max(pidBaseValue, -pivotSpeed);
        }
        if (desiredAngle >= angle) {
            return -Math.abs(pidSetValue);
        } else {
            return Math.abs(pidSetValue);
        }

    }
    
    private void spin(double speed) {

        motor.Spin(speed);

    }

    public void startPivot() {

        pivotActive = true;

    }

    public void stop() {
        
        motor.Spin(0);
        pivotActive = false;

    }

    public void hold() {
        
        motor.Spin(-0.02);

    }

}
