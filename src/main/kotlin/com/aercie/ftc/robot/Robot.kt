package com.aercie.ftc.robot

import com.aercie.ftc.persistence.Calibration
import com.aercie.ftc.persistence.PersistentObject
import com.aercie.ftc.persistence.Secrets
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.Servo

class Robot : IRobot {
    object Config {
        val bucket = listOf(.60, .58, .00)
        var capstone = listOf(.3, .2)
    }

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

    override lateinit var calibration: Calibration
    override lateinit var secrets: Secrets

    override fun init(p: OpMode) {
        calibration = PersistentObject.load("/sdcard/calibration.dat")
        secrets = PersistentObject.load("/sdcard/secrets.dat")

        m0 = p.hardwareMap.dcMotor.get("m0")
        m1 = p.hardwareMap.dcMotor.get("m1")
        m2 = p.hardwareMap.dcMotor.get("m2")
        m3 = p.hardwareMap.dcMotor.get("m3")
        m4 = p.hardwareMap.dcMotor.get("m4")
        m5 = p.hardwareMap.dcMotor.get("m5")
        m6 = p.hardwareMap.dcMotor.get("m6")
        s0 = p.hardwareMap.servo.get("s0")
        s6 = p.hardwareMap.servo.get("s6")

        drive = Drive(m0, m1, m2, m3, program = p, multiplier = calibration.tpcm)
    }

    override fun carousel(s: Double) {
        m6.power = s
    }

    override fun intake(s: Double) {
        m5.power = -s
    }

    override fun linear(s: Double) {
        m4.power = s
    }

    override fun bucket(p: Double) {
        s0.position = p
    }

    override var bucket: Int
        get() = TODO()
        set(v) = bucket(Config.bucket[v])

    override fun capstone(p: Double) {
        s6.position = p
    }

    override var capstone: Int
        get() = TODO()
        set(v) = capstone(Config.capstone[v])
}
