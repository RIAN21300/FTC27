package org.firstinspires.ftc.teamcode.Mechanisms;

import com.pedropathing.ivy.Command;

import org.firstinspires.ftc.teamcode.MainConfig;

import dev.nextftc.hardware.RobotController;
import dev.nextftc.hardware.actuators.NextMotor;
import dev.nextftc.robot.Mechanism;
import dev.nextftc.robot.triggers.CommandGamepad;

public class Intake implements Mechanism {
    NextMotor motor = new NextMotor(
            RobotController.controlHub(),
            MainConfig.Intake.port
    );

    public Command setOn() {
        return instant(() -> motor.setThrottle(MainConfig.Intake.power));
    }

    public Command setOff() {
        return instant(() -> motor.setThrottle(0.0));
    }

    public void start(CommandGamepad commandGamepad) {
        commandGamepad.leftBumper()
                .onTrue(setOn())
                .onFalse(setOff());
    }
}
