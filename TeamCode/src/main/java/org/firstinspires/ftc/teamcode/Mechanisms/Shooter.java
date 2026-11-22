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
import dev.nextftc.units.measuretypes.Angle;
import dev.nextftc.units.Units;

public class Shooter implements Mechanism {
    private static class Flywheel {
        private final NextMotor motor = new NextMotor(
                RobotController.expansionHub(),
                MainConfig.Shooter.Flywheel.port,
                new Angle(MainConfig.Shooter.Flywheel.anglePerCount, Units.Radians)
        );

        private final PIDController pid = new PIDController(new PIDCoefficients(
                MainConfig.Shooter.Flywheel.PIDCoefficient.kP,
                MainConfig.Shooter.Flywheel.PIDCoefficient.kI,
                MainConfig.Shooter.Flywheel.PIDCoefficient.kD
        ));
        private final SimpleFeedforward ff = new SimpleFeedforward(new SimpleFFCoefficients(
                MainConfig.Shooter.Flywheel.FFCoefficient.kS,
                MainConfig.Shooter.Flywheel.FFCoefficient.kV
        ));

        public double velocity() {
            return motor.getEncoderVelocity().getMagnitude();
        }
        private double goalVelocity() {
            return MainConfig.Shooter.Flywheel.relVelocity[state] * MainConfig.Shooter.Flywheel.maxVelocity;
        }
        private double error() {
            return goalVelocity() - velocity();
        }

        public void init() {
            motor.setDirection(NextMotor.Direction.REVERSE);
        }

        public void periodic() {
            motor.setThrottle(Range.clip(
                    ff.calculate(goalVelocity()) + pid.calculate(error()),
                    -1.0,
                    1.0
            ));

            Telemetry.log("Shooter velocity", velocity());
            Telemetry.log("Shooter max velocity", MainConfig.Shooter.Flywheel.maxVelocity);
            Telemetry.log("Shooter goal velocity", goalVelocity());
            Telemetry.log("Shooter power", motor.getThrottle());
            Telemetry.log("zero", 0.0);
        }
    }

    private static class Compression {
        private final NextServo servo = new NextServo(
                RobotController.controlHub(),
                MainConfig.Shooter.Compression.servoPort
        );

        public void periodic() {
            servo.setPosition(MainConfig.Shooter.Compression.position[state]);

            Telemetry.log("Servo Shooter position", servo.getPosition());
            Telemetry.log("Shooter state", state);
        }
    }

    private final Flywheel flywheel = new Flywheel();
    private final Compression compression = new Compression();

    private static final int[][] aut = {
            {0, 1, 2, 0},
            {1, 1, 2, 0},
            {2, 1, 2, 0}
    };
    private static int state = 0;
    private static int input = 0;
    public void update(boolean pollen, boolean nectar) {
        input = (pollen ? 1 : 0) | ((nectar ? 1 : 0) << 1);
    }

    public void init() {
        flywheel.init();
    }

    @Override
    public void periodic() {
        state = aut[state][input];

        flywheel.periodic();
        compression.periodic();
    }
}