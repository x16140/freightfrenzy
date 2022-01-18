package io.arct.ftc.robot

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DcMotor
import io.arct.util.extensions.normalize
import io.arct.util.units.Angle
import io.arct.util.units.deg
import kotlin.math.cos
import kotlin.math.sin

class Drive(
    vararg val motors: DcMotor,
    var invert: Boolean = false,
    val program: OpMode
) {
    fun move(direction: Angle, speed: Double): Drive {
        val dir = if (invert) -direction else direction

        val a = sin((dir + 45.deg).rad)
        val b = cos((dir + 45.deg).rad)

        val (x, y) = Double.normalize(a, b, upscale = true).map { speed * it }

        motors[0].power = x
        motors[1].power = y
        motors[2].power = x
        motors[3].power = y

        return this
    }

    fun turn(speed: Double, rotationSpeed: Double): Drive {
        val a = speed + if (rotationSpeed > 0) rotationSpeed else .0
        val b = speed - if (rotationSpeed < 0) rotationSpeed else .0

        val (l, r) = Double.normalize(a, b)

        motors[0].power = l
        motors[1].power = l
        motors[2].power = r
        motors[3].power = r

        return this
    }

    fun rotate(speed: Double): Drive {
        motors[0].power = speed
        motors[1].power = speed
        motors[2].power = -speed
        motors[3].power = -speed

        return this
    }
}
