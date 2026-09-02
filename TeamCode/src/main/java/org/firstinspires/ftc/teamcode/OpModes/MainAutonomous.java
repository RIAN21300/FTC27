package org.firstinspires.ftc.teamcode.OpModes;

import org.firstinspires.ftc.teamcode.Subsystems.Intake;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.groups.SequentialGroup;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;

public class MainAutonomous extends NextFTCOpMode {
    public MainAutonomous() {
        addComponents(
                new SubsystemComponent(Intake.INSTANCE),
                BulkReadComponent.INSTANCE
        );
    }

    private Command tempRoutine() {
        return new SequentialGroup(

        );
    }

    @Override
    public void onStartButtonPressed() {
        tempRoutine().schedule();
    }
}
