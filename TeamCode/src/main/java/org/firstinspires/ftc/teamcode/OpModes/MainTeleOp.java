package org.firstinspires.ftc.teamcode.OpModes;

import org.firstinspires.ftc.teamcode.Config;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import dev.nextftc.hardware.driving.MecanumDriverControlled;
import dev.nextftc.hardware.impl.MotorEx;

public class MainTeleOp extends NextFTCOpMode {
    public MainTeleOp() {
        addComponents(
                new SubsystemComponent(Intake.INSTANCE),
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE
        );
    }

    private final MotorEx frontLeft = new MotorEx(Config.MOTOR_FL).reversed();
    private final MotorEx frontRight = new MotorEx(Config.MOTOR_FR);
    private final MotorEx backLeft = new MotorEx(Config.MOTOR_BL).reversed();
    private final MotorEx backRight = new MotorEx(Config.MOTOR_BR);

    @Override
    public void onStartButtonPressed() {
        Command driverControlled = new MecanumDriverControlled(
                frontLeft,
                frontRight,
                backLeft,
                backRight,
                Gamepads.gamepad1().leftStickY().negate(),
                Gamepads.gamepad1().leftStickX(),
                Gamepads.gamepad1().rightStickX()
        );
        driverControlled.schedule();

        Gamepads.gamepad2().rightBumper()
                .whenBecomesTrue(Intake.INSTANCE.setOn)
                .whenBecomesFalse(Intake.INSTANCE.setOff);
    }
}