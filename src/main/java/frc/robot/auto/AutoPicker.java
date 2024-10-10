package frc.robot.auto;

// import frc.robot.auto.Routines.AmpSideAuto;
// import frc.robot.auto.Routines.FourNoteAuto;
// import frc.robot.auto.Routines.LeaveAuto;
// import frc.robot.auto.Routines.SourceSideAuto;
import frc.robot.auto.Routines.TestAutoRoutine;
// import frc.robot.auto.Routines.ThreeAmpNoteAuto;
// import frc.robot.auto.Routines.ThreeSourceNoteAuto;
// import frc.robot.auto.Routines.TwoNoteAuto;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ShootSubsystem;
import frc.robot.subsystems.BeltSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.PivotSubsystem;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

public class AutoPicker extends Command{
    
    private final DriveSubsystem drive;
    private final ShootSubsystem shoot;
    private final BeltSubsystem belt;
    private final PivotSubsystem pivot;
    private final IntakeSubsystem intake;
    
    private final SendableChooser<Command> m_chooser = new SendableChooser<>();

    private Command m_LeaveAuto;
    private Command m_AmpSideAuto;
    private Command m_SourceSideAuto;
    private Command m_TwoNoteAuto;
    private Command m_ThreeAmpNoteAuto;
    private Command m_ThreeSourceNoteAuto;
    private Command m_FourNoteAuto;
    private Command m_TestAuto;


    public AutoPicker(DriveSubsystem m_drive, ShootSubsystem m_shoot, PivotSubsystem m_pivot, BeltSubsystem m_belt, IntakeSubsystem m_intake) {

        drive = m_drive;
        shoot = m_shoot;
        belt = m_belt;
        pivot = m_pivot;
        intake = m_intake;

        SetAuto();

        m_chooser.setDefaultOption("Choose Auto", null);
        m_chooser.addOption("Center Autos:", null);
        m_chooser.addOption("Side Autos:", null);
        m_chooser.addOption("Alternate Autos:", null);
        m_chooser.addOption("  - Test Auto (PROGRAMMING ONLY)", m_TestAuto);
        m_chooser.addOption("WIP Autos (DO NOT USE)", m_AmpSideAuto);
        m_chooser.addOption("  - Leave", m_LeaveAuto);
        m_chooser.addOption("  - 2 Notes To Center", m_TwoNoteAuto);
        m_chooser.addOption("  - 3 Notes To Amp", m_ThreeAmpNoteAuto);
        m_chooser.addOption("  - 3 Notes To Source", m_ThreeSourceNoteAuto);
        m_chooser.addOption("  - 4 Notes", m_FourNoteAuto);
        m_chooser.addOption("  - Amp Side", m_AmpSideAuto);
        m_chooser.addOption("  - Source Side", m_SourceSideAuto);
        m_chooser.addOption("  - 2 Notes Amp Side", null);
        m_chooser.addOption("  - 2 Notes Source Side", null);

        SmartDashboard.putData(m_chooser);

    }

    private void SetAuto() {

        // final LeaveAuto leaveAuto = new LeaveAuto(drive);
        // final AmpSideAuto ampSideAuto = new AmpSideAuto(robot);
        // final SourceSideAuto sourceSideAuto = new SourceSideAuto(robot, drive);
        // final TwoNoteAuto twoNoteAuto = new TwoNoteAuto(robot, drive);
        // final ThreeAmpNoteAuto threeAmpNoteAuto = new ThreeAmpNoteAuto(drive, robot);
        // final ThreeSourceNoteAuto threeSourceNoteAuto = new ThreeSourceNoteAuto(drive, robot);
        // final FourNoteAuto fourNoteAuto = new FourNoteAuto(drive, robot);
        final TestAutoRoutine testAuto = new TestAutoRoutine(drive, shoot, belt, pivot, intake);

        // m_LeaveAuto = leaveAuto;
        // m_AmpSideAuto = ampSideAuto;
        // m_SourceSideAuto = sourceSideAuto;
        // m_TwoNoteAuto = twoNoteAuto;
        // m_ThreeAmpNoteAuto = threeAmpNoteAuto;
        // m_ThreeSourceNoteAuto = threeSourceNoteAuto;
        // m_FourNoteAuto = fourNoteAuto;
        m_TestAuto = testAuto;
        
    }

    public Command GetAuto() {

        return m_chooser.getSelected();

    }

}
