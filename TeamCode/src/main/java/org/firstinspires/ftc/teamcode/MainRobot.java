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
//    public final RobotCentricMecanum drivetrain = new RobotCentricMecanum();
    public final Intake intake = new Intake();
    public final Shooter shooter = new Shooter();

    @NonNull
    @Override
    public Set<Mechanism> getMechanisms() {
        return Set.of(
//                drivetrain,
                intake,
                shooter
        );
    }

    public void init() {
//        drivetrain.init();
    }

    public void manual(Gamepad gamepad1, Gamepad gamepad2) {
        CommandGamepad driver2 = new CommandGamepad(gamepad2);

//        drivetrain.manual(gamepad1);

        driver2.rightBumper().toggleOnTrue(intake.runUntilCancel);
        driver2.leftBumper().toggleOnTrue(shooter.maintainVelocity);
    }

    @Override
    public void periodic() {
        
    }
}
