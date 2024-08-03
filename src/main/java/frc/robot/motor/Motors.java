package frc.robot.motor;

import frc.robot.motor.Motor.encoderType;

public class Motors {
    
    public final Motor motor;
    
    public Motors(MotorInfo motor, encoderType encoder) {

        this.motor = new Motor(motor, encoder);

    }

    public void Spin(double speed) {

        motor.motor.set(speed);

    }

    public void SetRampRate(double rate) {

        motor.motor.setClosedLoopRampRate(rate);

    }

}
