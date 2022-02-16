package com.aercie.ftc

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.aercie.ftc.robot.IRobot
import com.aercie.ftc.robot.Robot
import com.aercie.util.extensions.onRising
import com.aercie.util.extensions.value

class Drive : OpMode(), IRobot by Robot() {
    var capstoneState = 0
    var bucketState = 0

    override fun init() {
        init(this)
    }

    override fun loop() {
        // Movement
        val sr = gamepad1.right_trigger + gamepad1.right_bumper.value * 0.6
        val sl = gamepad1.left_trigger + gamepad1.left_bumper.value * 0.6
        val spot = sr - sl

        if (spot == .0)
            drive.gamepad(gamepad1)
        else
            drive.rotate(spot)

        // Carousel
        carousel(gamepad2.left_bumper.value.toDouble() - gamepad2.left_trigger)

        // Intake
        intake(gamepad2.b.value.toDouble() - gamepad2.x.value)

        // Capstone
        capstone = capstoneState

        // Bucket
        bucket = bucketState

        gamepad2::dpad_up.onRising {
            if (bucketState < 2)
                bucketState += 1
        }

        gamepad2::dpad_down.onRising {
            if (bucketState > 0)
                bucketState -= 1
        }

        // Linear
        linear(-gamepad2.right_stick_y.toDouble())
    }
}
