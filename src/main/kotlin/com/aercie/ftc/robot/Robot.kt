package com.aercie.ftc.robot

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.Servo

class Robot(private val p: OpMode) : IRobot {
    override lateinit var drive: Drive

    override lateinit var m0: DcMotor
    override lateinit var m1: DcMotor
    override lateinit var m2: DcMotor
    override lateinit var m3: DcMotor
    override lateinit var m4: DcMotor
    override lateinit var m5: DcMotor
    override lateinit var m6: DcMotor

    override lateinit var s0: Servo
    override lateinit var s6: Servo

    override fun init() {
        m0 = p.hardwareMap.dcMotor.get("m0")
        m1 = p.hardwareMap.dcMotor.get("m1")
        m2 = p.hardwareMap.dcMotor.get("m2")
        m3 = p.hardwareMap.dcMotor.get("m3")
        m4 = p.hardwareMap.dcMotor.get("m4")
        m5 = p.hardwareMap.dcMotor.get("m5")
        m6 = p.hardwareMap.dcMotor.get("m6")
        s0 = p.hardwareMap.servo.get("s0")
        s6 = p.hardwareMap.servo.get("s6")

        drive = Drive(m0, m1, m2, m3, program = p)
    }
}
