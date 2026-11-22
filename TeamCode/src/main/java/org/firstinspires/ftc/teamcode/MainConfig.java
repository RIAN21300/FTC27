package org.firstinspires.ftc.teamcode;

public class MainConfig {
    public static class Drivetrain {
        // Control Hub
        public static class port {
            public static final int front_left = 0;
            public static final int back_left = 1;
            public static final int back_right = 2;
            public static final int front_right = 3;
        }
    }

    public static class Intake {
        // Expansion Hub
        public static final int port = 0;
        public static final double power = 1.0;
    }

    public static class Transfer {
        // Expansion Hub
        public static final int port = 2;
        public static final double power = 1.0;
    }

    public static class Shooter {
        public static class Flywheel {
            // Expansion Hub
            public static final int port = 1;
            public static final double anglePerCount = 0.2243994753;
            public static final double maxVelocity = 627.3132211;
            public static final double[] relVelocity = {0.0, 0.25, 0.265};

            public static class PIDCoefficient {
                public static final double kP = 8.5e-3;
                public static final double kI = 0.0;
                public static final double kD = 0.0;
            }

            public static class FFCoefficient {
                public static final double kS = 0.14505437;
                public static final double kV = 0.00050435;
            }
        }

        public static class Compression {
            // Control Hub
            public static final int servoPort = 0;
            public static final double[] position = {0.36, 0.25, 0.16};
        }
    }
}
