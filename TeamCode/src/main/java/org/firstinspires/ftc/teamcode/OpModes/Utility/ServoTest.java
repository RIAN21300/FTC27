package org.firstinspires.ftc.teamcode.OpModes.Utility;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Utility;
import com.qualcomm.robotcore.hardware.ServoImplEx;
import com.qualcomm.robotcore.util.Range;

@Utility
public class ServoTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        ServoImplEx servo = hardwareMap.get(ServoImplEx.class, "servo");
        double currentPosition = 0.0;

        waitForStart();

        while (opModeIsActive()) {
            currentPosition += 1e-3 * gamepad1.left_stick_y;
            currentPosition = Range.clip(currentPosition, 0.0, 1.0);
            servo.setPosition(currentPosition);

            telemetry.addData("gamepad1 left stick y", gamepad1.left_stick_y);
            telemetry.addData("position", servo.getPosition());
            telemetry.update();
        }
    }
}
