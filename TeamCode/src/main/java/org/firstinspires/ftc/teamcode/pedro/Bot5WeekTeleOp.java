package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.follower.Follower;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name = "5 Week")
public class Bot5WeekTeleOp extends OpMode {

    private Follower follower;
    public DcMotorEx Top;
    public DcMotorEx Bottom;
    DcMotor leftBack;
    DcMotor rightBack;
    DcMotor leftFront;
    DcMotor rightFront;
    Servo servo;
    GoBildaPinpointDriver odo;
    public double TopVelocity = 0;
    public double BottomVelocity = 0;
    public boolean On = false;
    @Override
    public void init() {
        leftBack = hardwareMap.get(DcMotor.class, "lfb");
        rightBack = hardwareMap.get(DcMotor.class, "rtb");
        leftFront = hardwareMap.get(DcMotor.class, "lff");
        rightFront = hardwareMap.get(DcMotor.class, "rtf");
        leftBack.setDirection(DcMotorSimple.Direction.FORWARD);
        rightBack.setDirection(DcMotorSimple.Direction.FORWARD);
        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        servo = hardwareMap.get(Servo.class, "Servo");
        odo = hardwareMap.get(GoBildaPinpointDriver.class, "odo");
        odo.setOffsets(0, 30, DistanceUnit.MM);
    }

    @Override
    public void loop() {

    }
}