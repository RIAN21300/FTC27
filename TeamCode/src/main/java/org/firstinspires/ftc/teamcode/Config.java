package org.firstinspires.ftc.teamcode;

public class Config {
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
        public static final double maxEncoderVelocity = 2000.0; // TODO: measure this
        public static final double relVelocity = 0.6;
        public static final double goalEncoderVelocity = relVelocity * maxEncoderVelocity;
    }

    public static class GeckoWheel {
        public static final int port = 0;
        public static final double power = 1.0;
    }
}
