package frc.robot.motor;

import frc.robot.motor.Motor.encoderType;

public class PairedMotors {
    
    public final Motor mainMotor;
    public final Motor slaveMotor;

    public PairedMotors(MotorInfo main, MotorInfo slave, encoderType encoder) {
        
        this.mainMotor = new Motor(main, encoder);
        this.slaveMotor = new Motor(slave, encoderType.None);

    }

    public void Spin(double speed) {
        
        mainMotor.motor.set(speed);
        slaveMotor.motor.set(-speed);

    }
    
    public void SetRampRate(double rate) {

        mainMotor.motor.setClosedLoopRampRate(rate);
        slaveMotor.motor.setClosedLoopRampRate(rate);

    }

}
