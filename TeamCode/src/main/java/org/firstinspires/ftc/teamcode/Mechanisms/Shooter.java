package org.firstinspires.ftc.teamcode.Mechanisms;

import org.firstinspires.ftc.teamcode.Config;

import dev.nextftc.hardware.RobotController;
import dev.nextftc.hardware.actuators.NextMotor;
import dev.nextftc.robot.Mechanism;
import dev.nextftc.robot.triggers.CommandGamepad;

public class Shooter implements Mechanism {
    NextMotor motor = new NextMotor(
            RobotController.expansionHub(),
            Config.Shooter.port
    );

    public void start(CommandGamepad commandGamepad) {
        commandGamepad.rightBumper()
                .onTrue(instant(() -> motor.setThrottle(Config.Shooter.speed)))
                .onFalse(instant(() -> motor.setThrottle(0.0)));
    }

    @Override
    public void periodic() {

    }
}
//