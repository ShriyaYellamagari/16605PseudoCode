/*package org.firstinspires.ftc.teamcode.subsystems

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.rowanmcalpin.nextftc.core.Subsystem
import com.rowanmcalpin.nextftc.core.command.Command
import com.rowanmcalpin.nextftc.core.command.utility.InstantCommand
import com.rowanmcalpin.nextftc.ftc.OpModeData

object Outake: Subsystem() {
    lateinit var outtake1 : DcMotor
    lateinit var outtake2 : DcMotor

    /*You can add commands to control the intake servo like reverse and stop
    These commands are basic and just turn the servo on.
    You can have a command to automatically stop the servo after a color/distance sensor detects an object https://nextftc.dev/concepts/commands
    https://www.revrobotics.com/rev-31-1557/?searchid=4551615&search_query=color+sensor or https://docs.brushlandlabs.com/sensors/color-rangefinder
    are good color/distance sensors to use
     */
    val on: Command
        get() = InstantCommand{servo.power = 1.0} // Set the servo to full power to intake

    override fun initialize() {
        servo = OpModeData.hardwareMap.get(CRServo::class.java, "intake")

        //servo.direction = Servo.Direction.REVERSE //TODO: Reverse the direction of the servo if needed
    }
}*/