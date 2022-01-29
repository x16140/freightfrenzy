package com.aercie.ftc

import com.aercie.ftc.cv.DuckDetector
import com.aercie.ftc.robot.IRobot
import com.aercie.ftc.robot.Robot
import com.aercie.util.units.Angle
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer

class Autonomous : LinearOpMode(), IRobot by Robot() {
    val detect by lazy { DuckDetector(this, secrets.vuforia) }

    private fun prepare() {
        init(this)

        // Init
//        detect
    }

    private fun run() {
//        val state = detect.scan(true)

        drive.move(Angle.Backward, 0.3, 200)
        drive.move(Angle.Right, 0.5, 1000)

    }

    override fun runOpMode() {
        prepare()
        waitForStart()
        run()
    }
}
