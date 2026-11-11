package org.firstinspires.ftc.teamcode.OpModes.Utility;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Utility;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Utility
public class ShooterFFTuning extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotorEx motor = hardwareMap.get(DcMotorEx.class, "shooter");

        waitForStart();

        long convergeTime = 20000;
        long waitTime = 20000;
        for (double power = 0.4; power <= 1.0; power += 0.1)
        {
            telemetry.addData("Shooter power", power);
            telemetry.update();

            motor.setPower(power);
            sleep(convergeTime);

            telemetry.addData("Shooter velocity", motor.getVelocity());
            telemetry.update();
            motor.setPower(0.0);

            sleep(waitTime);
        }
    }
}
