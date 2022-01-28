package com.aercie.ftc.robot

import com.aercie.ftc.persistence.Calibration
import com.aercie.ftc.persistence.PersistentObject
import com.aercie.ftc.persistence.Secrets
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.Servo

interface IRobot {
    val drive: Drive

    val calibration: Calibration
    val secrets: Secrets

    val m0: DcMotor
    val m1: DcMotor
    val m2: DcMotor
    val m3: DcMotor
    val m4: DcMotor
    val m5: DcMotor
    val m6: DcMotor

    val s0: Servo
    val s6: Servo

    fun init(p: OpMode)

    fun carousel(s: Double)
    fun intake(s: Double)
    fun linear(s: Double)
    fun bucket(p: Double)
    var bucket: Int
    fun capstone(p: Double)
    var capstone: Int
}
