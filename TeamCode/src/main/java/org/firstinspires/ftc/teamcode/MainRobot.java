package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Mechanisms.Intake;
import org.firstinspires.ftc.teamcode.Mechanisms.RobotCentricMecanum;
import org.firstinspires.ftc.teamcode.Mechanisms.Shooter;

import java.util.Set;

import dev.nextftc.robot.Mechanism;
import dev.nextftc.robot.NextRobot;
import dev.nextftc.robot.triggers.CommandGamepad;

public class MainRobot implements NextRobot {
    public final RobotCentricMecanum drivetrain = new RobotCentricMecanum();
    public final Intake intake = new Intake();
    public final Shooter shooter = new Shooter();

    @NonNull
    @Override
    public Set<Mechanism> getMechanisms() {
        return Set.of(
                drivetrain,
                intake,
                shooter
        );
    }

    private Gamepad gamepad1, gamepad2;
    private CommandGamepad driver1, driver2;

    public void init(Gamepad _gamepad1, Gamepad _gamepad2) {
        gamepad1 = _gamepad1;
        gamepad2 = _gamepad2;
        driver1 = new CommandGamepad(gamepad1);
        driver2 = new CommandGamepad(gamepad2);

        drivetrain.init();
        shooter.init();
    }

    public void manual() {
        drivetrain.manual(gamepad1);

        driver1.rightBumper().toggleOnTrue(intake.runUntilCancel);
    }

    @Override
    public void periodic() {
        shooter.updateInput(
                gamepad2.leftBumperWasPressed(),
                gamepad2.rightBumperWasPressed()
        );
    }
}
