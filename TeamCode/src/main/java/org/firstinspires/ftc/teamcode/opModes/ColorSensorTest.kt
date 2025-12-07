package org.firstinspires.ftc.teamcode.opModes

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.rowanmcalpin.nextftc.ftc.NextFTCOpMode
import org.firstinspires.ftc.teamcode.subsystems.Indexer

@TeleOp(name = "Color Sensor Test")
class ColorSensorTest : NextFTCOpMode(Indexer) {

    override fun onStartButtonPressed() {
        while (opModeIsActive()) {

            // Get color values from sensor
            val colorValues = Indexer.getColorValues()

            // Show on driver station
            telemetry.addData("Color Sensor", colorValues)
            telemetry.update()
        }
    }
}