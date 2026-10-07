package org.firstinspires.ftc.teamcode.Mechanisms;

import com.pedropathing.ivy.Command;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.MainConfig;

import dev.nextftc.control.feedback.PIDCoefficients;
import dev.nextftc.control.feedback.PIDController;
import dev.nextftc.hardware.RobotController;
import dev.nextftc.hardware.actuators.NextMotor;
import dev.nextftc.robot.Mechanism;
import dev.nextftc.robot.Telemetry;
import dev.nextftc.robot.triggers.CommandGamepad;

public class Shooter implements Mechanism {
    NextMotor motor = new NextMotor(
            RobotController.controlHub(),
            MainConfig.Shooter.port
    );

    private PIDController pid = new PIDController(new PIDCoefficients(
            MainConfig.Shooter.PIDCoefficient.kP,
            MainConfig.Shooter.PIDCoefficient.kI,
            MainConfig.Shooter.PIDCoefficient.kD
    ));

    private double error() {
        return MainConfig.Shooter.goalEncoderVelocity - motor.getEncoderVelocity().getMagnitude();
    }

    private final Command maintainVelocity = Command.build()
            .setExecute(() -> motor.setThrottle(
                    Range.clip(pid.calculate(error()), -1.0, 1.0)
            ))
            .setEnd(endCondition -> motor.setThrottle(0.0))
            .requiring(motor);

    public void start(CommandGamepad commandGamepad) {
        commandGamepad
                .leftBumper()
                .toggleOnTrue(maintainVelocity);
    }

    @Override
    public void periodic() {
        Telemetry.log("Shooter velocity", motor.getEncoderVelocity());
        Telemetry.log("Shooter velocity goal", MainConfig.Shooter.goalEncoderVelocity);
    }
}
