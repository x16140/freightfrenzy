package com.aercie.ftc

import com.aercie.ftc.persistence.Calibration
import com.aercie.ftc.persistence.PersistentObject
import com.aercie.ftc.persistence.Secrets
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode

class InitPersistence : LinearOpMode() {
    override fun runOpMode() {
        PersistentObject.save(Secrets(
            vuforia = "<key>",
        ), "/sdcard/secrets.dat")

        PersistentObject.save(Calibration(
            tpcm = 1.0,
        ), "/sdcard/calibration.dat")
    }
}
