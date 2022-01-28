package com.aercie.ftc

import com.aercie.ftc.robot.IRobot
import com.aercie.ftc.robot.Robot
import com.qualcomm.robotcore.eventloop.opmode.OpMode

class Calibration : OpMode(), IRobot by Robot() {
    override fun init() {
        init(this)
    }

    override fun loop() {
        TODO("Not yet implemented")
    }
}
