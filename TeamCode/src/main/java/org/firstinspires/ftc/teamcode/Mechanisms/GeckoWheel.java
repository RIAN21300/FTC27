package org.firstinspires.ftc.teamcode.Mechanisms;

import com.pedropathing.ivy.Command;

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

    public Command setOn() {
        return instant(() -> crServo.setPower(Config.GeckoWheel.power));
    }

    public Command setOff() {
        return instant(() -> crServo.setPower(0.0));
    }

    public void start(CommandGamepad commandGamepad) {
        commandGamepad.triangle()
                .whileTrue(setOn())
                .whileFalse(setOff());
    }
}
