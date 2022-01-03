package io.arct.ftc

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DcMotor

class Debug : OpMode() {
    lateinit var m1: DcMotor
    lateinit var m2: DcMotor
    lateinit var m3: DcMotor
    lateinit var m4: DcMotor

    override fun init() {
        m1 = hardwareMap.dcMotor.get("m1")
        m2 = hardwareMap.dcMotor.get("m2")
        m3 = hardwareMap.dcMotor.get("m3")
        m4 = hardwareMap.dcMotor.get("m4")
    }

    override fun loop() {
        m1.power = gamepad1.left_stick_x.toDouble()
        m2.power = gamepad1.left_stick_y.toDouble()
        m3.power = gamepad1.right_stick_x.toDouble()
        m4.power = gamepad1.right_stick_y.toDouble()
    }
}
