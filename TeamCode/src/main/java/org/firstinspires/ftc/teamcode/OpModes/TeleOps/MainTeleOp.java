package org.firstinspires.ftc.teamcode.OpModes.TeleOps;

import com.acmerobotics.dashboard.FtcDashboard;

import org.firstinspires.ftc.teamcode.MainRobot;

import dev.nextftc.robot.Telemetry;
import dev.nextftc.robot.opmode.BulkReadHook;
import dev.nextftc.robot.opmode.NextOpMode;
import dev.nextftc.robot.opmode.NextTeleop;

@NextTeleop(name = "Main TeleOp")
public class MainTeleOp extends NextOpMode {
    private final MainRobot robot;

    public MainTeleOp(MainRobot robot) {
        super(robot, BulkReadHook.INSTANCE);
        this.robot = robot;

        Telemetry.addBackend(FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void disabledPeriodic() {

    }

    @Override
    public void start() {
        robot.start(gamepad1, gamepad2);
    }

    @Override
    public void periodic() {

    }

    @Override
    public void end() {

    }
}
