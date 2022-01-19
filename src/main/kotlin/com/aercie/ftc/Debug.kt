package com.aercie.ftc

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.Servo
import kotlin.math.max
import kotlin.math.min

class Debug : OpMode() {
    lateinit var m0: DcMotor
    lateinit var m1: DcMotor
    lateinit var m2: DcMotor
    lateinit var m3: DcMotor
    lateinit var m4: DcMotor
    lateinit var m5: DcMotor
    lateinit var m6: DcMotor

    lateinit var s0: Servo
    lateinit var s6: Servo

    var lb0 = false
    var rb0 = false
    var lb1 = false
    var rb1 = false

    var s0p = 0.0
    var s6p = 0.0

    override fun init() {
        m0 = hardwareMap.dcMotor.get("m0")
        m1 = hardwareMap.dcMotor.get("m1")
        m2 = hardwareMap.dcMotor.get("m2")
        m3 = hardwareMap.dcMotor.get("m3")
        m4 = hardwareMap.dcMotor.get("m4")
        m5 = hardwareMap.dcMotor.get("m5")
        m6 = hardwareMap.dcMotor.get("m6")
        s0 = hardwareMap.servo.get("s0")
        s6 = hardwareMap.servo.get("s6")
    }

    override fun loop() {
        if (lb0 && !gamepad1.left_bumper) lb0 = false
        if (rb0 && !gamepad1.right_bumper) rb0 = false
        if (lb1 && !gamepad2.left_bumper) lb1 = false
        if (rb1 && !gamepad2.right_bumper) rb1 = false

        m0.power = gamepad1.left_stick_x.toDouble()
        m1.power = gamepad1.left_stick_y.toDouble()
        m2.power = gamepad1.right_stick_x.toDouble()
        m3.power = gamepad1.right_stick_y.toDouble()
        m4.power = gamepad2.left_stick_x.toDouble()
        m5.power = gamepad2.left_stick_y.toDouble()
        m6.power = gamepad2.right_stick_x.toDouble()

        if (!lb0 && gamepad1.left_bumper) {
            s0p -= 0.05
            lb0 = true
        }

        if (!rb0 && gamepad1.right_bumper) {
            s0p += 0.05
            rb0 = true
        }

        if (!lb1 && gamepad2.left_bumper) {
            s6p -= 0.05
            lb1 = true
        }

        if (!rb1 && gamepad2.right_bumper) {
            s6p += 0.05
            rb1 = true
        }

        s0p = min(1.0, max(-1.0, s0p))
        s6p = min(1.0, max(-1.0, s6p))

        s0.position = s0p
        s6.position = s6p

        telemetry.addLine("s0: $s0p")
        telemetry.addLine("s6: $s6p")
        telemetry.update()
    }
}
