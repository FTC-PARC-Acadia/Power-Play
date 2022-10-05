package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake extends MecanumDrive {

    private Gamepad gamepad2;
    private DcMotor lift;
    private Servo servo;
    private int position;

    public Intake(DcMotor frontLeftDrive, DcMotor frontRightDrive, DcMotor backLeftDrive, DcMotor backRightDrive, DcMotor lift, Servo servo, Gamepad gamepad1, Gamepad gamepad2, BNO055IMU imu) {
        super(frontLeftDrive, frontRightDrive, backLeftDrive, backRightDrive, gamepad1, imu);

        this.lift = lift;
        lift.setTargetPosition(position);
        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        this.servo = servo;
        this.gamepad2 = gamepad2;

        position = 100;
    }

    public void lift() {
        lift.setTargetPosition(position);

        if (gamepad2.right_bumper && lift.isBusy()) {
            lift.setTargetPosition(100);
            lift.setPower(.5);
        } else if (gamepad2.right_trigger > 0 && lift.isBusy()) {
            lift.setTargetPosition(0);
            lift.setPower(-.5);
        } else if (!lift.isBusy()) {
            lift.setPower(0);
            position = position == 100  ? 0 : 100;
        } else {
            lift.setPower(0);
        }
    }
}