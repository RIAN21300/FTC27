package org.firstinspires.ftc.teamcode.Mechanisms;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.MainConfig;
import org.firstinspires.ftc.teamcode.pedro.Constants;

import dev.nextftc.hardware.RobotController;
import dev.nextftc.hardware.actuators.NextMotor;
import dev.nextftc.robot.Mechanism;
import dev.nextftc.robot.drive.DriveCommands;

public class FieldCentricMecanum implements Mechanism {
    private final NextMotor frontLeft = new NextMotor(
            RobotController.controlHub(),
            MainConfig.Drivetrain.port.front_left
    );
    private final NextMotor backLeft = new NextMotor(
            RobotController.controlHub(),
            MainConfig.Drivetrain.port.back_left
    );
    private final NextMotor backRight = new NextMotor(
            RobotController.controlHub(),
            MainConfig.Drivetrain.port.back_right
    );
    private final NextMotor frontRight = new NextMotor(
            RobotController.controlHub(),
            MainConfig.Drivetrain.port.front_right
    );

    private Follower follower;

    public void init(HardwareMap hardwareMap) {
        frontLeft.setDirection(NextMotor.Direction.REVERSE);
        backLeft.setDirection(NextMotor.Direction.REVERSE);
        backRight.setDirection(NextMotor.Direction.FORWARD);
        frontRight.setDirection(NextMotor.Direction.FORWARD);

        follower = Constants.create(hardwareMap);
    }

    public void manual(Gamepad gamepad) {
        DriveCommands.mecanumDriveFieldCentric(
                frontLeft,
                frontRight,
                backLeft,
                backRight,
                gamepad,
                () -> follower.pose().heading()
        ).schedule();
    }
}
