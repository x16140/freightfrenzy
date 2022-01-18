package io.arct.ftc.cv

import java.lang.Double.max
import java.lang.Double.min

data class Color(val h: Double, val s: Double, val v: Double) {
    override fun toString(): String = "hsv($h, $s, $v)"

    companion object {
        fun rgb(r: Int, g: Int, b: Int): Color {
            val _r = r.toDouble() / 255
            val _g = g.toDouble() / 255
            val _b = b.toDouble() / 255

            val cmax = max(_r, max(_g, _b))
            val cmin = min(_r, min(_g, _b))
            val diff = cmax - cmin

            val h = when (cmax) {
                cmin -> .0
                _r    -> (60 * ((_g - _b) / diff) + 360) % 360
                _g    -> (60 * ((_b - _r) / diff) + 120) % 360
                _b    -> (60 * ((_r - _g) / diff) + 240) % 360
                else -> -1.0
            }

            val s = if (cmax == .0) .0 else (diff / cmax) * 100
            val v = cmax * 100

            return Color(h, s, v)
        }
    }
}
