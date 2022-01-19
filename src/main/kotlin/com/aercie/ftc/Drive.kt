package com.aercie.ftc

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.aercie.ftc.robot.IRobot
import com.aercie.ftc.robot.Robot

class Drive : OpMode() {
    val robot = Robot(this)

    override fun init() {
        robot.init()
    }

    override fun loop() {
        robot.drive.gamepad(gamepad1)
    }
}
