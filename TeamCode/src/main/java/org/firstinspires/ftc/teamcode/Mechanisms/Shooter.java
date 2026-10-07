package org.firstinspires.ftc.teamcode.Mechanisms;

import com.pedropathing.ivy.Command;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.MainConfig;

import dev.nextftc.control.feedback.PIDCoefficients;
import dev.nextftc.control.feedback.PIDController;
import dev.nextftc.control.feedforward.SimpleFFCoefficients;
import dev.nextftc.control.feedforward.SimpleFeedforward;
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

    private final PIDController pid = new PIDController(new PIDCoefficients(
            MainConfig.Shooter.PIDCoefficient.kP,
            MainConfig.Shooter.PIDCoefficient.kI,
            MainConfig.Shooter.PIDCoefficient.kD
    ));

    private final SimpleFeedforward ff = new SimpleFeedforward(new SimpleFFCoefficients(
            MainConfig.Shooter.FFCoefficient.kS,
            MainConfig.Shooter.FFCoefficient.kV
    ));

    private double velocity() {
        return motor.getEncoderVelocity().getMagnitude();
    }

    private double error() {
        return MainConfig.Shooter.goalEncoderVelocity - velocity();
    }

    private final Command maintainVelocity = Command.build()
            .setExecute(() -> motor.setThrottle(
                    Range.clip(
                            pid.calculate(error()) + ff.calculate(velocity()),
                            -1.0,
                            1.0
                    )
            ))
            .setEnd(endCondition -> motor.setThrottle(0.0))
            .requiring(motor);

    // For calculating Feedforward coefficient
    private Command test(double power) {
        return Command.build()
                .setExecute(() -> motor.setThrottle(power))
                .setEnd(endCondition -> motor.setThrottle(0.0))
                .requiring(motor);
    }

    public void start(CommandGamepad commandGamepad) {
        commandGamepad
                .leftBumper()
                .toggleOnTrue(test(MainConfig.Shooter.testPower));
    }

    @Override
    public void periodic() {
        Telemetry.log("Shooter velocity", velocity());
        Telemetry.log("Shooter velocity goal", MainConfig.Shooter.goalEncoderVelocity);
    }
}
