package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.AnalogInput;

import frc.robot.motor.Motors;
import frc.robot.motor.Motor.encoderType;
import frc.robot.Constants;

public class BeltSubsystem extends SubsystemBase {
    //MïC
    private final Motors motor;
    private final AnalogInput sensor;      
    
    public BeltSubsystem() {

        motor = new Motors(Constants.FEEDER_BELT, encoderType.None);
        motor.SetRampRate(0.25);

        sensor = new AnalogInput(0);

    }

    private void setBeltSpeed(double speed){

        motor.Spin(speed);   
        
    }

    public void push(double speed){
       
        this.setBeltSpeed(-Math.abs(speed));
        
    }
    
    public void retract(double speed){
        
        this.setBeltSpeed(Math.abs(speed));

    }

    public boolean isNoteIn() {

        return sensor.getVoltage() < 0.01;

    }

    public boolean isNoteOut() {

        return sensor.getVoltage() > 0.1;

    }

    public double getSensorVal() {

        return sensor.getVoltage();

    }
    
    public void stop(){
       
        this.setBeltSpeed(0);

    }

    public double beltEncoderVal () {

        return Math.abs(this.motor.motor.inBuiltEncoder.getPosition());

    }

}
