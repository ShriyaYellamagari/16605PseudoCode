package org.firstinspires.ftc.teamcode.subsystems

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.rowanmcalpin.nextftc.core.Subsystem
import com.rowanmcalpin.nextftc.ftc.OpModeData

object Outake : Subsystem() {

    lateinit var motor1: DcMotor
    lateinit var motor2: DcMotor

    private const val OUTTAKE_POWER = 0.9

    override fun initialize() {
        motor1 = OpModeData.hardwareMap.get(DcMotor::class.java, "motor1")
        motor2 = OpModeData.hardwareMap.get(DcMotor::class.java, "motor2")

        // Set directions — change if needed
        motor1.direction = DcMotorSimple.Direction.FORWARD
        motor2.direction = DcMotorSimple.Direction.REVERSE  // ← FIX for opposite spinning

        motor1.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        motor2.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
    }

    fun outtake() {
        motor1.power = OUTTAKE_POWER
        motor2.power = OUTTAKE_POWER
    }

    fun stop() {
        motor1.power = 0.0
        motor2.power = 0.0
    }
}
