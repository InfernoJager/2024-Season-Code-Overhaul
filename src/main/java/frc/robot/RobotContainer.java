// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import frc.robot.commands.DriveStopCommand;
import frc.robot.commands.TeleopMoveCommand;

import edu.wpi.first.wpilibj.XboxController;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LimelightSubsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

public class RobotContainer {
  DriveSubsystem drive = new DriveSubsystem();
  LimelightSubsystem limelight = new LimelightSubsystem();
  
  CommandXboxController commandDriverController = new CommandXboxController(Constants.DRIVE_CONTROL_PORT);
  XboxController driverController = new XboxController(Constants.DRIVE_CONTROL_PORT);
  CommandXboxController commandOperatorController = new CommandXboxController(Constants.OPERATOR_CONTROL_PORT);
  XboxController operatorController = new XboxController(Constants.OPERATOR_CONTROL_PORT);
  CommandGenericHID buttonBoard = new CommandGenericHID(Constants.BUTTON_BOARD_PORT);

  public RobotContainer() {
    drive.setDefaultCommand(new DriveStopCommand(drive));

    configureBindings();
  }

  
  private void configureBindings() {

    SmartDashboard.putData(new InstantCommand(()->{DriveSubsystem.resetGyro(0);}));

    // Driver Triggers

    commandDriverController.axisGreaterThan(0, 0.1)
      .or(commandDriverController.axisLessThan(0, -0.1))
      .or(commandDriverController.axisGreaterThan(1, 0.1))
      .or(commandDriverController.axisLessThan(1, -0.1))
      .or(commandDriverController.axisGreaterThan(4, 0.1))
      .or(commandDriverController.axisGreaterThan(5, 0.1))
      .or(commandDriverController.leftBumper())
      .or(commandDriverController.rightBumper())
      .onTrue(new TeleopMoveCommand(drive, driverController));

    // Operator Triggers

    displayDashboard();
  }

  public Command getAutonomousCommand() {
    return null;
  }

  public void displayDashboard() {
    // SMART DASHBOARD
    limelight.LimelightWhere();
    
    // SHUFFLEBOARD

  }
}