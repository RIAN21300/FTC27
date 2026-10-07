package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Mechanisms.Intake;
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

    public void start(Gamepad gamepad1, Gamepad gamepad2) {
        CommandGamepad driver2 = new CommandGamepad(gamepad2);

//        drivetrain.start(gamepad1);

        intake.start(driver2);
        shooter.start(driver2);
    }

    @Override
    public void periodic() {
        
    }
}
