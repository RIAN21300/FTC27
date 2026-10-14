package org.firstinspires.ftc.teamcode;

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

        public static class PIDCoefficient {
            public static final double kP = 4e-3;
            public static final double kI = 0.0;
            public static final double kD = 0.0;
        }

        public static class FFCoefficient {
            public static final double kS = 0.1;
            public static final double kV = 4.5e-4;
        }
    }
}
