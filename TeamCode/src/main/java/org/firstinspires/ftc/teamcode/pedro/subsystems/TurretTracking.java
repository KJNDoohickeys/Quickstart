package org.firstinspires.ftc.teamcode.pedro.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
@Config
public class TurretTracking {
    Servo Turret;
    static double targetX = 144;
    static double targetY = 144;
    double deltaX;
    double deltaY;
    double theta;
    double turretAngle;

    public void init(HardwareMap hardwareMap) {
        Turret = hardwareMap.get(Servo.class, "Servo");
        Turret.setPosition(.5);
    }
    public void calculateHeading(Follower follower){
        //RADIANS
        deltaX = targetX - follower.pose().x();
        deltaY = targetY - follower.pose().y();
        theta = Math.toDegrees(Math.atan2(deltaY,deltaX));
        turretAngle = -1*(theta - Math.toDegrees(follower.pose().heading()));
        if (turretAngle > 180.0) {
            turretAngle -= 360.0;
        }
        if (turretAngle < -180.0) {
            turretAngle += 360.0;
        }
        Turret.setPosition(AngleToPosition(turretAngle));
    }
    public double getTurretAngle() {
        return turretAngle;
    }

    public double AngleToPosition(double angleDegrees) {
        return 0.5 + angleDegrees / 327.0;
    }

}
