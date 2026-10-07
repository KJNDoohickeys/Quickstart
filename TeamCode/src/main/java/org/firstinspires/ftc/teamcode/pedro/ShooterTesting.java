package org.firstinspires.ftc.teamcode.pedro;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.FocusControl;

@TeleOp
public class ShooterTesting extends OpMode {
    Servo AdjusterTop;
    Servo AdjusterBottom;
    double TopAdjusterPosition = .5;
    double BottomAdjusterPosition = .5;
    DcMotorEx Shooter;
    double ShooterVelocity = 500;
    boolean ShooterOn = false;
    enum ShooterCompression {
        POLLEN,
        NECTAR
    }
    ShooterCompression ShooterState = ShooterCompression.POLLEN;
    @Override
    public void init() {
        AdjusterTop = hardwareMap.get(Servo.class, "AdjusterTop");
        AdjusterBottom = hardwareMap.get(Servo.class, "AdjusterBottom");
        Shooter = hardwareMap.get(DcMotorEx.class, "Shooter");
        AdjusterBottom.setDirection(Servo.Direction.REVERSE);
        AdjusterTop.setDirection(Servo.Direction.FORWARD);
        Shooter.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void start() {
        AdjusterBottom.setPosition(.375);
        AdjusterTop.setPosition(.375);
    }
    public void loop() {
        if (gamepad1.bWasPressed()) {
            if (ShooterState == ShooterCompression.POLLEN) {
                ShooterState = ShooterCompression.NECTAR;
            }
            else if (ShooterState == ShooterCompression.NECTAR) {
                ShooterState = ShooterCompression.POLLEN;
            }
        }
        if (gamepad1.dpadUpWasPressed()) {
            TopAdjusterPosition += .01;
            BottomAdjusterPosition += .01;
        }
        if (gamepad1.dpadDownWasPressed()) {
            TopAdjusterPosition -= .01;
            BottomAdjusterPosition -= .01;
        }
        if (gamepad1.rightBumperWasPressed()) {
            ShooterVelocity += 100;
        }
        if (gamepad1.leftBumperWasPressed()) {
            ShooterVelocity -= 100;
        }
        if (gamepad1.aWasPressed()) {
            ShooterOn = !ShooterOn;
        }
        if (ShooterOn) {
            Shooter.setVelocity(ShooterVelocity);
        }
        if (!ShooterOn) {
            Shooter.setVelocity(0);
        }
        if (ShooterState == ShooterCompression.POLLEN) {
            AdjusterTop.setPosition(.375);
            AdjusterBottom.setPosition(.375);
        }
        if (ShooterState == ShooterCompression.NECTAR) {
            AdjusterBottom.setPosition(.6);
            AdjusterTop.setPosition(.6);
        }
        telemetry.addData("Top Pos:", TopAdjusterPosition);
        telemetry.addData("Bot Pos:", BottomAdjusterPosition);
        telemetry.addData("Target Vel:", ShooterVelocity);
        telemetry.addData("Actual Vel:", Shooter.getVelocity());
        telemetry.addData("Compression State:", ShooterState);
        telemetry.update();
    }
}
