package frc.robot.commands.DriveCommands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.utils.VectorR;

import frc.robot.subsystems.DriveSubsystem;

public class AutoMoveCommand extends Command {
    
    final DriveSubsystem drive;
    private VectorR moveSpeed;
    private double position;
    private double desiredPosition; 
    private double startPosition;
    private double angle;
    private double magnitude;
    private double turnSpeed;
    // private boolean turning;
    private double desiredAngle;


    public AutoMoveCommand(DriveSubsystem m_drive, double m_distance, double m_angle, double m_magnitude, double m_turnSpeed, double m_desiredAngle) {

        drive = m_drive;
        moveSpeed = new VectorR();
        desiredPosition = m_distance;
        angle = m_angle;
        magnitude = m_magnitude;
        turnSpeed = m_turnSpeed;
        desiredAngle = m_desiredAngle;

        addRequirements(drive);

    }

    @Override
    public void initialize() {
        moveSpeed.setFromPolar(magnitude, angle);
        startPosition = drivePosition();
    }

    @Override
    public void execute() {

        if (Math.abs(drive.GetGyro() - desiredAngle) < 1) {
            turnSpeed = 0;
        }

        // if (Math.abs(turnSpeed) > 0) {
        //     turning = true;
        // } else {
        //     turning = false;
        // }
        
        drive.move(moveSpeed, turnSpeed, false, false);

        // One revolution is 0.5116 inches
        // One full rotation is 49.52 revolutions
        position = Math.abs(drivePosition() - startPosition);
        // SmartDashboard.putNumber("Expected Position", position);
    }

    @Override
    public boolean isFinished() {
        if (position >= desiredPosition) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void end(boolean interrupted){
        drive.stop();
    }

    private double drivePosition() {
        return drive.modules.backRight.driveMotor.getEncoder().getPosition();
    }

}
