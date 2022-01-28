package com.aercie.ftc.persistence

import kotlinx.serialization.Serializable

@Serializable
data class Calibration(
    val tpcm: Double
)

@Serializable
data class Secrets(
    val vuforia: String
)
