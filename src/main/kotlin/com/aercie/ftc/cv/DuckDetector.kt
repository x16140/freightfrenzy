package com.aercie.ftc.cv

import android.graphics.Bitmap
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.vuforia.PIXEL_FORMAT
import com.vuforia.Vuforia
import org.firstinspires.ftc.robotcore.external.ClassFactory
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import kotlin.math.pow

class DuckDetector(
    program: OpMode,
    key: String,
    cameraDirection: VuforiaLocalizer.CameraDirection = VuforiaLocalizer.CameraDirection.FRONT
) {
    object Config {
        val locations = listOf(
            Rectangle(0, 0, 0, 0),
            Rectangle(0, 0, 0, 0),
            Rectangle(0, 0, 0, 0)
        )

        val average = { p: List<Color> ->
            var ha = .0
            var sa = .0
            var va = .0

            for ((h, s, v) in p) {
                ha += h
                sa += s
                va += v
            }

            Color(ha / p.size, sa / p.size, va / p.size)
        }

        val distance = { (h, s, v): Color ->
            val target = Color(10.0, 10.0, 10.0)
            val weights = Triple(2.0, 1.0, 1.0)

            val dh = weights.first * (target.h - h).pow(2)
            val ds = weights.second * (target.s - s).pow(2)
            val dv = weights.third * (target.v - v).pow(2)
            dh + ds + dv
        }
    }

    init {
        Vuforia.setFrameFormat(PIXEL_FORMAT.RGB565, true)
    }

    private val vuforia = ClassFactory.getInstance().createVuforia(VuforiaLocalizer.Parameters(
        program.hardwareMap.appContext.resources.getIdentifier(
            "cameraMonitorViewId",
            "id",
            program.hardwareMap.appContext.packageName
        )
    ).also {
        it.vuforiaLicenseKey = key
        it.cameraDirection = cameraDirection
    }).also {
        it.enableConvertFrameToBitmap()
        it.frameQueueCapacity = 5
    }

    fun scan(save: Boolean = false): DuckState {
        val frame = vuforia.frameQueue.take()
        val bitmap = vuforia.convertFrameToBitmap(frame)!!
        frame.close()

        if (save)
            save(bitmap, "duck-full")

        val (a, b, c) = Config.locations.mapIndexed { i, (x, y, w, h) ->
            val sub = Bitmap.createBitmap(bitmap, x, y, w, h)

            if (save)
                save(sub, "duck-$i")

            Config.distance(sub.average)
        }

        return when {
            a <= b && a <= c -> DuckState.A
            b < a && b <= c -> DuckState.B
            else -> DuckState.C
        }
    }

    private fun save(bitmap: Bitmap, label: Any) {
        try {
            val f = File("/sdcard/$label.jpg")

            if (f.exists())
                f.delete()

            val stream = FileOutputStream(f)

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}

val Bitmap.average: Color
    get() {
    val total = width * height
    val pixels = IntArray(total)

    getPixels(pixels, 0, width, 0, 0, width, height)

    return DuckDetector.Config.average(pixels.map {
        Color.rgb(
            android.graphics.Color.red(it),
            android.graphics.Color.green(it),
            android.graphics.Color.blue(it)
        )
    })
}
