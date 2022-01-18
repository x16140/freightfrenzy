package io.arct.util.units

import kotlin.math.PI
import kotlin.math.atan2

val Number.deg get() = Angle(toDouble() * PI / 180)
val Number.rad get() = Angle(toDouble())
val Number.rev get() = Angle(toDouble() * 2 * PI)

data class Angle(val rad: Double) {
    val deg = rad * 180.0 / PI

    val normal: Angle get() {
        val r = deg % 360

        return when {
            r.isNaN() -> r
            r > 180 -> r - 360
            r <= -180 -> r + 360
            else -> r
        }.deg
    }

    val general: Angle get() {
        val r = normal.deg

        return when {
            r.isNaN() -> r
            r < 0 -> 360 + r
            else -> r
        }.deg
    }

    operator fun unaryPlus(): Angle =
        this

    operator fun unaryMinus(): Angle =
        Angle(-rad)

    operator fun plus(rhs: Angle): Angle =
        Angle(rad + rhs.rad)

    operator fun minus(rhs: Angle): Angle =
        Angle(rad - rhs.rad)

    operator fun times(rhs: Double): Angle =
        Angle(rad * rhs)

    operator fun div(rhs: Angle): Double =
        rad / rhs.rad

    operator fun rem(rhs: Angle): Angle =
        Angle(rad % rhs.rad)

    operator fun compareTo(rhs: Angle): Int =
        (rad - rhs.rad).toInt()

    override fun toString(): String =
        "$rad"

    companion object {
        val Forward = 0.deg
        val Right = 90.deg
        val Backward = 180.deg
        val Left = (-90).deg

        fun fromCoordinates(x: Double, y: Double): Angle? =
            if (x > 1 || y > 1) null
            else if (x != 0.0 || y != 0.0) (atan2(y, -x) * (180 / Math.PI) - 90).deg
            else null

        fun fromDegrees(deg: Number) =
            deg.deg
    }
}
