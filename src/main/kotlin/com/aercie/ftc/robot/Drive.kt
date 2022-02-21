package com.aercie.ftc.robot

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.Gamepad
import com.aercie.util.extensions.normalize
import com.aercie.util.extensions.normalize2
import com.aercie.util.units.Angle
import com.aercie.util.units.deg
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import kotlin.math.*

class Drive(
    vararg val motors: DcMotor,
    var invert: Boolean = false,
    val program: OpMode,
    val multiplier: Double = 1.0,
) {
    fun free() {
        motors[0].mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        motors[1].mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        motors[2].mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        motors[3].mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
    }

    fun position() {
        motors[0].mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        motors[1].mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        motors[2].mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        motors[3].mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER

        motors[0].mode = DcMotor.RunMode.RUN_TO_POSITION
        motors[1].mode = DcMotor.RunMode.RUN_TO_POSITION
        motors[2].mode = DcMotor.RunMode.RUN_TO_POSITION
        motors[3].mode = DcMotor.RunMode.RUN_TO_POSITION
    }

    init {
        motors[0].zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        motors[1].zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        motors[2].zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        motors[3].zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE

        free()
    }

    fun move(direction: Angle, speed: Double): Drive {
        val dir = if (invert) -direction else direction

        val a = sin((dir + 45.deg).rad)
        val b = cos((dir + 45.deg).rad)

        val (x, y) = Double.normalize(a, b).map { speed * it }

        motors[0].power = -x
        motors[1].power = -y
        motors[2].power = x
        motors[3].power = y

        return this
    }

    fun move(direction: Angle, speed: Double, time: Long): Drive {
        move(direction, speed)

        if (program is LinearOpMode)
            program.sleep(time)
        else
            Thread.sleep(time)

        move(Angle.Forward, .0)
        return this
    }

    fun turn(speed: Double, rotationSpeed: Double): Drive {
        val a = speed + if (rotationSpeed > 0) rotationSpeed * sign(speed) else .0
        val b = speed - if (rotationSpeed < 0) rotationSpeed * sign(speed) else .0

        val (l, r) = Double.normalize2(a, b)

        motors[0].power = -l
        motors[1].power = -l
        motors[2].power = r
        motors[3].power = r

        return this
    }

    fun rotate(speed: Double): Drive {
        motors[0].power = -speed
        motors[1].power = -speed
        motors[2].power = -speed
        motors[3].power = -speed

        return this
    }

    fun rotate(speed: Double, time: Long): Drive {
        rotate(speed)

        if (program is LinearOpMode)
            program.sleep(time)
        else
            Thread.sleep(time)

        move(Angle.Forward, .0)
        return this
    }

    fun gamepad(g: Gamepad, invertX: Boolean = false, invertY: Boolean = false): Drive {
        if (g.left_stick_x == 0f && g.left_stick_y == 0f && (g.right_stick_y != 0f || g.right_stick_x != 0f)) {
            return turn(
                    g.right_stick_y * if (invertY) 1.0 else -1.0,
                    g.right_stick_x * if (invertX) -1.0 else 1.0
                )
        }

        return move(
            Angle.fromCoordinates(
                g.left_stick_x * if (invertX) -1.0 else 1.0,
                -g.left_stick_y * if (invertY) -1.0 else 1.0
            ) ?: Angle.Forward,

            min(sqrt(g.left_stick_x.pow(2) + g.left_stick_y.pow(2)), 1f).toDouble()
        )
    }
}
