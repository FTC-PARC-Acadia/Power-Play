package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Lift {
    Gamepad gamepad2;
    DcMotor lift;

    private int pos = 0;

    public Lift(Gamepad gamepad2, DcMotor lift) {
        this.gamepad2 = gamepad2;
        this.lift = lift;

        lift.setDirection(DcMotor.Direction.REVERSE);
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setTargetPosition(0);
        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public Lift(DcMotor lift) {
        this.lift = lift;

        lift.setDirection(DcMotor.Direction.REVERSE);
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setTargetPosition(0);
        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

//    public void liftByLevel() {
//        int level = pos / 1250;
//        if (gamepad2.dpad_up && (level < 4) && lift.getCurrentPosition() >= pos - 100) {
//            pos += 1250;
//            lift.setTargetPosition(pos);
//            lift.setPower(1);
//        } else if (gamepad2.dpad_down && (level > 0) && lift.getCurrentPosition() <= pos + 100) {
//            pos -= 1250;
//            lift.setTargetPosition(pos);
//            lift.setPower(-1);
//        }
//    }

    public void liftByPush() {
        if (gamepad2.dpad_up && pos < 5000) {

            pos += 50;
            lift.setTargetPosition(pos);
            lift.setPower(1);
        } else if (gamepad2.dpad_down && pos > 50) {
            pos -= 50;
            lift.setTargetPosition(pos);
            lift.setPower(1);
        }

        if (!lift.isBusy()) {
            lift.setPower(0);
        }
    }

    public void adjust() {
        if (lift.getCurrentPosition() < pos) {
            lift.setPower(1);
        }
    }

    public void lift(int level) {
        pos = level*1000;
        lift.setTargetPosition(pos);
        lift.setPower(0.75);

        while (lift.isBusy()) {

        }

        if (!lift.isBusy()) {
            lift.setPower(0);
        }
    }
}
