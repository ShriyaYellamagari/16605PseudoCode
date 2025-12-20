package org.firstinspires.ftc.teamcode.subsystems

import com.qualcomm.robotcore.hardware.ColorSensor
import com.qualcomm.robotcore.hardware.DcMotor
import com.rowanmcalpin.nextftc.core.Subsystem
import com.rowanmcalpin.nextftc.ftc.OpModeData
import kotlin.math.abs

object Indexer : Subsystem() {

    // Hardware
    lateinit var indexerMotor: DcMotor
    lateinit var colorSensor: ColorSensor

    enum class ArtifactColor {
        PURPLE, GREEN, NONE
    }

    //tracking variable - indexer slot near indexer
    private var initialSlot = 0

    //All slots start with nothing
    private var slotContents = arrayOf(
        ArtifactColor.NONE,
        ArtifactColor.NONE,
        ArtifactColor.NONE
        )


    override fun initialize() {
        indexerMotor = OpModeData.hardwareMap.get(DcMotor::class.java, "indexerMotor")
        colorSensor = OpModeData.hardwareMap.get(ColorSensor::class.java, "colorSensor")

        indexerMotor.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        indexerMotor.mode = DcMotor.RunMode.RUN_USING_ENCODER
        indexerMotor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
    }

    fun getColorValues(): String {
        val red = colorSensor.red()
        val green = colorSensor.green()
        val blue = colorSensor.blue()

        return "R:$red G:$green B:$blue"
    }


    fun detectArtifactColor(): ArtifactColor {
        val red = colorSensor.red()
        val green = colorSensor.green()
        val blue = colorSensor.blue()

        // Purple target: RGB (144,7,255)
        val purpleR = 144
        val purpleG = 7
        val purpleB = 255

        // Green target: RGB (0,192,0)
        val greenR = 0
        val greenG = 192
        val greenB = 0

        val tolerance = 50 //change accordingly

        if (abs(red - purpleR) < tolerance &&
            abs(green - purpleG) < tolerance &&
            abs(blue - purpleB) < tolerance) {
            return ArtifactColor.PURPLE
        }

        if (abs(red - greenR) < tolerance &&
            abs(green - greenG) < tolerance &&
            abs(blue - greenB) < tolerance) {
            return ArtifactColor.GREEN
        }

        return ArtifactColor.NONE
    }


    fun slotForColor (color: ArtifactColor): Int {
        //slot 0 - purple1
        //slot 1 - purple2
        //slot 3 - green

        when (color) {
            ArtifactColor.PURPLE -> {
                // Find first empty purple slot
                if (slotContents[0] == ArtifactColor.NONE)
                    return 0
                if (slotContents[1] == ArtifactColor.NONE)
                    return 1

                return -1  // Both purple slots full
            }
            ArtifactColor.GREEN -> {
                // Check if green slot is empty
                if (slotContents[2] == ArtifactColor.NONE)
                    return 2
                return -1  // Green slot full
            }

            else -> return -1  // No ball detected
        }
    }



    fun rotateToSlot(indexerSlot: Int) {
        if (indexerSlot < 0 || indexerSlot > 2) return

        val slotsToMove = (indexerSlot - initialSlot + 3) % 3

        if (slotsToMove == 0) return

        val ticksPerSlot = 300 // CHANGE?!!!
        val targetPosition = indexerMotor.currentPosition + (slotsToMove * ticksPerSlot)

        indexerMotor.targetPosition = targetPosition
        indexerMotor.mode = DcMotor.RunMode.RUN_TO_POSITION
        indexerMotor.power = 0.5

        initialSlot = indexerSlot
    }


    fun incomingBall() {
        val detectedColor = detectArtifactColor()

        if (detectedColor == ArtifactColor.NONE) {
            return
        }

        val targetSlot = slotForColor(detectedColor)

        // If indexer is full(no empty slots), do nothing
        if (targetSlot == -1) {
            return //could change later to make the intake spin out to make ball exit
        }

        rotateToSlot(targetSlot)

        slotContents[targetSlot] = detectedColor
    }

}