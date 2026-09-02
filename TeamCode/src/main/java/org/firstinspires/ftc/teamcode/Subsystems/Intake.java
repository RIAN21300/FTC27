package org.firstinspires.ftc.teamcode.Subsystems;

import org.firstinspires.ftc.teamcode.Config;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.powerable.SetPower;

public class Intake implements Subsystem {
    public static final Intake INSTANCE = new Intake();
    private Intake() {  }

    private final MotorEx motor = new MotorEx(Config.MOTOR_INTAKE);

    public Command setOn = new SetPower(motor, 1.0).requires(this);
    public Command setOff = new SetPower(motor, 0.0).requires(this);
}
