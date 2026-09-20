package org.firstinspires.ftc.teamcode.Mechanisms;

import org.firstinspires.ftc.teamcode.Config;

import dev.nextftc.hardware.RobotController;
import dev.nextftc.hardware.actuators.NextCRServo;
import dev.nextftc.robot.Mechanism;
import dev.nextftc.robot.triggers.CommandGamepad;

public class GeckoWheel implements Mechanism {
    NextCRServo crServo = new NextCRServo(
            RobotController.controlHub(),
            Config.GeckoWheel.port
    );

    public void start(CommandGamepad commandGamepad) {
        commandGamepad.triangle()
                .whileTrue(instant(() -> crServo.setPower(Config.GeckoWheel.power)))
                .whileFalse(instant(() -> crServo.setPower(0.0)));
    }
}
