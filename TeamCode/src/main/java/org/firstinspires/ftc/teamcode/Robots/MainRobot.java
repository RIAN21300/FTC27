package org.firstinspires.ftc.teamcode.Robots;

import androidx.annotation.NonNull;

import org.firstinspires.ftc.teamcode.Mechanisms.RobotCentricMecanum;
import org.firstinspires.ftc.teamcode.Mechanisms.GeckoWheel;
import org.firstinspires.ftc.teamcode.Mechanisms.Intake;
import org.firstinspires.ftc.teamcode.Mechanisms.Shooter;

import java.util.Set;

import dev.nextftc.robot.Mechanism;
import dev.nextftc.robot.NextRobot;

public class MainRobot implements NextRobot {
    public final RobotCentricMecanum drivetrain = new RobotCentricMecanum();
    public final Intake intake = new Intake();
    public final Shooter shooter = new Shooter();
    public final GeckoWheel geckoWheel = new GeckoWheel();

    @NonNull
    @Override
    public Set<Mechanism> getMechanisms() {
        return Set.of(
                drivetrain,
                intake,
                shooter,
                geckoWheel
        );
    }
}
