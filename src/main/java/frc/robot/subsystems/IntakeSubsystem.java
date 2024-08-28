package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.motor.Motors;
import frc.robot.motor.Motor.encoderType;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase  {

    //MIC
    private final Motors motor;

    public IntakeSubsystem() {

        motor = new Motors(Constants.INTAKE, encoderType.None);
        motor.SetRampRate(0.25);
    
    }

    private void setIntakeSpeed(double speed){

        motor.Spin(speed);   
        
    }

    public void feed(double speed){
        
        this.setIntakeSpeed(-Math.abs(speed));

    }

    public void purge(double speed){
       
        this.setIntakeSpeed(Math.abs(speed));
        
    }

    public void stop(){
       
        this.setIntakeSpeed(0);

    }


    
   
}
