package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {
    public static Follower create(HardwareMap h) {
        return new Follower(
                new PinpointLocalizer(h, localizerConfig),
                new Mecanum(h, drivetrainConfig),
                new Foresight(foresightConfig)
        );
    }
    public static MecanumConfig drivetrainConfig = new MecanumConfig(c -> {
        c.frontLeftName.set("lff");
        c.frontRightName.set("rtf");
        c.backLeftName.set("lfb");
        c.backRightName.set("rtb");
        c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
        c.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.backRightDirection.set(DcMotorSimple.Direction.FORWARD);
    });
    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
        c.name.set("odo");
        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        c.xPodOffset.set(-1.8754100048635889);
        c.yPodOffset.set(0.538099356523649);
        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.REVERSED);
        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.offsetUnits.set(DistanceUnit.INCH);
    });
    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.20559156853273783);
                Controller secondaryTranslationalForward = Controller.proportional(0.07596059106496005);
                Controller primaryTranslationalLateral = Controller.proportional(2.9716721528102243);
                Controller secondaryTranslationalLateral = Controller.proportional(1.0979534559210407);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.011053570265108047));
                c.brake.set(Controller.proportionalFeedforward(0.00939553472534184));

                c.headingFeedback.set(Controller.proportional(3.2909362861523195));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.037518175822414936, 0.006370359679465311));

                c.linearBrakeCoefficients.set(Matrix.diag(0.06416599774525904, 0.01211343084662271));
                c.quadraticBrakeCoefficients.set(Matrix.diag(0.0015123941157332709, 0.0012252231217713678));

                c.maxAchievableForwardVelocity.set(74.27676093694389);
                c.maxAchievableStrafeVelocity.set(47.99369605997761);
                c.naturalForwardDeceleration.set(34.244157181714925);
                c.naturalStrafeDeceleration.set(205.95308937142465);
            }
    );

}