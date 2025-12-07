package org.firstinspires.ftc.teamcode.subsystems

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.rowanmcalpin.nextftc.core.Subsystem
import com.rowanmcalpin.nextftc.ftc.OpModeData

object Drivetrain : Subsystem() {
    lateinit var frontLeft: DcMotor
    lateinit var frontRight: DcMotor
    lateinit var rearLeft: DcMotor
    lateinit var rearRight: DcMotor

    override fun initialize() {
        frontLeft = OpModeData.hardwareMap.get(DcMotorEx::class.java, "frontLeft")
        frontRight = OpModeData.hardwareMap.get(DcMotorEx::class.java, "frontRight")
        rearLeft = OpModeData.hardwareMap.get(DcMotorEx::class.java, "rearLeft")
        rearRight = OpModeData.hardwareMap.get(DcMotorEx::class.java,"rearRight")

        //Motor directions
        frontLeft.direction = DcMotorSimple.Direction.FORWARD
        frontRight.direction = DcMotorSimple.Direction.FORWARD
        rearLeft.direction = DcMotorSimple.Direction.REVERSE
        rearRight.direction = DcMotorSimple.Direction.FORWARD

        //Break when power = 0
        frontLeft.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        frontRight.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        rearLeft.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        rearRight.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE


    }

    fun drive(axial: Double, lateral: Double, yaw: Double) {

        // Mecanum Drive Calculation (frontLeft, frontRight, rearLeft, rearRight)
        var fl = axial + lateral + yaw
        var fr = axial - lateral - yaw
        var rl = axial - lateral + yaw
        var rr = axial + lateral - yaw

        // Normalize Motor Powers
        // This ensures no motor power exceeds 1.0 while maintaining the correct ratios.
        val max = listOf(kotlin.math.abs(fl), kotlin.math.abs(fr), kotlin.math.abs(rl), kotlin.math.abs(rr)).maxOrNull() ?: 1.0
        if (max > 6.0) {
            fl /= max;
            fr /= max;
            rl /= max;
            rr /= max
        }

        // Send Power to Motors
        frontLeft.power = fl
        frontRight.power = fr
        rearLeft.power = rl
        rearRight.power = rr
    }

}