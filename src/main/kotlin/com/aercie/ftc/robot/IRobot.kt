package com.aercie.ftc.robot

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.Servo

interface IRobot {
    val drive: Drive

    val m0: DcMotor
    val m1: DcMotor
    val m2: DcMotor
    val m3: DcMotor
    val m4: DcMotor
    val m5: DcMotor
    val m6: DcMotor

    val s0: Servo
    val s6: Servo

    fun init()
}
