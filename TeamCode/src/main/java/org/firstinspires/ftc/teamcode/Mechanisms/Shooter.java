package org.firstinspires.ftc.teamcode.Mechanisms;

import com.pedropathing.ivy.Command;

import org.firstinspires.ftc.teamcode.Config;

import dev.nextftc.control.feedback.PIDCoefficients;
import dev.nextftc.control.feedback.PIDController;
import dev.nextftc.hardware.RobotController;
import dev.nextftc.hardware.actuators.NextMotor;
import dev.nextftc.robot.Mechanism;
import dev.nextftc.robot.Telemetry;
import dev.nextftc.robot.triggers.CommandGamepad;

public class Shooter implements Mechanism {
    NextMotor motor = new NextMotor(
            RobotController.expansionHub(),
            Config.Shooter.port
    );

    private PIDController pid = new PIDController(new PIDCoefficients(
            1e-3,
            0.0,
            0.0
    )); // TODO: tune this later

    private double error() {
        return Config.Shooter.goalEncoderVelocity - motor.getEncoderVelocity().getMagnitude();
    }

    public void start(CommandGamepad commandGamepad) {
        // TODO: program this
    }

    @Override
    public void periodic() {
        Telemetry.log("Shooter velocity", motor.getEncoderVelocity().getMagnitude());
        Telemetry.log("Shooter velocity goal", Config.Shooter.goalEncoderVelocity);
    }
}
