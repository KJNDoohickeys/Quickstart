package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp
public class ShooterCompressionTesting extends OpMode {

    public DcMotorEx Top;
    public DcMotorEx Bottom;
    public double TopVelocity = 0;
    public double BottomVelocity = 0;
    public boolean On = false;
    @Override
    public void init() {
        Top = hardwareMap.get(DcMotorEx.class, "Top");
        Bottom = hardwareMap.get(DcMotorEx.class, "Bottom");
    }

    @Override
    public void loop() {
        if (gamepad1.rightBumperWasPressed()) {
            On = !On;
        }
        if (On) {
            Top.setVelocity(TopVelocity);
            Bottom.setVelocity(BottomVelocity);
        }
        if (!On) {
            Top.setVelocity(0);
            Bottom.setVelocity(0);
        }
        if (gamepad1.dpadUpWasPressed()) {
            TopVelocity += 100;
        }
        if (gamepad1.dpadDownWasPressed()) {
            TopVelocity -= 100;
        }
        if (gamepad1.dpadRightWasPressed()) {
            BottomVelocity += 100;
        }
        if (gamepad1.dpadRightWasPressed()) {
            BottomVelocity += 100;
        }
        telemetry.addData("Motor State:", On);
        telemetry.addData("Top Velocity Target:", TopVelocity);
        telemetry.addData("Top Velocity:", Top.getVelocity());
        telemetry.addData("Bottom Velocity Target:", BottomVelocity);
        telemetry.addData("Bottom Velocity:", Bottom.getVelocity());
        telemetry.update();
    }
}