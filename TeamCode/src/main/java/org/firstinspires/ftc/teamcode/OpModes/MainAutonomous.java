package org.firstinspires.ftc.teamcode.OpModes;

import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.delays.Delay;
import dev.nextftc.core.commands.groups.ParallelGroup;
import dev.nextftc.core.commands.groups.SequentialGroup;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.extensions.pedro.FollowPath;
import dev.nextftc.extensions.pedro.PedroComponent;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;

public class MainAutonomous extends NextFTCOpMode {
    public MainAutonomous() {
        addComponents(
                new SubsystemComponent(Intake.INSTANCE),
                BulkReadComponent.INSTANCE,
                new PedroComponent(Constants::createFollower)
        );
    }

    private final Pose startPose = new Pose(72, 72, Math.toRadians(90));
    private final Pose pose2 = new Pose(100, 100, Math.toRadians(120));

    private PathChain path1, path2;

    PathChain LinePath(Pose A, Pose B) {
        return PedroComponent.follower().pathBuilder()
                .addPath(new BezierLine(A, B))
                .setLinearHeadingInterpolation(A.getHeading(), B.getHeading())
                .build();
    }

    public void buildPaths() {
        path1 = LinePath(startPose, pose2);
        path2 = LinePath(pose2, startPose);
    }

    private Command autonomousRoutine() {
        return new SequentialGroup(
                new ParallelGroup(
                        new FollowPath(path1),
                        Intake.INSTANCE.setOn
                ),
                new Delay(2),
                new ParallelGroup(
                        new FollowPath(path2),
                        Intake.INSTANCE.setOff
                )
        );
    }

    @Override
    public void onInit() {
        PedroComponent.follower().setStartingPose(startPose);
        buildPaths();
    }

    @Override
    public void onStartButtonPressed() {
        autonomousRoutine().schedule();
    }
}
