package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.MainConfig;

import dev.nextftc.hardware.RobotController;
import dev.nextftc.hardware.actuators.NextMotor;
import dev.nextftc.robot.Mechanism;
import dev.nextftc.robot.drive.DriveCommands;

public class RobotCentricMecanum implements Mechanism {
    public final NextMotor frontLeft = new NextMotor(
            RobotController.controlHub(),
            MainConfig.Drivetrain.port.front_left
    );
    public final NextMotor backLeft = new NextMotor(
            RobotController.controlHub(),
            MainConfig.Drivetrain.port.back_left
    );
    public final NextMotor backRight = new NextMotor(
            RobotController.controlHub(),
            MainConfig.Drivetrain.port.back_right
    );
    public final NextMotor frontRight = new NextMotor(
            RobotController.controlHub(),
            MainConfig.Drivetrain.port.front_right
    );

    public void start(Gamepad gamepad) {
        frontLeft.setDirection(NextMotor.Direction.REVERSE);
        backLeft.setDirection(NextMotor.Direction.REVERSE);
        backRight.setDirection(NextMotor.Direction.FORWARD);
        frontRight.setDirection(NextMotor.Direction.FORWARD);

        DriveCommands.mecanumDrive(
                frontLeft,
                frontRight,
                backLeft,
                backRight,
                gamepad
        ).schedule();
    }
}
