package org.firstinspires.ftc.teamcode.subsystems

import com.qualcomm.robotcore.hardware.CRServo
import com.rowanmcalpin.nextftc.core.Subsystem
import com.rowanmcalpin.nextftc.core.command.Command
import com.rowanmcalpin.nextftc.core.command.utility.InstantCommand
import com.rowanmcalpin.nextftc.ftc.OpModeData

object Intake: Subsystem() {
    lateinit var leftIntake: CRServo
    lateinit var rightIntake: CRServo

    /*You can add commands to control the intake servo like reverse and stop
    These commands are basic and just turn the servo on.
    You can have a command to automatically stop the servo after a color/distance sensor detects an object https://nextftc.dev/concepts/commands
    https://www.revrobotics.com/rev-31-1557/?searchid=4551615&search_query=color+sensor or https://docs.brushlandlabs.com/sensors/color-rangefinder
    are good color/distance sensors to use */

    /*val on: Command
        get() = InstantCommand{servo.power = 1.0} // Set the servo to full power to intake*/

    override fun initialize() {
        leftIntake = OpModeData.hardwareMap.get(CRServo::class.java, "intakeLeft")
        rightIntake = OpModeData.hardwareMap.get(CRServo::class.java, "intakeRight")

        //servo.direction = Servo.Direction.REVERSE //TODO: Reverse the direction of the servo if needed
/*
        // Reverse one servo if needed so both spin inward
        leftIntake.direction = CRServo.Direction.FORWARD
        rightIntake.direction = CRServo.Direction.REVERSE


        /** Commands for controlling intake **/
        // Spin both intake wheels inward
        val spinInwards: Command
        get() = InstantCommand {
            leftIntake.power = 1.0
            rightIntake.power = 1.0
        }

        // Spin both intake wheels outward
        val spinOutwards: Command
        get() = InstantCommand {
            leftIntake.power = -1.0
            rightIntake.power = -1.0
        }

        // Stop intake wheels
        val stop: Command
        get() = InstantCommand {
            leftIntake.power = 0.0
            rightIntake.power = 0.0
        }
*/
    }
}