package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;

public class MainConfig {
    public static class Drivetrain {
        public static class port {
            public static final int front_left = 0;
            public static final int back_left = 1;
            public static final int back_right = 2;
            public static final int front_right = 3;
        }
    }

    public static class Intake {
        public static final int port = 0;
        public static final double power = 1.0;
    }

    public static class Shooter {
        public static final int port = 1;
        public static final double maxEncoderVelocity = 2000.0;
        public static final double relVelocity = 0.6; // TODO: tune this later
        public static final double goalEncoderVelocity = relVelocity * maxEncoderVelocity;

        @Config
        public static class PIDCoefficient {
            public static double kP = 2e-2;
            public static double kI = 1e-3;
            public static double kD = 0.0;
        }
    }

    public static class GeckoWheel {
        public static final int port = 0;
        public static final double power = 1.0;
    }
}
