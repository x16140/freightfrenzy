package com.aercie.ftc

import com.aercie.ftc.cv.DuckDetector
import com.aercie.ftc.robot.IRobot
import com.aercie.ftc.robot.Robot
import com.aercie.util.units.Angle
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer

class AutonomousB : LinearOpMode(), IRobot by Robot() {
    private fun prepare() {
        init(this)
    }

    private fun run() {
        drive.move(Angle.Backward, .8, 1600)
    }

    override fun runOpMode() {
        prepare()
        waitForStart()
        run()
    }
}
