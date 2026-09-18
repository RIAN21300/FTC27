package org.firstinspires.ftc.teamcode.OpModes.TeleOps;

import org.firstinspires.ftc.teamcode.RIANRobot;

import dev.nextftc.robot.opmode.BulkReadHook;
import dev.nextftc.robot.opmode.NextOpMode;
import dev.nextftc.robot.opmode.NextTeleop;
import dev.nextftc.robot.triggers.CommandGamepad;

@NextTeleop(name = "Main TeleOp")
public class MainTeleOp extends NextOpMode {
    private final RIANRobot robot;
    CommandGamepad driver1 = new CommandGamepad(gamepad1);
    CommandGamepad driver2 = new CommandGamepad(gamepad2);

    public MainTeleOp(RIANRobot robot) {
        super(robot, BulkReadHook.INSTANCE);
        this.robot = robot;
    }

    @Override
    public void disabledPeriodic() {

    }

    @Override
    public void start() {
        robot.drivetrain.start(gamepad1);

        robot.intake.start(driver2);

        robot.shooter.start(driver2);
    }

    @Override
    public void periodic() {

    }

    @Override
    public void end() {

    }
}
//