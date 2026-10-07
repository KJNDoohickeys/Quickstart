package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.pedro.subsystems.TurretTracking;

@TeleOp
public class TurretTest extends OpMode {
    TurretTracking track = new TurretTracking();
    private Follower follower;
    Pose startPose = new Pose(72, 72, 90);
    @Override
    public void init() {
        follower = Constants.create(hardwareMap);
        follower.setPose(startPose);
    }
    public void start() {
        track.init(hardwareMap);
    }
    @Override
    public void loop() {
        follower.manual(
                -gamepad1.left_stick_y,
                gamepad1.left_stick_x,
                gamepad1.right_stick_x
        );
        track.calculateHeading(follower);
        follower.update();
        telemetry.addData("Turret Angle:", track.getTurretAngle());
        telemetry.addData("X Position", follower.pose().x());
        telemetry.addData("Y Position", follower.pose().y());
        telemetry.addData("Heading", follower.pose().heading());
        telemetry.update();
    }
}