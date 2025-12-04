package org.firstinspires.ftc.teamcode.subsystems

import com.qualcomm.robotcore.hardware.CRServo
import com.rowanmcalpin.nextftc.core.Subsystem
import com.rowanmcalpin.nextftc.ftc.OpModeData

object Intake : Subsystem() {

    lateinit var intake1: CRServo
    lateinit var intake2: CRServo

    private const val INTAKE_POWER = 0.7
    private const val OUTTAKE_POWER = -0.7

    override fun initialize() {
        intake1 = OpModeData.hardwareMap.get(CRServo::class.java, "intake1")
        intake2 = OpModeData.hardwareMap.get(CRServo::class.java, "intake2")

        // If one wheel is spinning the wrong direction, uncomment this:
        // intake2.direction = DcMotorSimple.Direction.REVERSE
    }

    // ---- INTAKE ACTIONS ----
    fun intake() {
        intake1.power = INTAKE_POWER
        intake2.power = -INTAKE_POWER
    }

    fun outtake() {
        intake1.power = OUTTAKE_POWER
        intake2.power = -OUTTAKE_POWER
    }

    fun stop() {
        intake1.power = 0.0
        intake2.power = 0.0
    }
}
