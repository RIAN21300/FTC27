package org.firstinspires.ftc.teamcode.Mechanisms;

import com.pedropathing.ivy.Command;

import org.firstinspires.ftc.teamcode.MainConfig;

import dev.nextftc.hardware.RobotController;
import dev.nextftc.hardware.actuators.NextMotor;
import dev.nextftc.robot.Mechanism;

public class Intake implements Mechanism {
    NextMotor motor = new NextMotor(
            RobotController.controlHub(),
            MainConfig.Intake.port
    );

    public  final Command runUntilCancel = Command.build()
            .setStart(() -> motor.setThrottle(MainConfig.Intake.power))
            .setEnd(endCondition -> motor.setThrottle(0.0))
            .requiring(motor);
}
