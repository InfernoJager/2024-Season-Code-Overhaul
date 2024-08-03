package frc.robot.motor;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkAbsoluteEncoder;
import com.revrobotics.SparkAnalogSensor;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class Motor {
    
    public final CANSparkMax motor;
    public SparkAnalogSensor analogEncoder;
    public SparkAbsoluteEncoder absoluteEncoder;
    public RelativeEncoder inBuiltEncoder;
    public final MotorInfo info;
    
    public enum encoderType {
        None, Analog, Absolute;
    }

    public Motor(MotorInfo info, encoderType encoder) {

        this.info = info;
        this.motor = new CANSparkMax(info.ID, MotorType.kBrushless);
        this.inBuiltEncoder = motor.getEncoder();
        motor.setIdleMode(IdleMode.kBrake);

        if (encoder == encoderType.Analog) {
            this.analogEncoder = motor.getAnalog(SparkAnalogSensor.Mode.kAbsolute);
        } else if (encoder == encoderType.Absolute) {
            this.absoluteEncoder = motor.getAbsoluteEncoder(SparkAbsoluteEncoder.Type.kDutyCycle);
        } 

    }

    public double getAnalogRawAngle() {
        
        double degreesPerVolt = 360/info.MAX_ENCODER_VALUE;
        double encoderVoltage = analogEncoder.getVoltage();

        double rawAngle = degreesPerVolt * encoderVoltage;

        return rawAngle;

    }

    public double getAnalogAngle() {
        
        return getAnalogRawAngle() - info.REFERENCE_ANGLE;

    }

    public double getAbsoluteRawAngle() {

        return absoluteEncoder.getPosition();

    }

    public double getAbsoluteAngle() {

        return getAbsoluteRawAngle() - info.REFERENCE_ANGLE;

    }

}
