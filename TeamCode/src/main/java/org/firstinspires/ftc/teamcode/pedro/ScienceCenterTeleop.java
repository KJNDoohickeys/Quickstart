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
public class ScienceCenterTeleop extends OpMode {
    DcMotor frontLeftMotor;
    DcMotor backLeftMotor;
    DcMotor frontRightMotor;
    DcMotor backRightMotor;
    Servo TurretServo;
    DcMotorEx Shooter;
    ElapsedTime elapsedTime = new ElapsedTime();
    double TurretPosition = 0.5;
    double ShooterTargetVelocity = 500;
    boolean ShooterOn = false;
    double GatePosition = 0.25;
    Servo GateServo;
    boolean FirstPress = true;
    @Override
    public void init() {
        Shooter = hardwareMap.get(DcMotorEx.class, "Shooter");
        TurretServo = hardwareMap.get(Servo.class, "Turret");
        backLeftMotor = hardwareMap.get(DcMotor.class, "LeftBack");
        frontLeftMotor = hardwareMap.get(DcMotor.class, "LeftFront");
        backRightMotor = hardwareMap.get(DcMotor.class, "RightBack");
        frontRightMotor = hardwareMap.get(DcMotor.class, "RightFront");
        backLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE );
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        Shooter.setDirection(DcMotorSimple.Direction.REVERSE);
        GateServo = hardwareMap.get(Servo.class, "Gate");
    }
    public void start() {
        GateServo.setPosition(.25);
    }
    public void loop() {
        double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
        double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
        double rx = gamepad1.right_stick_x;

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        frontLeftMotor.setPower(frontLeftPower);
        backLeftMotor.setPower(backLeftPower);
        frontRightMotor.setPower(frontRightPower);
        backRightMotor.setPower(backRightPower);

        if (gamepad1.dpadLeftWasPressed()) {
            TurretPosition += .05;
        }
        if (gamepad1.dpadRightWasPressed()) {
            TurretPosition -= .05;
        }
        if (gamepad1.dpadUpWasPressed()) {
            ShooterTargetVelocity += 100;
        }
        if (gamepad1.dpadDownWasPressed()) {
            ShooterTargetVelocity -= 100;
        }
        if (gamepad1.rightBumperWasPressed()) {
            ShooterOn = !ShooterOn;
        }
        if (ShooterOn) {
            Shooter.setVelocity(ShooterTargetVelocity);
        }
        if (!ShooterOn) {
            Shooter.setVelocity(0);
        }
        if (gamepad2.dpadUpWasPressed()) {
            GatePosition += .05;
        }
        if (gamepad2.dpadDownWasPressed()) {
            GatePosition -= .05;
        }
        if (gamepad1.leftBumperWasPressed()) {
            FirstPress = false;
            elapsedTime.reset();
            GateServo.setPosition(.40);
        }
        if (elapsedTime.milliseconds() >= 400 && FirstPress == false) {
            GateServo.setPosition(.25);
        }
        TurretServo.setPosition(TurretPosition);
        telemetry.addData("Shooter Target Velocity:", ShooterTargetVelocity);
        telemetry.addData("Shooter Real Velocity", Shooter.getVelocity());
        telemetry.addData("Shooter On?:", ShooterOn);
        telemetry.addData("Target Turret Position", TurretPosition);
        telemetry.addData("Turret Real Position", TurretServo.getPosition());
        telemetry.addData("Gate Position", GateServo.getPosition());
        telemetry.addData("Time", elapsedTime.milliseconds());
        telemetry.update();
    }
}
