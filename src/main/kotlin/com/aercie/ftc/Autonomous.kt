package com.aercie.ftc

import com.aercie.ftc.cv.DuckDetector
import com.aercie.ftc.robot.IRobot
import com.aercie.ftc.robot.Robot
import com.aercie.util.units.Angle
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer

class Autonomous : LinearOpMode(), IRobot by Robot() {
    val detect by lazy { DuckDetector(this, secrets.vuforia, VuforiaLocalizer.CameraDirection.FRONT ) }

    private fun prepare() {
        init(this)
        bucket = 1

        // Init
//        detect
    }

    private fun run() {
//        val state = detect.scan(true)
//        telemetry.addLine(state.toString())
//        telemetry.update()
//        sleep(5000)
//        return

        drive.move(Angle.Left, 0.6, 1150)
        drive.move(Angle.Backward, 0.2, 200)

        linear(.75)
        sleep(2700)
        linear(.0)

        bucket = 3
        sleep(1000)

        bucket = 1
        sleep(1000)

        linear(-.75 / 2)
        sleep(5000)
        linear(.0)

        drive.rotate(-0.8, 300)
//        drive.move(Angle.Backward, 0.4, 1800)
        drive.move(Angle.Backward, 0.6, 200)
        drive.move(Angle.Right, 0.6, 2000)

//        drive.move(Angle.Forward, 0.3, 270)
//        drive.move(Angle.Right, 0.3, 600)

//        drive.rotate(0.4, 250)
        drive.move(Angle.Backward, 0.4, 600)

        drive.rotate(0.2, 250)
        drive.move(Angle.Backward, 0.4, 250)

        drive.move(Angle.Backward, 0.1)
        carousel(.5)
        sleep(3000)
        carousel(.0)
        drive.move(Angle.Backward, .0)

        drive.rotate(.4, 700)
//        drive.move(Angle.Forward, 0.4, 1000)
        drive.move(Angle.Left, 0.6, 300)
        drive.move(Angle.Forward, 0.4, 1000)

        drive.move(Angle.Backward, 1.0, 700)
        drive.move(Angle.Right, 1.0, 800)
        drive.move(Angle.Backward, .6, 1200)
    }

    override fun runOpMode() {
        prepare()
        waitForStart()
        run()
    }
}
