package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.MainConfig;

import dev.nextftc.control.feedback.PIDCoefficients;
import dev.nextftc.control.feedback.PIDController;
import dev.nextftc.control.feedforward.SimpleFFCoefficients;
import dev.nextftc.control.feedforward.SimpleFeedforward;
import dev.nextftc.hardware.RobotController;
import dev.nextftc.hardware.actuators.NextMotor;
import dev.nextftc.hardware.actuators.NextServo;
import dev.nextftc.robot.Mechanism;
import dev.nextftc.robot.Telemetry;

public class Shooter implements Mechanism {
    private final NextMotor motor = new NextMotor(
            RobotController.expansionHub(),
            MainConfig.Shooter.port
    );
    private final NextServo servo = new NextServo(
            RobotController.controlHub(),
            MainConfig.Shooter.servoPort
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

    public double velocity() {
        return motor.getEncoderVelocity().getMagnitude();
    }
    private double goalVelocity() {
        return MainConfig.Shooter.relVelocity[state] * MainConfig.Shooter.maxEncoderVelocity;
    }
    private double error() {
        return goalVelocity() - velocity();
    }

    private final int[][] aut = {
            {0, 1, 2, 0},
            {1, 1, 2, 0},
            {2, 1, 2, 0}
    };
    private int state = 0;
    private int input = 0;
    public void update(boolean pollen, boolean nectar) {
        input = (pollen ? 1 : 0) | ((nectar ? 1 : 0) << 1);
    }

    public void init() {
        motor.setDirection(NextMotor.Direction.REVERSE);
    }

    @Override
    public void periodic() {
        state = aut[state][input];

        motor.setThrottle(Range.clip(
                ff.calculate(goalVelocity()) + pid.calculate(error()),
                -1.0,
                1.0
        ));

        servo.setPosition(MainConfig.Shooter.position[state]);

        Telemetry.log("Shooter state", state);
        Telemetry.log("Servo Shooter position", servo.getPosition());
        Telemetry.log("Shooter velocity", velocity());
        Telemetry.log("Shooter max velocity", MainConfig.Shooter.maxEncoderVelocity);
        Telemetry.log("Shooter goal velocity", goalVelocity());
        Telemetry.log("Shooter power", motor.getThrottle());
        Telemetry.log("zero", 0.0);
    }
}
