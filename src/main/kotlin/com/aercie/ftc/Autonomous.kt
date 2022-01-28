package com.aercie.ftc

import com.aercie.ftc.robot.IRobot
import com.aercie.ftc.robot.Robot
import com.aercie.util.units.Angle
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode

class Autonomous : LinearOpMode(), IRobot by Robot() {
    private fun prepare() {
        init(this)
    }

    private fun run() {
        drive.move(Angle.Forward, 0.7, 100.0)
    }

    override fun runOpMode() {
        prepare()
        waitForStart()
        run()
    }
}
