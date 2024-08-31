package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;
import frc.robot.motor.PairedMotors;
import frc.robot.motor.Motor.encoderType;

public class PivotSubsystem extends SubsystemBase {

    private final PairedMotors motor;
    private PIDController pivotpid;
    private double pivotSpeed;
    private boolean pivotActive;
    private double desiredAngle;
    
    public PivotSubsystem() {
        pivotSpeed = 0;
        desiredAngle = 0;
        pivotActive = false;

        // Provides the motor
        motor = new PairedMotors(Constants.PIVOT_MAIN, Constants.PIVOT_SLAVE, encoderType.Absolute);
        motor.mainMotor.absoluteEncoder.setInverted(true); // Inverted becauase encoder upside down
        motor.SetRampRate(0.1); // Sets ramp up rate per second to save energy

        // Proportional Integral Derivative
        this.pivotpid = new PIDController(0.02, 0, 0.001);
        this.pivotpid.enableContinuousInput(0, 360); // Limits PID to circle
        this.pivotpid.setTolerance(1); // Sets angle tolerance of PID

    }

    public void setTargetAngle(double angle) {
        
        desiredAngle = angle;

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
        }
        if (angle > maxAngle) {
            motor.Spin(0.05);
        }

        double pidValue = getPIDValue(angle);
        double speedMultiplier = speedMultiplier(pidValue);

        spin(pidValue * speedMultiplier);
            
    }

    public double currentAngle() {
        double offset = 20;

        return (motor.mainMotor.getAbsoluteRawAngle() + offset) % 360;
    }

    private double speedMultiplier(double pidValue) {
        double angle = currentAngle();

        if (isInDeadzone(angle, 0.25)) {
            return 1;
        }
        
        // if (Math.abs(pidValue) < 0.002) {
        //     return 13;
        // } else if (Math.abs(pidValue) < 0.003) {
        //     return 9;
        // } else if (Math.abs(pidValue) < 0.005) {
        //     return 6;
        // } else if (Math.abs(pidValue) < 0.01) {
        //     return 3;
        // } else if (Math.abs(pidValue) < 0.03) {
        //     return 2;
        // }

        return 1;
    }

    private boolean isInDeadzone(double angle, double deadzone) {
        return desiredAngle > angle - deadzone && desiredAngle < angle + deadzone;
    }

    private double getPIDValue(double angle) {
        double pidBaseValue = pivotpid.calculate(angle, desiredAngle);
        double pidSetValue;
        if (pidBaseValue > 0) {
            pidSetValue = Math.min(pidBaseValue, -pivotSpeed);  
        } else {
            pidSetValue = Math.max(pidBaseValue, pivotSpeed);
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

    public void stop() {
        
        motor.Spin(0);
        pivotActive = false;

    }

    public void hold() {
        
        motor.Spin(-0.03);

    }

}
