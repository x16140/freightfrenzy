package com.aercie.util.extensions

import kotlin.math.abs
import kotlin.math.round

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier).toInt() / multiplier
}

fun Double.map(from: ClosedRange<Double>, to: ClosedRange<Double>): Double =
    if (to.start <= to.endInclusive) (this - from.start) * (to.endInclusive - to.start) / (from.endInclusive - from.start) + to.start
    else (to.endInclusive + to.start) - this.map(from, (to.endInclusive)..(to.start))

fun Double.Companion.normalize(vararg values: Double): List<Double> {
    var max = 0.0

    for (value in values) {
        val v = abs(value)

        if (v > max)
            max = v
    }

    return values.map { it / max }
}

fun List<Double>.normalize(): List<Double> =
    Double.normalize(*this.toDoubleArray())
