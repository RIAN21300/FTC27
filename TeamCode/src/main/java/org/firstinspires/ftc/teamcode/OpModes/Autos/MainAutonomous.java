package org.firstinspires.ftc.teamcode.OpModes.Autos;

import com.acmerobotics.dashboard.FtcDashboard;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;
import static com.pedropathing.ivy.commands.Commands.*;
import com.pedropathing.ivy.Scheduler;
import com.pedropathing.math.Pose;
import static com.pedropathing.api.Paths.*;
import com.pedropathing.paths.Path;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;
import static com.pedropathing.ivy.groups.Groups.*;

import org.firstinspires.ftc.teamcode.MainRobot;
import org.firstinspires.ftc.teamcode.pedro.Constants;

import dev.nextftc.robot.Telemetry;
import dev.nextftc.robot.opmode.BulkReadHook;
import dev.nextftc.robot.opmode.NextAutonomous;
import dev.nextftc.robot.opmode.NextOpMode;
import dev.nextftc.robot.triggers.CommandGamepad;

@NextAutonomous(name = "Main Autonomous")
public class MainAutonomous extends NextOpMode {
    private final MainRobot robot;
    CommandGamepad driver = new CommandGamepad(gamepad1);
    private final Follower follower;
    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose startPose = poseFactory.of(24, 72, 0); // TODO: change these
    private final Pose intakePose = poseFactory.of(24, 48, 0);
    private final Pose scorePose = poseFactory.of(48, 72, 180);
    private final Pose parkPose = poseFactory.of(24, 24, 90);

    public MainAutonomous(MainRobot robot) {
        super(robot, BulkReadHook.INSTANCE);
        this.robot = robot;

        Scheduler.reset();

        follower = Constants.create(hardwareMap);
        follower.setPose(startPose);
        follower.update();

        Telemetry.addBackend(FtcDashboard.getInstance().getTelemetry());
    }

    private Path LinearLine(Pose A, Pose B) {
        return line(A, B).linear(A, B);
    }

    private Path toIntake() { return LinearLine(startPose, intakePose); }
    private Path toScore() { return LinearLine(intakePose, scorePose); }
    private Path toPark() { return LinearLine(scorePose, parkPose); }

    @Override
    public void disabledPeriodic() {

    }

    @Override
    public void start() {

    }

    @Override
    public void periodic() {
        follower.update();
        Scheduler.execute();

        Telemetry.log("X", follower.pose().x());
        Telemetry.log("Y", follower.pose().y());
        Telemetry.log("Heading", Math.toDegrees(follower.pose().heading()));
        Telemetry.log("Follower Mode", follower.mode());
    }

    @Override
    public void end() {

    }
}
