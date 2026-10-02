package org.firstinspires.ftc.teamcode.Robots;

import androidx.annotation.NonNull;

import org.firstinspires.ftc.teamcode.Mechanisms.RobotCentricMecanum;

import java.util.Set;

import dev.nextftc.robot.Mechanism;
import dev.nextftc.robot.NextRobot;

public class MecanumRobot implements NextRobot {
    public final RobotCentricMecanum drivetrain = new RobotCentricMecanum();

    @NonNull
    @Override
    public Set<Mechanism> getMechanisms() {
        return Set.of(drivetrain);
    }
}
