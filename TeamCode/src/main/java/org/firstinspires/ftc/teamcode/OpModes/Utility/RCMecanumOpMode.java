package org.firstinspires.ftc.teamcode.OpModes.Utility;

import org.firstinspires.ftc.teamcode.Robots.MecanumRobot;

import dev.nextftc.robot.opmode.BulkReadHook;
import dev.nextftc.robot.opmode.NextOpMode;
import dev.nextftc.robot.opmode.NextUtility;

@NextUtility(name = "Robot-centric Mecanum OpMode", description = "to test robot-centric mecanum drivetrain")
public class RCMecanumOpMode extends NextOpMode {
    private final MecanumRobot robot;

    public RCMecanumOpMode(MecanumRobot robot) {
        super(robot, BulkReadHook.INSTANCE);
        this.robot = robot;
    }

    @Override
    public void start() {
        robot.drivetrain.start(gamepad1);
    }
}
