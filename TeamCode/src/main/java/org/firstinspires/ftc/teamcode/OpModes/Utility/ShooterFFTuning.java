package org.firstinspires.ftc.teamcode.OpModes.Utility;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Utility;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.ArrayList;
import java.util.List;

@Utility
public class ShooterFFTuning extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotorEx motor = hardwareMap.get(DcMotorEx.class, "MotorShooter");
        Servo servo = hardwareMap.get(Servo.class, "ServoShooter");
        List<Double> velocityList = new ArrayList<>();

        waitForStart();

        servo.setPosition(0.36);

        long convergeTime = 20000;
        long waitTime = 20000;
        for (double power = 0.1; power <= 1.0; power += 0.05)
        {
            telemetry.addData("Shooter power", power);
            telemetry.update();
            motor.setPower(power);

            sleep(convergeTime);

            velocityList.add(motor.getVelocity());
            telemetry.addData("Shooter velocity", motor.getVelocity());
            telemetry.update();
            motor.setPower(0.0);

            sleep(waitTime);
        }

        while (opModeIsActive()) {
            telemetry.addData("velocity list", velocityList);
            telemetry.update();
        }
    }
}
